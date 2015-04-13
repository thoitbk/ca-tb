package com.catb.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.catb.bo.NewsCatalogBO;
import com.catb.common.PropertiesUtil;
import com.catb.model.NewsCatalog;
import com.catb.web.viewmodel.NewsCatalogViewModel;

@Controller
public class NewsCatalogController {
	
	@Autowired
	private NewsCatalogBO newsCatalogBO;
	
	@SuppressWarnings("unchecked")
	@ModelAttribute("displayLocations")
	public Map<String, String> populateDisplayLocations(HttpServletRequest request) {
		Map<String, String> displayLocations = (Map<String, String>) request.getServletContext().getAttribute("DISPLAY_LOCATION"); 
		return displayLocations;
	}
	
	@ModelAttribute("newsCatalogsMap")
	public Map<Integer, String> getNewsCatalogMap(
			@RequestParam(value = "location", required = false, defaultValue = "") String displayLocation) {
		List<NewsCatalog> bindingNewsCatalogs = newsCatalogBO.getNewsCatalog(displayLocation, null);
		Map<Integer, String> newsCatalogsMap = new HashMap<Integer, String>();
		if (bindingNewsCatalogs != null && bindingNewsCatalogs.size() > 0) {
			for (NewsCatalog newsCatalog : bindingNewsCatalogs) {
				newsCatalogsMap.put(newsCatalog.getId(), String.format("%s (Level %d)", newsCatalog.getName(), newsCatalog.getChildLevel()));
			}
		}
		
		return newsCatalogsMap;
	}
	
	@RequiresPermissions(value = {"newsCatalog:manage"})
	@RequestMapping(value = "/cm/newsCatalog/add", method = RequestMethod.GET)
	public ModelAndView showCreateNewsCatalog(
			@RequestParam(value = "location", required = false, defaultValue = "") String displayLocation, 
			@RequestParam(value = "parent", required = false, defaultValue = "-1") Integer parentId, 
			ModelMap model, HttpServletRequest request) {
		
		NewsCatalogViewModel newsCatalogViewModel = new NewsCatalogViewModel();
		if (displayLocation != null && !"".equals(displayLocation.trim())) {
			newsCatalogViewModel.setDisplayLocation(displayLocation);
		}
		if (parentId != null && parentId >= 0) {
			newsCatalogViewModel.setParentId(parentId);
		}
		model.addAttribute("newsCatalogViewModel", newsCatalogViewModel);
		
		List<NewsCatalog> newsCatalogs = newsCatalogBO.getNewsCatalog(displayLocation, parentId);
		model.addAttribute("newsCatalogs", newsCatalogs);
		
		return new ModelAndView("cm/newsCatalog/add");
	}
	
