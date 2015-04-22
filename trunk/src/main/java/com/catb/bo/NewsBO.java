package com.catb.bo;

import java.util.List;

import com.catb.model.News;
import com.catb.model.NewsContent;
import com.catb.vo.SearchNewsVO;

public interface NewsBO {
	
	public void addNews(News news, NewsContent newsContent, Integer newsCatalogId);
	public List<News> getNews(SearchNewsVO searchNewsVO, Integer page, Integer pageSize);
	public Long countNews(SearchNewsVO searchNewsVO);
}
