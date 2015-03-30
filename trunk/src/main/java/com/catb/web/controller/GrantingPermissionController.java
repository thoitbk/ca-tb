package com.catb.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.catb.bo.PermissionBO;
import com.catb.bo.RoleBO;
import com.catb.common.PropertiesUtil;
import com.catb.model.Role;
import com.catb.vo.PermissionInfo;
import com.catb.web.viewmodel.Status;

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
	
	@RequestMapping(value = "/cm/showPermission", method = RequestMethod.GET)
	public ModelAndView showGrantingPermission(ModelMap model, @RequestParam(value = "id", required = false) Integer id) {
		List<PermissionInfo> permissionInfos = permissionBO.getPermissionInfoByRoleId(id);
		model.addAttribute("permissionInfos", permissionInfos);
		
		return new ModelAndView("cm/showPermission");
	}
	
	@RequestMapping(value = "/cm/changePermission", method = RequestMethod.POST)
	@ResponseBody
	public Status changePermissionsOfRole(
			@RequestParam("roleId") Integer roleId, 
			@RequestParam("permissionIds") Integer[] permissionIds,
			HttpServletRequest request) {
		Status status = new Status(Status.OK, "ok");
		
		System.out.println(roleId + " " + permissionIds.length);
		
		for (Integer pId : permissionIds) {
			System.out.println(pId);
		}
		
		if (roleId < 0 || permissionIds == null || roleBO.getRoleById(roleId) == null) {
			request.getSession().setAttribute("msg", PropertiesUtil.getProperty("invalid.parameter"));
		} else {
			roleBO.updatePermissionsOfRole(roleId, permissionIds);
			request.getSession().setAttribute("msg", PropertiesUtil.getProperty("grant.permission.successfully"));
		}
		
		return status;
	}
}
