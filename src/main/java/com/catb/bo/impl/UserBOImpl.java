package com.catb.bo.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catb.bo.UserBO;
import com.catb.dao.UserDAO;
import com.catb.model.Permission;
import com.catb.model.Role;
import com.catb.model.User;

@Service
public class UserBOImpl implements UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Transactional
	public void addUser(User user) {
		userDAO.addUser(user);
	}
	
	@Transactional
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}
	
	@Transactional
	public Set<Role> getRolesForUser(String username) {
		User user = getUserByUsername(username);
		return user.getRoles();
	}
	
	@Transactional
	public Set<String> getRoleNamesForUser(String username) {
		Set<Role> roles = getRolesForUser(username);
		Set<String> roleNames = new LinkedHashSet<String>();
		if (roles != null) {
			for (Role role : roles) {
				String roleName = role.getName();
				if (roleName != null) {
					roleNames.add(roleName);
				}
			}
		}
		
		return roleNames;
	}
	
	@Transactional
	public Set<String> getPermissions(String roleName) {
		List<Permission> permissions = userDAO.getPermissionsByRole(roleName);
		Set<String> permissionSet = new LinkedHashSet<String>();
		for (Permission p : permissions) {
			if (p.getName() != null) {
				permissionSet.add(p.getName());
			}
		}
		
		return permissionSet;
	}
}