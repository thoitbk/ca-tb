package com.catb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.catb.dao.ImageDAO;
import com.catb.model.Image;

@Repository
public class ImageDAOImpl implements ImageDAO {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Image> getImages(Integer imageCatalogId, Integer page, Integer pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = buildCriteria(session, imageCatalogId);
		
		criteria.setFirstResult((page - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		
		criteria.addOrder(Order.desc("id"));
		
		return (List<Image>) criteria.list();
	}
	
	private Criteria buildCriteria(Session session, Integer imageCatalogId) {
		Criteria criteria = session.createCriteria(Image.class, "image");
		criteria.createAlias("image.imageCatalog", "imageCatalog");
		criteria.setFetchMode("imageCatalog", FetchMode.JOIN);
		
		if (imageCatalogId != null && imageCatalogId >= 0) {
			criteria.add(Restrictions.eq("imageCatalog.id", imageCatalogId));
		}
		
		return criteria;
	}

	public Long countImages(Integer imageCatalogId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = buildCriteria(session, imageCatalogId);
		criteria.setProjection(Projections.rowCount());
		
		return (Long) criteria.uniqueResult();
	}

	public void addImage(Image image) {
		Session session = sessionFactory.getCurrentSession();
		session.save(image);
	}
}
