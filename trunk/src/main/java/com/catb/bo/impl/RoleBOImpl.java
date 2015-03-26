package com.catb.bo.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catb.bo.RoleBO;
import com.catb.dao.RoleDAO;
import com.catb.model.Role;

@Service
public class RoleBOImpl implements RoleBO {
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Transactional
	public List<Role> getRoles() {
		return roleDAO.getRoles();
	}
	
	@Transactional
	public void addRole(Role role) {
		roleDAO.addRole(role);
	}
	
	@Transactional
	public Role getRoleById(Integer id) {
		return roleDAO.getRoleById(id);
	}
	
	@Transactional
	public Role getRoleByName(String name) {
		return roleDAO.getRoleByName(name);
	}
	
	@Transactional
	public void updateRole(Role role) {
		if (role.getId() != null) {
			Role r = roleDAO.getRoleById(role.getId());
			if (r != null) {
				r.setName(role.getName());
				r.setDescription(role.getDescription());
				
				roleDAO.updateRole(r);
			}
		}
	}
	
	@Transactional
	public void deleteRoles(Integer[] ids) {
		if (ids != null && ids.length > 0) {
			for (Integer id : ids) {
				roleDAO.deleteRole(id);
			}
		}
	}
}
