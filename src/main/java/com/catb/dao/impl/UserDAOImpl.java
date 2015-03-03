package com.catb.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.catb.dao.UserDAO;
import com.catb.model.Permission;
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

	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		String select = "FROM User WHERE username = :username";
		Query query = session.createQuery(select);
		query.setParameter("username", username);
		List<User> users = (List<User>) query.list();
		
		return users.size() > 0 ? users.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public List<Permission> getPermissionsByRole(String roleName) {
		Session session = sessionFactory.getCurrentSession();
		String select = "SELECT p FROM Role AS r INNER JOIN r.permissions p WHERE r.name = :roleName";
		Query query = session.createQuery(select);
		query.setParameter("roleName", roleName);
		List<Permission> permissions = (List<Permission>) query.list();
		
		return permissions;
	}
}
