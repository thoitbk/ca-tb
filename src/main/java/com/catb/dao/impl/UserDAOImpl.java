package com.catb.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.catb.dao.UserDAO;
import com.catb.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM User";
		List<User> users = session.createQuery(query).list();
		
		return users;
	}

	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
}
