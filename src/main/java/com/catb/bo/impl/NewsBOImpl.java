package com.catb.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catb.bo.NewsBO;
import com.catb.dao.NewsDAO;
import com.catb.model.News;
import com.catb.model.NewsContent;

@Service
public class NewsBOImpl implements NewsBO {
	
	@Autowired
	private NewsDAO newsDAO;
	
	@Transactional
	public void addNews(News news, NewsContent newsContent) {
		newsDAO.addNewsContent(newsContent);
		news.setNewsContent(newsContent);
		newsDAO.addNews(news);
	}
}
