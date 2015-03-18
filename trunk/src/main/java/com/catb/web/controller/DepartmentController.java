package com.catb.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.catb.bo.DepartmentBO;
import com.catb.common.PropertiesUtil;
import com.catb.model.Department;
import com.catb.web.viewmodel.DepartmentViewModel;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentBO departmentBO;
	
	@RequestMapping(value = "/cm/department/add", method = RequestMethod.GET)
	public ModelAndView showCreateDepartment(ModelMap model) {
		DepartmentViewModel departmentViewModel = new DepartmentViewModel();
		model.addAttribute("departmentViewModel", departmentViewModel);
		
		List<Department> departments = departmentBO.getDepartments();
		model.addAttribute("departments", departments);
		
		return new ModelAndView("cm/department/add");
	}
	
	@RequestMapping(value = "/cm/department/add", method = RequestMethod.POST)
	public ModelAndView processCreateDepartment(
			@Valid DepartmentViewModel departmentViewModel,
			BindingResult bindingResult, 
			HttpServletRequest request, ModelMap model) {
		if (bindingResult.hasErrors()) {
			List<Department> departments = departmentBO.getDepartments();
			model.addAttribute("departments", departments);
			
			return new ModelAndView("cm/department/add");
		} else {
			Department department = new Department(
					departmentViewModel.getCode(), 
					departmentViewModel.getName(), 
					departmentViewModel.getPhone(), 
					departmentViewModel.getFax(), 
					departmentViewModel.getDescription());
			
			departmentBO.addDepartment(department);
			request.getSession().setAttribute("msg", PropertiesUtil.getProperty("department.created.success"));
			
			return new ModelAndView(new RedirectView(request.getContextPath() + "/cm/department/add"));
		}
	}
}
