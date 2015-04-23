package com.catb.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catb.bo.NewsBO;
import com.catb.dao.NewsCatalogDAO;
import com.catb.dao.NewsDAO;
import com.catb.model.News;
import com.catb.model.NewsCatalog;
import com.catb.model.NewsContent;
import com.catb.vo.SearchNewsVO;

@Service
public class NewsBOImpl implements NewsBO {
	
	@Autowired
	private NewsDAO newsDAO;
	
	@Autowired
	private NewsCatalogDAO newsCatalogDAO;
	
	@Transactional
	public void addNews(News news, NewsContent newsContent, Integer newsCatalogId) {
		NewsCatalog newsCatalog = newsCatalogDAO.getNewsCatalogById(newsCatalogId);
		if (newsCatalog != null) {
			newsCatalog.getNewses().add(news);
			news.setNewsCatalog(newsCatalog);
			
			newsDAO.addNewsContent(newsContent);
			news.setNewsContent(newsContent);
			newsDAO.addNews(news);
		}
	}
	
	@Transactional
	public List<News> getNews(SearchNewsVO searchNewsVO, Integer page, Integer pageSize) {
		return newsDAO.getNews(searchNewsVO, page, pageSize);
	}
	
	@Transactional
	public Long countNews(SearchNewsVO searchNewsVO) {
		return newsDAO.countNews(searchNewsVO);
	}
	
	@Transactional
	public News getNewsById(Integer id) {
		return newsDAO.getNewsById(id);
	}
	
	@Transactional
	public News fetchNewsById(Integer id) {
		return newsDAO.fetchNewsById(id);
	}
	
	@Transactional
	public void updateNews(News news, String content, Integer newsCatalogId) {
		News n = newsDAO.getNewsById(news.getId());
		if (n != null) {
			n.setAuthor(news.getAuthor());
			n.setHotNews(news.getHotNews());
			n.setImage(news.getImage());
			n.setPostedDate(news.getPostedDate());
			n.setSqNumber(news.getSqNumber());
			n.setSummary(news.getSummary());
			n.setTitle(news.getTitle());
			
			NewsCatalog newsCatalog = newsCatalogDAO.getNewsCatalogById(newsCatalogId);
			n.setNewsCatalog(newsCatalog);
			
			newsDAO.updateNews(n);
			
			NewsContent newsContent = n.getNewsContent();
			if (newsContent != null) {
				newsContent.setContent(content);
				
				newsDAO.updateNewsContent(newsContent);
			}
		}
	}
}