package com.catb.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.catb.auth.AuthUtil;
import com.catb.bo.DepartmentBO;
import com.catb.bo.PositionBO;
import com.catb.bo.UserBO;
import com.catb.common.PropertiesUtil;
import com.catb.model.Department;
import com.catb.model.Position;
import com.catb.model.User;
import com.catb.web.validator.CreateUserValidator;
import com.catb.web.viewmodel.UserViewModel;

@Controller
public class UserController {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PositionBO positionBO;
	
	@Autowired
	private DepartmentBO departmentBO;
	
	
	@Autowired
	private CreateUserValidator createUserValidator;
	
	@ModelAttribute("positions")
	public Map<Integer, String> populatePosition() {
		Map<Integer, String> positions = new HashMap<Integer, String>();
		List<Position> positionList = positionBO.getPositions();
		for (Position position : positionList) {
			positions.put(position.getId(), position.getName());
		}
		
		return positions;
	}
	
	@ModelAttribute("departments")
	public Map<Integer, String> populateDepartment() {
		Map<Integer, String> departments = new HashMap<Integer, String>();
		List<Department> departmentList = departmentBO.getDepartments();
		for (Department department : departmentList) {
			departments.put(department.getId(), department.getName());
		}
		
		return departments;
	}
	
	@RequestMapping(value = "/cm/user/add", method = RequestMethod.GET)
	public ModelAndView showCreateUser(ModelMap model) {
		UserViewModel userViewModel = new UserViewModel();
		model.addAttribute("userViewModel", userViewModel);
		
		List<User> users = userBO.getUsers();
		model.addAttribute("users", users);
		
		return new ModelAndView("cm/user/add");
	}
	
	@RequestMapping(value = "/cm/user/add", method = RequestMethod.POST)
	public ModelAndView processCreateUser(
			@Valid UserViewModel userViewModel,
			BindingResult bindingResult, 
			HttpServletRequest request, ModelMap model) {
		createUserValidator.validate(userViewModel, bindingResult);
		
		if (bindingResult.hasErrors()) {
			List<User> users = userBO.getUsers();
			model.addAttribute("users", users);
			
			return new ModelAndView("cm/user/add");
		} else {
			User user = new User();
			user.setUsername(userViewModel.getUsername());
			user.setPassword(AuthUtil.hashPassword(userViewModel.getPassword()));
			user.setFullName(userViewModel.getFullName());
			Boolean gender = null;
			if (userViewModel.getGender().equals(0)) {
				gender = true;
			} else if (userViewModel.getGender().equals(1)) {
				gender = false;
			}
			user.setGender(gender);
			user.setHomePhoneNumber(userViewModel.getHomePhoneNumber());
			user.setMobileNumber(userViewModel.getMobileNumber());
			user.setOfficePhoneNumber(userViewModel.getOfficePhoneNumber());
			user.setAddress(userViewModel.getAddress());
			user.setEmail(userViewModel.getEmail());
			user.setDescription(userViewModel.getDescription());
			Integer positionId = userViewModel.getPosition() < 0 ? null : userViewModel.getPosition();
			Integer departmentId = userViewModel.getDepartment() < 0 ? null : userViewModel.getDepartment();
			
			userBO.addUser(user, positionId, departmentId);
			
			request.getSession().setAttribute("msg", PropertiesUtil.getProperty("user.created.successfully"));
			
			return new ModelAndView(new RedirectView(request.getContextPath() + "/cm/user/add"));
		}
	}
}
