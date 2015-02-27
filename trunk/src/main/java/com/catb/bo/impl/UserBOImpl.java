package com.catb.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catb.bo.UserBO;
import com.catb.dao.UserDAO;
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
}
