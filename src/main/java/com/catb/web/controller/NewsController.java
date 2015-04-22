package com.catb.web.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.catb.bo.NewsBO;
import com.catb.bo.NewsCatalogBO;
import com.catb.common.Constants;
import com.catb.common.PropertiesUtil;
import com.catb.common.exception.AppException;
import com.catb.common.web.Util;
import com.catb.model.News;
import com.catb.model.NewsCatalog;
import com.catb.model.NewsContent;
import com.catb.model.NewsStatus;
import com.catb.vo.SearchNewsVO;
import com.catb.web.tag.PageInfo;
import com.catb.web.viewmodel.FileMeta;
import com.catb.web.viewmodel.NewsViewModel;
import com.catb.web.viewmodel.SearchNewsViewModel;
import com.catb.web.viewmodel.Status;

@Controller
public class NewsController {
	
	@Autowired
	private NewsCatalogBO newsCatalogBO;
	
	@Autowired
	private NewsBO newsBO;
	
	@ModelAttribute("newsCatalogs")
	public Map<Integer, String> populateNewsCatalogs() {
		List<NewsCatalog> newsCatalogs = newsCatalogBO.getNewsCatalog(null, null);
		Map<Integer, String> newsCatalogsMap = new LinkedHashMap<Integer, String>();
		for (NewsCatalog newsCatalog : newsCatalogs) {
			newsCatalogsMap.put(newsCatalog.getId(), newsCatalog.getName());
		}
		
		return newsCatalogsMap;
	}
	
	@SuppressWarnings("unchecked")
	@ModelAttribute("newsStatuses")
	public Map<Integer, String> populateNewsStatus(HttpServletRequest request) {
		Map<Integer, String> newsStatuses = (Map<Integer, String>) request.getServletContext().getAttribute("NEWS_STATUSES");
		return newsStatuses;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequiresPermissions(value = {"news:create"})
	@RequestMapping(value = "/cm/news/create", method = RequestMethod.GET)
	public ModelAndView showCreateNews(ModelMap model) {
		NewsViewModel newsViewModel = new NewsViewModel();
		newsViewModel.setPostedDate(new Date());
		model.addAttribute("newsViewModel", newsViewModel);
		
		return new ModelAndView("cm/news/create");
	}
	
	@RequiresPermissions(value = {"news:create"})
	@RequestMapping(value = "/cm/news/create", method = RequestMethod.POST)
	public ModelAndView processCreateNews(
			@Valid NewsViewModel newsViewModel, 
			BindingResult bindingResult, 
			ModelMap model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("cm/news/create");
		} else {
			News news = new News();
			if (newsViewModel.getSqNumber() != null && !"".equals(newsViewModel.getSqNumber().trim())) {
				news.setSqNumber(Integer.parseInt(newsViewModel.getSqNumber()));
			}
			news.setTitle(newsViewModel.getTitle());
			news.setSummary(newsViewModel.getSummary());
			news.setAuthor(newsViewModel.getAuthor());
			news.setPostedDate(newsViewModel.getPostedDate());
			news.setHotNews(newsViewModel.getHotNews());
			news.setStatus(NewsStatus.PENDING.getStatus());
			FileMeta fileMeta = (FileMeta) request.getSession().getAttribute("newsImage");
			if (fileMeta != null) {
				news.setImage(fileMeta.getPath());
				request.getSession().removeAttribute("newsImage");
			}
			
			NewsContent newsContent = new NewsContent();
			newsContent.setContent(newsViewModel.getContent());
			
			newsBO.addNews(news, newsContent, Integer.parseInt(newsViewModel.getNewsCatalogId()));
			
			request.getSession().setAttribute("msg", PropertiesUtil.getProperty("news.created.successfully"));
			
			return new ModelAndView(new RedirectView(request.getContextPath() + "/cm/news/create"));
		}
	}
	
	@RequiresPermissions(value = {"news:create"})
	@RequestMapping(value = "/cm/news/uploadNewsImage", method = RequestMethod.POST)
	@ResponseBody
	public FileMeta uploadNewsImage(HttpServletRequest request, HttpServletResponse response) {
		List<FileMeta> files = getUploadFiles(request);
		
		if (files != null && files.size() > 0) {
			request.getSession().setAttribute("newsImage", files.get(0));
			return files.get(0);
		}
		
		return null;
	}
	
