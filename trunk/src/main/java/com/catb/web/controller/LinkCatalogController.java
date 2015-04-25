package com.catb.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.catb.bo.LinkCatalogBO;
import com.catb.common.Constants;
import com.catb.common.PropertiesUtil;
import com.catb.model.LinkCatalog;
import com.catb.web.viewmodel.LinkCatalogViewModel;
import com.catb.web.viewmodel.Status;

@Controller
public class LinkCatalogController {
	
	@Autowired
	private LinkCatalogBO linkCatalogBO;
	
	@RequiresPermissions(value = {"link:manage"})
	@RequestMapping(value = "/cm/linkCatalog/add", method = RequestMethod.GET)
	public ModelAndView showCreateLinkCatalog(ModelMap model) {
		LinkCatalogViewModel linkCatalogViewModel = new LinkCatalogViewModel();
		model.addAttribute("linkCatalogViewModel", linkCatalogViewModel);
		
		List<LinkCatalog> linkCatalogs = linkCatalogBO.getLinkCatalogs();
		model.addAttribute("linkCatalogs", linkCatalogs);
		
		return new ModelAndView("cm/linkCatalog/add");
	}
	
	@RequiresPermissions(value = {"link:manage"})
	@RequestMapping(value = "/cm/linkCatalog/add", method = RequestMethod.POST)
	public ModelAndView processCreateLinkCatalog(
								@Valid LinkCatalogViewModel linkCatalogViewModel, 
								BindingResult bindingResult, 
								HttpServletRequest request, ModelMap model) {
		if (bindingResult.hasErrors()) {
			List<LinkCatalog> linkCatalogs = linkCatalogBO.getLinkCatalogs();
			model.addAttribute("linkCatalogs", linkCatalogs);
			
			return new ModelAndView("cm/linkCatalog/add");
		} else {
			LinkCatalog linkCatalog = new LinkCatalog();
			linkCatalog.setTitle(linkCatalogViewModel.getTitle());
			linkCatalog.setLinkSite(linkCatalogViewModel.getLinkSite());
			Integer sqNumber = Constants.MAX_SQ_NUMBER;
			if (linkCatalogViewModel.getSqNumber() != null && !"".equals(linkCatalogViewModel.getSqNumber().trim())) {
				sqNumber = Integer.parseInt(linkCatalogViewModel.getSqNumber().trim());
			}
			linkCatalog.setSqNumber(sqNumber);
			linkCatalog.setOpenBlank(linkCatalogViewModel.getOpenBlank());
			
			linkCatalogBO.addLinkCatalog(linkCatalog);
			request.getSession().setAttribute("msg", PropertiesUtil.getProperty("link.created.successfully"));
			
			return new ModelAndView(new RedirectView(request.getContextPath() + "/cm/linkCatalog/add"));
		}
	}
	
	@RequiresPermissions(value = {"link:manage"})
	@RequestMapping(value = "/cm/linkCatalog/delete", method = RequestMethod.POST)
	@ResponseBody
	public Status deleteLinkCatalog(@RequestParam("ids") Integer[] ids, HttpSession session) {
		linkCatalogBO.deleteLinkCatalogs(ids);
		session.setAttribute("msg", PropertiesUtil.getProperty("link.deleted.successfully"));
		Status status = new Status(Status.OK, "ok");
		return status;
	}
}
