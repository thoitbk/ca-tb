package com.catb.web.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.catb.bo.DepartmentBO;
import com.catb.bo.DocumentBO;
import com.catb.bo.DocumentTypeCatalogBO;
import com.catb.common.Constants;
import com.catb.common.PropertiesUtil;
import com.catb.common.exception.AppException;
import com.catb.model.Department;
import com.catb.model.Document;
import com.catb.model.DocumentFile;
import com.catb.model.DocumentTypeCatalog;
import com.catb.web.util.Util;
import com.catb.web.viewmodel.DocumentViewModel;
import com.catb.web.viewmodel.FileMeta;
import com.catb.web.viewmodel.Status;

@Controller
public class DocumentController {
	
	@Autowired
	private DocumentBO documentBO;
	
	@Autowired
	private DepartmentBO departmentBO;
	
	@Autowired
	private DocumentTypeCatalogBO documentTypeCatalogBO;
	
	private Map<Integer, String> populateDepartments() {
		List<Department> departments = departmentBO.getDepartments();
		Map<Integer, String> departmentMap = new LinkedHashMap<Integer, String>();
		for (Department department : departments) {
			departmentMap.put(department.getId(), department.getName());
		}
		
		return departmentMap;
	}
	
	private Map<Integer, String> populateDocumentTypeCatalog() {
		List<DocumentTypeCatalog> documentTypeCatalogs = documentTypeCatalogBO.getDocumentTypeCatalogs();
		Map<Integer, String> documentTypeCatalogMap = new LinkedHashMap<Integer, String>();
		for (DocumentTypeCatalog documentTypeCatalog : documentTypeCatalogs) {
			documentTypeCatalogMap.put(documentTypeCatalog.getId(), documentTypeCatalog.getName());
		}
		
		return documentTypeCatalogMap;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequiresPermissions(value = {"document:manage"})
	@RequestMapping(value = "/cm/document/add", method = RequestMethod.GET)
	public ModelAndView showCreateDocument(ModelMap model, HttpServletRequest request) {
		DocumentViewModel documentViewModel = new DocumentViewModel();
		model.addAttribute("documentViewModel", documentViewModel);
		model.addAttribute("departmentMap", populateDepartments());
		model.addAttribute("documentTypeCatalogMap", populateDocumentTypeCatalog());
		model.addAttribute("documents", documentBO.getDocuments());
		request.getSession().removeAttribute("documentFiles");
		
		return new ModelAndView("cm/document/add");
	}
	
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value = {"document:manage"})
	@RequestMapping(value = "/cm/document/add", method = RequestMethod.POST)
	public ModelAndView processCreateDocument(
			@Valid DocumentViewModel documentViewModel, 
			BindingResult bindingResult, 
			ModelMap model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("departmentMap", populateDepartments());
			model.addAttribute("documentTypeCatalogMap", populateDocumentTypeCatalog());
			model.addAttribute("documents", documentBO.getDocuments());
			return new ModelAndView("cm/document/add");
		} else {
			Document document = new Document();
			document.setCode(documentViewModel.getCode());
			document.setContent(documentViewModel.getContent());
			document.setDescription(documentViewModel.getDescription());
			document.setLeader(documentViewModel.getLeader());
			document.setPublishedDate(documentViewModel.getPublishDate());
			Integer sqNumber = Constants.MAX_SQ_NUMBER;
			if (documentViewModel.getSqNumber() != null && !"".equals(documentViewModel.getSqNumber().trim())) {
				sqNumber = Integer.parseInt(documentViewModel.getSqNumber().trim());
			}
			document.setSqNumber(sqNumber);
			document.setSummary(documentViewModel.getSummary());
			document.setValidDate(documentViewModel.getValidDate());
			
			List<FileMeta> fileMetas = (List<FileMeta>) request.getSession().getAttribute("documentFiles");
			List<DocumentFile> files = null;
			if (fileMetas != null && fileMetas.size() > 0) {
				files = new LinkedList<DocumentFile>();
				for (FileMeta fileMeta : fileMetas) {
					files.add(new DocumentFile(fileMeta.getFileName(), fileMeta.getPath(), fileMeta.getFileType()));
				}
			}
			
			Integer departmentId = documentViewModel.getDepartmentId();
			Integer documentTypeCatalogId = documentViewModel.getDocumentTypeCatalogId();
			
			documentBO.addDocument(document, departmentId, documentTypeCatalogId, files);
			
			request.getSession().setAttribute("msg", PropertiesUtil.getProperty("document.created.successfully"));
			
			return new ModelAndView(new RedirectView(request.getContextPath() + "/cm/document/add"));
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value = {"document:manage"})
	@RequestMapping(value = "/cm/document/uploadFiles", method = RequestMethod.POST)
	@ResponseBody
	public List<FileMeta> uploadFiles(HttpServletRequest request, HttpServletResponse response) {
		List<FileMeta> files = getUploadFiles(request);
		
		if (files != null && files.size() > 0) {
			List<FileMeta> storedFiles = (List<FileMeta>) request.getSession().getAttribute("documentFiles");
			if (storedFiles != null) {
				storedFiles.addAll(files);
			} else {
				request.getSession().setAttribute("documentFiles", files);
			}
		}
		
		return files;
	}
	
	@SuppressWarnings("unchecked")
	private List<FileMeta> getUploadFiles(HttpServletRequest request) {
		List<FileMeta> files = new LinkedList<FileMeta>();
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		FileMeta temp = null;
		
		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			try {
				List<FileItem> items = upload.parseRequest(request);
				
				for (FileItem item : items) {
					if (!item.isFormField()) {
						String dirName = Util.getCurrentDateString();
						String fileName = item.getName();
						String randomString = Util.getRandomString();
						String newFileName = randomString + "." + FilenameUtils.getExtension(fileName);
						String path = String.format("\\%s\\%s", dirName, newFileName);
						
						File file = createFile(dirName, newFileName);
						if (!file.exists()) {
							item.write(file);
							temp = new FileMeta(
									randomString, fileName, String.valueOf(item.getSize() / 1024), 
									item.getContentType(), path);
						}
						files.add(temp);
					}
				}
			} catch (Exception ex) {
				throw new AppException(ex);
			}
		}
		
		return files;
	}
	
	private File createFile(String dirName, String fileName) {
		String directory = Constants.DOCUMENT_LOCATION + File.separator + dirName;
		File dir = new File(directory);
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		String absolutePath = directory + File.separator + fileName;
		File file = new File(absolutePath);
		
		return file;
	}
	
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value = {"document:manage"})
	@RequestMapping(value = "/cm/document/removeFiles", method = RequestMethod.POST)
	@ResponseBody
	public Status removeAdministrativeProcedureFiles(
			@RequestParam(value = "fileIds", required = true) String[] fileIds, HttpServletRequest request) {
		List<FileMeta> fileMetas = (List<FileMeta>) request.getSession().getAttribute("documentFiles");
		if (fileMetas != null && fileMetas.size() > 0) {
			Iterator<FileMeta> fileMetaIterator = fileMetas.iterator();
			FileMeta fileMeta = null;
			while (fileMetaIterator.hasNext()) {
				fileMeta = fileMetaIterator.next();
				for (String fileId : fileIds) {
					if (fileId != null && fileId.equals(fileMeta.getId())) {
						String path = String.format("%s\\%s", Constants.DOCUMENT_LOCATION, fileMeta.getPath());
						File file = new File(path);
						if (file.exists()) {
							file.delete();
						}
						fileMetaIterator.remove();
						break;
					}
				}
			}
		}
		
		Status status = new Status(Status.OK, "ok");
		return status;
	}
}
