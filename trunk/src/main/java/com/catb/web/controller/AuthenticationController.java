package com.catb.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
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
		
		return new ModelAndView("cm/login");
	}
	
	@RequestMapping(value = "/cm/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "username") String username, 
							  @RequestParam(value = "password") String password, 
							  @RequestParam(value = "rememberMe", required = false, defaultValue = "false") Boolean rememberMe, 
							  HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (username == null || "".equals(username.trim()) || password == null || "".equals(password.trim())) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMsg", PropertiesUtil.getProperty("username.password.not.empty"));
			return new ModelAndView(new RedirectView("/cm/login"));
		}
		
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
			SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
			if (savedRequest != null) {
				return new ModelAndView(new RedirectView(savedRequest.getRequestUrl()));
			} else {
				return new ModelAndView(new RedirectView("/cm/home"));
			}
		} else {
			return new ModelAndView(new RedirectView("/cm/home"));
		}
	}
	
	@RequestMapping(value = "/cm/unauthorized", method = RequestMethod.GET)
	public ModelAndView unauthorized() {
		return new ModelAndView("cm/unauthorized");
	}
}
