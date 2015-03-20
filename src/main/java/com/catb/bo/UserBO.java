package com.catb.bo;

import java.util.List;
import java.util.Set;

import com.catb.model.Role;
import com.catb.model.User;

public interface UserBO {
	
	public List<User> getUsers();
	public void addUser(User user);
	public User getUserByUsername(String username);
	public Set<Role> getRolesForUser(String username);
	public Set<String> getRoleNamesForUser(String username);
	public Set<String> getPermissions(String roleName);
	public void addUser(User user, Integer positionId, Integer departmentId);
}
