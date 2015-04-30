package com.catb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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
		criteria.addOrder(Order.desc("id"));
		
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

	public void updateNewsCatalog(NewsCatalog newsCatalog) {
		Session session = sessionFactory.getCurrentSession();
		session.update(newsCatalog);
	}

	public void deleteNewsCatalog(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		String delete = "DELETE FROM NewsCatalog " + 
						"WHERE id = :id";
		Query query = session.createQuery(delete);
		query.setParameter("id", id);
		
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<NewsCatalog> getNewsCatalogs(String displayLocation,
			Integer parentId, Integer childLevel, Boolean display) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(NewsCatalog.class, "newsCatalog");
		
		if (displayLocation != null && !"".equals(displayLocation.trim())) {
			criteria.add(Restrictions.eq("displayLocation", displayLocation.trim()));
		}
		if (parentId != null && parentId >= 0) {
			criteria.add(Restrictions.eq("parentId", parentId));
		}
		if (childLevel != null && childLevel >= 0) {
			criteria.add(Restrictions.eq("childLevel", childLevel));
		}
		if (display != null) {
			criteria.add(Restrictions.eq("display", display));
		}
		
		criteria.addOrder(Order.asc("sqNumber"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<NewsCatalog>) criteria.list();
	}
}
