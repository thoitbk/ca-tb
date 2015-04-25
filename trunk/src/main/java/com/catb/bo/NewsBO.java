package com.catb.bo;

import java.util.List;

import com.catb.model.News;
import com.catb.model.NewsContent;
import com.catb.model.NewsStatus;
import com.catb.vo.SearchNewsVO;

public interface NewsBO {
	
	public void addNews(News news, NewsContent newsContent, Integer newsCatalogId);
	public List<News> getNews(SearchNewsVO searchNewsVO, Integer page, Integer pageSize);
	public Long countNews(SearchNewsVO searchNewsVO);
	public News getNewsById(Integer id);
	public News fetchNewsById(Integer id);
	public void updateNews(News news, String content, Integer newsCatalogId);
	public void deleteNewses(Integer[] ids);
	public void updateNewsStatus(NewsStatus newsStatus, Integer newsId);
	public void updateNewsesStatus(NewsStatus newsStatus, Integer[] newsIds);
	public void updateHotNews(Boolean hotNews, Integer newsId);
}
