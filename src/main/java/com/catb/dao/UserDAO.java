package com.catb.dao;

import java.util.List;

import com.catb.model.Permission;
import com.catb.model.User;

public interface UserDAO {
	
	public List<User> getUsers();
	public void addUser(User user);
	public User getUserByUsername(String username);
	public List<Permission> getPermissionsByRole(String roleName);
}
