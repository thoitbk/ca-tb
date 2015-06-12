package com.catb.web.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.catb.bo.ImageBO;
import com.catb.bo.ImageCatalogBO;
import com.catb.model.Image;
import com.catb.model.ImageCatalog;
import com.catb.web.tag.PageInfo;
import com.catb.web.util.Util;
import com.catb.web.viewmodel.FileMeta;
import com.catb.web.viewmodel.ImageViewModel;

@Controller
public class ImageController {
	
	@Autowired
	private ImageBO imageBO;
	
	@Autowired
	private ImageCatalogBO imageCatalogBO;
	
	private Map<Integer, String> populateImageCatalogs() {
		List<ImageCatalog> imageCatalogs = imageCatalogBO.getImageCatalogs();
		Map<Integer, String> imageCatalogMap = new LinkedHashMap<Integer, String>();
		for (ImageCatalog imageCatalog : imageCatalogs) {
			imageCatalogMap.put(imageCatalog.getId(), imageCatalog.getName());
		}
		
		return imageCatalogMap;
	}
	
	@RequiresPermissions(value = {"image:manage"})
	@RequestMapping(value = "/cm/image/add", method = RequestMethod.GET)
	public ModelAndView showCreateImage(
			@RequestParam(value = "cId", required = false) Integer imageCatalogId, 
			@RequestParam(value = "p", required = false, defaultValue = "1") Integer page, 
			ModelMap model, HttpServletRequest request) {
		ImageViewModel imageViewModel = new ImageViewModel();
		model.addAttribute("imageViewModel", imageViewModel);
		
		Integer pageSize = Util.getPageSize(request);
		model.addAttribute("images", imageBO.getImages(imageCatalogId, page, pageSize));
		model.addAttribute("pageInfo", new PageInfo(imageBO.countImages(imageCatalogId), page, pageSize));
		
		model.addAttribute("imageCatalogMap", populateImageCatalogs());
		
		request.getSession().removeAttribute("imageFile");
		
		return new ModelAndView("cm/image/add");
	}
	
	@RequiresPermissions(value = {"image:manage"})
	@RequestMapping(value = "/cm/image/add", method = RequestMethod.POST)
	public ModelAndView processCreateImage(
								@RequestParam(value = "cId", required = false) Integer imageCatalogId,
								@RequestParam(value = "p", required = false, defaultValue = "1") Integer page,
								@Valid ImageViewModel imageViewModel, BindingResult bindingResult, 
								HttpServletRequest request, ModelMap model) {
		if (bindingResult.hasErrors()) {
			Integer pageSize = Util.getPageSize(request);
			model.addAttribute("images", imageBO.getImages(imageCatalogId, page, pageSize));
			model.addAttribute("pageInfo", new PageInfo(imageBO.countImages(imageCatalogId), page, pageSize));
			
			return new ModelAndView("cm/image/add");
		} else {
			Image image = new Image();
			image.setCaption(imageViewModel.getCaption());
			image.setDisplay(imageViewModel.getDisplay());
			
			FileMeta fileMeta = (FileMeta) request.getSession().getAttribute("imageFile");
			if (fileMeta != null) {
				image.setFile(fileMeta.getPath());
				request.getSession().removeAttribute("imageFile");
			}
			
			return null;
		}
	}
}
