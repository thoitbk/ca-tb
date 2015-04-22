package com.catb.dao;

import java.util.List;

import com.catb.model.News;
import com.catb.model.NewsContent;
import com.catb.vo.SearchNewsVO;

public interface NewsDAO {
	
	public void addNews(News news);
	public void addNewsContent(NewsContent newsContent);
	public List<News> getNews(SearchNewsVO searchNewsVO, Integer page, Integer pageSize);
	public Long countNews(SearchNewsVO searchNewsVO);
}
