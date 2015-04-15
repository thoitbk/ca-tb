package com.catb.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.catb.dao.NewsDAO;
import com.catb.model.News;
import com.catb.model.NewsContent;

@Repository
public class NewsDAOImpl implements NewsDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addNews(News news) {
		Session session = sessionFactory.getCurrentSession();
		session.save(news);
	}

	public void addNewsContent(NewsContent newsContent) {
		Session session = sessionFactory.getCurrentSession();
		session.save(newsContent);
	}
}
