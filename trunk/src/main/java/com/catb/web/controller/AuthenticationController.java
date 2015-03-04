package com.catb.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.catb.common.PropertiesUtil;
import com.catb.common.web.Util;

@Controller
public class AuthenticationController {
	
	static Logger logger = Logger.getLogger(AuthenticationController.class.getCanonicalName());
	
	@RequestMapping(value = "/cm/login", method = RequestMethod.GET)
	public ModelAndView showLogin() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated() || subject.isRemembered()) {
			subject.logout();
		}
		
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/cm/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "username", required = true) String username, 
							  @RequestParam(value = "password", required = true) String password, 
							  @RequestParam(value = "rememberMe", required = false, defaultValue = "false") Boolean rememberMe, 
							  HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			AuthenticationToken token = new UsernamePasswordToken(username, password, rememberMe);
			try {
				subject.login(token);
			} catch (AuthenticationException ex) {
				logger.info(String.format("Login attempt of user %s at %s failed", username, Util.getIpAddress(request)));
				HttpSession session = request.getSession();
				session.setAttribute("loginMsg", PropertiesUtil.getProperty("login.failed"));
				return new ModelAndView(new RedirectView("/cm/login"));
			}
			
			logger.info(String.format("Login successfully - user: %s at %s", username, Util.getIpAddress(request)));
			return new ModelAndView(new RedirectView("/cm/home"));
		}
		
		return null;
	}
	
	@RequestMapping(value = "/cm/unauthorized", method = RequestMethod.GET)
	public ModelAndView unauthorized() {
		return new ModelAndView("unauthorized");
	}
}
