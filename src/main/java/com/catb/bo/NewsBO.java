package com.catb.bo;

import com.catb.model.News;
import com.catb.model.NewsContent;

public interface NewsBO {
	
	public void addNews(News news, NewsContent newsContent, Integer newsCatalogId);
}
