package com.catb.bo;

import java.util.List;

import com.catb.model.User;

public interface UserBO {
	
	public List<User> getUsers();
	public void addUser(User user);
}
