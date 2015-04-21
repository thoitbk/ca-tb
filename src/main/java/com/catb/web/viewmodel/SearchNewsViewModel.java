package com.catb.web.viewmodel;

public class SearchNewsViewModel {
	
	private Integer newsCatalogId;
	private Integer newsStatus;
	private Boolean hotNews;
	private String author;
	private String title;
	private String from;
	private String to;
	
	public SearchNewsViewModel() {
		
	}

	public SearchNewsViewModel(Integer newsCatalogId, Integer newsStatus,
			Boolean hotNews, String author, String title, String from, String to) {
		this.newsCatalogId = newsCatalogId;
		this.newsStatus = newsStatus;
		this.hotNews = hotNews;
		this.author = author;
		this.title = title;
		this.from = from;
		this.to = to;
	}

	public Integer getNewsCatalogId() {
		return newsCatalogId;
	}

	public void setNewsCatalogId(Integer newsCatalogId) {
		this.newsCatalogId = newsCatalogId;
	}

	public Integer getNewsStatus() {
		return newsStatus;
	}

	public void setNewsStatus(Integer newsStatus) {
		this.newsStatus = newsStatus;
	}

	public Boolean getHotNews() {
		return hotNews;
	}

	public void setHotNews(Boolean hotNews) {
		this.hotNews = hotNews;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
