package com.catb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.catb.auth.AuthUtil;
import com.catb.bo.UserBO;
import com.catb.model.User;

@Controller
public class UserController {
	
	@Autowired
	private UserBO userBO;
	
	@RequestMapping(value = "/cm/user/add", method = RequestMethod.GET)
	public ModelAndView createUser() {
		
		String hashPassword = AuthUtil.hashPassword("tho");
		
		System.out.println(hashPassword);
		
		User u = new User();
		u.setUsername("tho");
		u.setPassword(hashPassword);
		u.setFullName("tran anh tho");
		
		userBO.addUser(u);
		
		return new ModelAndView("home");
	}
}
