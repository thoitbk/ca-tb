package com.catb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.catb.dao.NewsCatalogDAO;
import com.catb.model.NewsCatalog;

@Repository
public class NewsCatalogDAOImpl implements NewsCatalogDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<NewsCatalog> getNewsCatalog(String displayLocation, Integer parent) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(NewsCatalog.class, "newsCatalog");
		
		if (displayLocation != null && !"".equals(displayLocation.trim())) {
			criteria.add(Restrictions.eq("displayLocation", displayLocation.trim()));
		}
		if (parent != null && parent >= 0) {
			criteria.add(Restrictions.eq("parentId", parent));
		}
		
		criteria.addOrder(Order.asc("sqNumber"));
		criteria.addOrder(Order.asc("id"));
		
		return (List<NewsCatalog>) criteria.list();
	}

	public void addNewsCatalog(NewsCatalog newsCatalog) {
		Session session = sessionFactory.getCurrentSession();
		session.save(newsCatalog);
	}

	public NewsCatalog getNewsCatalogById(Integer newsCatalogId) {
		Session session = sessionFactory.getCurrentSession();
		return (NewsCatalog) session.get(NewsCatalog.class, newsCatalogId);
	}
}
