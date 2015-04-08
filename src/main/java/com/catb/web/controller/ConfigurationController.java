package com.catb.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.catb.common.CommonInfo;

@Controller
public class ConfigurationController {
	
	@RequiresPermissions(value = {"configuration:manage"})
	@RequestMapping(value = "/cm/configurations", method = RequestMethod.GET)
	public ModelAndView showConfiguration(ModelMap model, HttpServletRequest request) {
		CommonInfo commonInfo = (CommonInfo) request.getServletContext().getAttribute("COMMONINFO");
		model.addAttribute("commonInfo", commonInfo);
		
		return new ModelAndView("cm/configurations");
	}
	
	@RequiresPermissions(value = {"configuration:manage"})
	@RequestMapping(value = "/cm/configurations", method = RequestMethod.POST)
	public ModelAndView processUpdateConfiguration(
			@Valid CommonInfo commonInfo,
			BindingResult bindingResult, 
			HttpServletRequest request, ModelMap model) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("cm/configurations");
		} else {
			return new ModelAndView(new RedirectView(request.getContextPath() + "/cm/configurations"));
		}
	}
}