	@RequiresPermissions(value = {"news:create"})
	@RequestMapping(value = "/cm/news/removeNewsImage", method = RequestMethod.POST)
	@ResponseBody
	public Status removeNewsImage(HttpServletRequest request) {
		FileMeta fileMeta = (FileMeta) request.getSession().getAttribute("newsImage");
		if (fileMeta != null) {
			String relativePath = fileMeta.getPath();
			String absolutePath = request.getServletContext().getRealPath(relativePath);
			File file = new File(absolutePath);
			if (file.exists()) {
				file.delete();
			}
			
			request.getSession().removeAttribute("newsImage");
		}
		
		Status status = new Status(Status.OK, "ok");
		return status;
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
						String imageUrl = String.format("%s/%s/%s", Constants.NEWS_IMAGE_PATH, dirName, newFileName);
						
						File file = createFile(dirName, newFileName);
						if (!file.exists()) {
							item.write(file);
							temp = new FileMeta(
									randomString, fileName, String.valueOf(item.getSize() / 1024), 
									item.getContentType(), imageUrl);
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
		String directory = Constants.NEWS_IMAGE_LOCATION + File.separator + dirName;
		File dir = new File(directory);
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		String absolutePath = directory + File.separator + fileName;
		File file = new File(absolutePath);
		
		return file;
	}
	
	@RequiresPermissions(value = {"news:manage"})
	@RequestMapping(value = "/cm/news/manage", method = RequestMethod.GET)
	public ModelAndView manageNews(
						SearchNewsViewModel searchNewsViewModel,
						@RequestParam(value = "p", defaultValue = "1", required = false) Integer page,
						ModelMap model, HttpServletRequest request) {
		SearchNewsViewModel viewModel = new SearchNewsViewModel();
		SearchNewsVO searchNewsVO = new SearchNewsVO();
		Map<String, String> params = new LinkedHashMap<String, String>();
		if (searchNewsViewModel.getNewsCatalogId() != null && searchNewsViewModel.getNewsCatalogId() >= 0) {
			searchNewsVO.setNewsCatalogId(searchNewsViewModel.getNewsCatalogId());
			viewModel.setNewsCatalogId(searchNewsViewModel.getNewsCatalogId());
			params.put("newsCatalogId", String.valueOf(searchNewsViewModel.getNewsCatalogId()));
		}
		if (searchNewsViewModel.getNewsStatus() != null && searchNewsViewModel.getNewsStatus() >= 0) {
			searchNewsVO.setNewsStatus(searchNewsViewModel.getNewsStatus());
			viewModel.setNewsStatus(searchNewsViewModel.getNewsStatus());
			params.put("newsStatus", String.valueOf(searchNewsViewModel.getNewsStatus()));
		}
		if (searchNewsViewModel.getHotNews() != null) {
			searchNewsVO.setHotNews(searchNewsViewModel.getHotNews());
			viewModel.setHotNews(searchNewsViewModel.getHotNews());
			params.put("hotNews", String.valueOf(searchNewsViewModel.getHotNews()));
		}
		if (searchNewsViewModel.getAuthor() != null && !"".equals(searchNewsViewModel.getAuthor().trim())) {
			searchNewsVO.setAuthor(searchNewsViewModel.getAuthor().trim());
			viewModel.setAuthor(searchNewsViewModel.getAuthor());
			params.put("author", searchNewsViewModel.getAuthor());
		}
		if (searchNewsViewModel.getTitle() != null && !"".equals(searchNewsViewModel.getTitle().trim())) {
			searchNewsVO.setTitle(searchNewsViewModel.getTitle().trim());
			viewModel.setTitle(searchNewsViewModel.getTitle());
			params.put("title", searchNewsViewModel.getTitle());
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		if (searchNewsViewModel.getFrom() != null && !"".equals(searchNewsViewModel.getFrom().trim())) {
			try {
				Date from = dateFormat.parse(searchNewsViewModel.getFrom().trim());
				searchNewsVO.setFrom(from);
				viewModel.setFrom(searchNewsViewModel.getFrom());
				params.put("from", searchNewsViewModel.getFrom());
			} catch (Exception ex) {
				// Ignore this exception
			}
		}
		if (searchNewsViewModel.getTo() != null && !"".equals(searchNewsViewModel.getTo().trim())) {
			try {
				Date to = dateFormat.parse(searchNewsViewModel.getTo().trim());
				searchNewsVO.setTo(to);
				viewModel.setTo(searchNewsViewModel.getTo());
				params.put("to", searchNewsViewModel.getTo());
			} catch (Exception ex) {
			}
		}
		
		Integer pageSize = Util.getPageSize(request);
		List<News> newses = newsBO.getNews(searchNewsVO, page, pageSize);
		PageInfo pageInfo = new PageInfo(newsBO.countNews(searchNewsVO), page, pageSize);
		
		model.addAttribute("searchNewsViewModel", viewModel);
		model.addAttribute("newses", newses);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("params", params);
		
		return new ModelAndView("cm/news/manage");
	}
}
