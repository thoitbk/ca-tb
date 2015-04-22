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

import com.catb.dao.NewsDAO;
import com.catb.model.News;
import com.catb.model.NewsContent;
import com.catb.vo.SearchNewsVO;

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

	@SuppressWarnings("unchecked")
	public List<News> getNews(SearchNewsVO searchNewsVO, Integer page, Integer pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = buildCriteria(session, searchNewsVO);
		
		criteria.setFirstResult((page - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		
		criteria.addOrder(Order.desc("sqNumber"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<News>) criteria.list();
	}

	public Long countNews(SearchNewsVO searchNewsVO) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = buildCriteria(session, searchNewsVO);
		criteria.setProjection(Projections.rowCount());
		
		return (Long) criteria.uniqueResult();
	}
	
	private Criteria buildCriteria(Session session, SearchNewsVO searchNewsVO) {
		Criteria criteria = session.createCriteria(News.class, "news");
		criteria.createAlias("news.newsCatalog", "newsCatalog");
		criteria.setFetchMode("newsCatalog", FetchMode.JOIN);
		
		if (searchNewsVO.getNewsCatalogId() != null) {
			criteria.add(Restrictions.eq("newsCatalog.id", searchNewsVO.getNewsCatalogId()));
		}
		if (searchNewsVO.getNewsStatus() != null) {
			criteria.add(Restrictions.eq("status", searchNewsVO.getNewsStatus()));
		}
		if (searchNewsVO.getHotNews() != null) {
			criteria.add(Restrictions.eq("hotNews", searchNewsVO.getHotNews()));
		}
		if (searchNewsVO.getAuthor() != null) {
			criteria.add(Restrictions.like("author", "%" + searchNewsVO.getAuthor() + "%"));
		}
		if (searchNewsVO.getTitle() != null) {
			criteria.add(Restrictions.like("title", "%" + searchNewsVO.getTitle() + "%"));
		}
		if (searchNewsVO.getFrom() != null) {
			criteria.add(Restrictions.ge("postedDate", searchNewsVO.getFrom()));
		}
		if (searchNewsVO.getTo() != null) {
			criteria.add(Restrictions.le("postedDate", searchNewsVO.getTo()));
		}
		
		return criteria;
	}
}
