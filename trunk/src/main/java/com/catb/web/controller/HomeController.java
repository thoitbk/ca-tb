package com.catb.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catb.bo.UserBO;
import com.catb.model.User;

@Controller
public class HomeController {
	
	@Autowired
	private UserBO userBO;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(ModelMap model) {
		System.out.println("home");
		List<User> users = userBO.getUsers();
		model.addAttribute("user", users.size());
		return "home";
	}
	
	@RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
	public String addUser(@PathVariable("id") Integer id) {
		
		User user = new User(null, String.valueOf(id), String.valueOf(id), null, String.valueOf(id), null, null, null, null, null, null, null, null, null);
		
		userBO.addUser(user);
		
		return "home";
	}
}
