package com.catb.dao;

import com.catb.model.News;
import com.catb.model.NewsContent;

public interface NewsDAO {
	
	public void addNews(News news);
	public void addNewsContent(NewsContent newsContent);
}
