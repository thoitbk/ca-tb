package com.catb.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.catb.dao.CommentDAO;
import com.catb.model.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addComment(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.save(comment);
	}
}
