package com.catb.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.catb.bo.PermissionBO;
import com.catb.bo.RoleBO;
import com.catb.model.Permission;
import com.catb.model.Role;

@Controller
public class GrantingPermissionController {
	
	@Autowired
	private PermissionBO permissionBO;
	
	@Autowired
	private RoleBO roleBO;
	
	@ModelAttribute("roleMap")
	public Map<Integer, String> populateRoles() {
		List<Role> roles = roleBO.getRoles();
		Map<Integer, String> roleMap = new HashMap<Integer, String>();
		for (Role role : roles) {
			roleMap.put(role.getId(), role.getName());
		}
		
		return roleMap;
	}
	
	@RequestMapping(value = "/cm/grantPermission", method = RequestMethod.GET)
	public ModelAndView showGrantingPermission(ModelMap model, @RequestParam("id") Integer id) {
		List<Permission> permissions = permissionBO.getPermissionsOfRole(id);
		model.addAttribute("permissions", permissions);
		
		for (Permission permission : permissions) {
			System.out.print(permission.getId() + " " + permission.getName() + " ");
			if (permission.getRoles() == null) {
				System.out.println("null");
			} else {
				System.out.println(permission.getRoles().size());
			}
		}
		
		//return new ModelAndView("cm/grantPermission");
		return null;
	}
}