	@RequiresPermissions(value = {"newsCatalog:manage"})
	@RequestMapping(value = "/cm/newsCatalog/add", method = RequestMethod.POST)
	public ModelAndView processCreateNewsCatalog(
								@Valid NewsCatalogViewModel newsCatalogViewModel, 
								BindingResult bindingResult, 
								ModelMap model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			List<NewsCatalog> newsCatalogs = newsCatalogBO.getNewsCatalog(
															newsCatalogViewModel.getDisplayLocation(), 
															newsCatalogViewModel.getParentId());
			model.addAttribute("newsCatalogs", newsCatalogs);
			
			return new ModelAndView("cm/newsCatalog/add");
		} else {
			NewsCatalog newsCatalog = new NewsCatalog();
			
			if (newsCatalogViewModel.getDisplayLocation() != null && 
					populateDisplayLocations(request).containsKey(newsCatalogViewModel.getDisplayLocation())) {
				newsCatalog.setDisplayLocation(newsCatalogViewModel.getDisplayLocation());
			}
			
			Integer childLevel = 0;
			if (newsCatalogViewModel.getParentId() != null) {
				if (newsCatalogViewModel.getParentId() >= 0) {
					newsCatalog.setParentId(newsCatalogViewModel.getParentId());
					NewsCatalog parentNewsCatalog = newsCatalogBO.getNewsCatalogById(newsCatalogViewModel.getParentId());
					if (parentNewsCatalog != null && parentNewsCatalog.getChildLevel() != null) {
						childLevel = parentNewsCatalog.getChildLevel() + 1;
					}
				}
			}
			newsCatalog.setChildLevel(childLevel);
			
			newsCatalog.setName(newsCatalogViewModel.getName());
			newsCatalog.setUrl(newsCatalogViewModel.getUrl());
			newsCatalog.setSqNumber(Integer.parseInt(newsCatalogViewModel.getSqNumber()));
			newsCatalog.setDisplay(newsCatalogViewModel.getDisplay());
			newsCatalog.setSpecialSite(newsCatalogViewModel.getSpecialSite());
			
			newsCatalogBO.addNewsCatalog(newsCatalog);
			
			request.getSession().setAttribute("msg", PropertiesUtil.getProperty("newsCatalog.created.successfully"));
			
			return new ModelAndView(new RedirectView(request.getContextPath() + "/cm/newsCatalog/add"));
		}
	}
	
	@RequiresPermissions(value = {"newsCatalog:manage"})
	@RequestMapping(value = "/cm/newsCatalog/update/{id}", method = RequestMethod.GET)
	public ModelAndView showUpdateNewsCatalog(@PathVariable("id") Integer id, ModelMap model) {
		NewsCatalog newsCatalog = newsCatalogBO.getNewsCatalogById(id);
		NewsCatalogViewModel newsCatalogViewModel = null;
		if (newsCatalog != null) {
			newsCatalogViewModel = new NewsCatalogViewModel(
											newsCatalog.getDisplayLocation(), 
											newsCatalog.getParentId(), 
											newsCatalog.getName(), 
											newsCatalog.getUrl(), 
											String.valueOf(newsCatalog.getSqNumber()), 
											newsCatalog.getDisplay(), 
											newsCatalog.getSpecialSite());
		} else {
			newsCatalogViewModel = new NewsCatalogViewModel();
		}
		model.addAttribute("newsCatalogViewModel", newsCatalogViewModel);
		
		List<NewsCatalog> newsCatalogs = newsCatalogBO.getNewsCatalog(newsCatalog.getDisplayLocation(), newsCatalog.getParentId());
		model.addAttribute("newsCatalogs", newsCatalogs);
		
		return new ModelAndView("cm/newsCatalog/update");
	}
	
	@RequiresPermissions(value = {"newsCatalog:manage"})
	@RequestMapping(value = "/cm/newsCatalog/update/{id}", method = RequestMethod.POST)
	public ModelAndView processUpdateNewsCatalog(
								@PathVariable("id") Integer id, 
								@Valid NewsCatalogViewModel newsCatalogViewModel, 
								BindingResult bindingResult, 
								ModelMap model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			List<NewsCatalog> newsCatalogs = newsCatalogBO.getNewsCatalog(newsCatalogViewModel.getDisplayLocation(), newsCatalogViewModel.getParentId());
			model.addAttribute("newsCatalogs", newsCatalogs);
			
			return new ModelAndView("cm/newsCatalog/update");
		} else {
			NewsCatalog newsCatalog = new NewsCatalog(id, 
									newsCatalogViewModel.getName(), newsCatalogViewModel.getUrl(), 
									Integer.parseInt(newsCatalogViewModel.getSqNumber()), 
									newsCatalogViewModel.getDisplay(), newsCatalogViewModel.getSpecialSite(), 
									newsCatalogViewModel.getDisplayLocation(), newsCatalogViewModel.getParentId(), null);
			newsCatalogBO.updateNewsCatalog(newsCatalog);
			
			request.getSession().setAttribute("msg", PropertiesUtil.getProperty("newsCatalog.updated.successfully"));
			
			String queryString = String.format("?location=%s&parent=%d", newsCatalogViewModel.getDisplayLocation(), newsCatalogViewModel.getParentId());
			return new ModelAndView(new RedirectView(request.getContextPath() + "/cm/newsCatalog/add" + queryString));
		}
	}
}
