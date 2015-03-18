package com.catb.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.catb.bo.UserBO;

@Controller
public class HomeController {
	
	@Autowired
	private UserBO userBO;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(ModelMap model) {
		return "home";
	}
	
	@RequestMapping(value = "/cm", method = RequestMethod.GET)
	public ModelAndView showContentManagerment(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return new ModelAndView(new RedirectView(request.getContextPath() + "/cm/home"));
		} else {
			return new ModelAndView(new RedirectView(request.getContextPath() + "/cm/login"));
		}
	}
	
	@RequiresAuthentication
	@RequestMapping(value = "/cm/home", method = RequestMethod.GET)
	public ModelAndView showContentManagementHome() {
		return new ModelAndView("cm/home"); 
	}
}
