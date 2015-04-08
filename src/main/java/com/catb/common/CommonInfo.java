package com.catb.common;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class CommonInfo {
	
	private String webTitle;
	
	@NotBlank
	@Range(min = 1, max = 100)
	private Integer recentNews;
	
	@NotBlank
	@Range(min = 1, max = 100)
	private Integer questionAnswer;
	
	@NotBlank
	@Range(min = 1, max = 100)
	private Integer tcCatalogs;
	
	@NotBlank
	@Range(min = 1, max = 100)
	private Integer sameSubjects;
	
	@NotBlank
	@Range(min = 1, max = 100)
	private Integer headlines;
	
	private String headlineCaption;
	private String imageCaption;
	private String videoCaption;
	private String audioCaption;
	private String detailsCaption;
	private String administrativeProcedures;
	private String administrativeProceduresInstruction;
	private String views;
	private String introduction;
	private String organizationalStructure;
	
	@NotBlank
	@Range(min = 1, max = 100)
	private Integer mostViewed;
	
	@NotBlank
	@Range(min = 1, max = 100)
	private Integer adAmount;
	
	@NotBlank
	@Range(min = 1, max = 100)
	private Integer newsInSameCatalog;
	
	@NotBlank
	@Range(min = 1, max = 100)
	private Integer newsInSearchResult;
	
	private String sameSubjectTitle;
	private String today;
	private String postedDate;
	private String author;
	private String print;
	private String homePage;
	private String document;
	private String legalDocument;
	private String goTop;
	private String duty;
	private String achievement;
	
	@NotBlank
	@Range(min = 1, max = 100)
	private Integer pageSize;
	
	public CommonInfo() {
		
	}

	public String getWebTitle() {
		return webTitle;
	}

	public void setWebTitle(String webTitle) {
		this.webTitle = webTitle;
	}

	public Integer getRecentNews() {
		return recentNews;
	}

	public void setRecentNews(Integer recentNews) {
		this.recentNews = recentNews;
	}

	public Integer getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(Integer questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public Integer getTcCatalogs() {
		return tcCatalogs;
	}

	public void setTcCatalogs(Integer tcCatalogs) {
		this.tcCatalogs = tcCatalogs;
	}

	public Integer getSameSubjects() {
		return sameSubjects;
	}

	public void setSameSubjects(Integer sameSubjects) {
		this.sameSubjects = sameSubjects;
	}

	public Integer getHeadlines() {
		return headlines;
	}

	public void setHeadlines(Integer headlines) {
		this.headlines = headlines;
	}

	public String getHeadlineCaption() {
		return headlineCaption;
	}

	public void setHeadlineCaption(String headlineCaption) {
		this.headlineCaption = headlineCaption;
	}

	public String getImageCaption() {
		return imageCaption;
	}

	public void setImageCaption(String imageCaption) {
		this.imageCaption = imageCaption;
	}

	public String getVideoCaption() {
		return videoCaption;
	}

	public void setVideoCaption(String videoCaption) {
		this.videoCaption = videoCaption;
	}

	public String getAudioCaption() {
		return audioCaption;
	}

	public void setAudioCaption(String audioCaption) {
		this.audioCaption = audioCaption;
	}

	public String getDetailsCaption() {
		return detailsCaption;
	}

	public void setDetailsCaption(String detailsCaption) {
		this.detailsCaption = detailsCaption;
	}

	public String getAdministrativeProcedures() {
		return administrativeProcedures;
	}

	public void setAdministrativeProcedures(String administrativeProcedures) {
		this.administrativeProcedures = administrativeProcedures;
	}

	public String getAdministrativeProceduresInstruction() {
		return administrativeProceduresInstruction;
	}

	public void setAdministrativeProceduresInstruction(
			String administrativeProceduresInstruction) {
		this.administrativeProceduresInstruction = administrativeProceduresInstruction;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getOrganizationalStructure() {
		return organizationalStructure;
	}

	public void setOrganizationalStructure(String organizationalStructure) {
		this.organizationalStructure = organizationalStructure;
	}

	public Integer getMostViewed() {
		return mostViewed;
	}

	public void setMostViewed(Integer mostViewed) {
		this.mostViewed = mostViewed;
	}

	public Integer getAdAmount() {
		return adAmount;
	}

	public void setAdAmount(Integer adAmount) {
		this.adAmount = adAmount;
	}

	public Integer getNewsInSameCatalog() {
		return newsInSameCatalog;
	}

	public void setNewsInSameCatalog(Integer newsInSameCatalog) {
		this.newsInSameCatalog = newsInSameCatalog;
	}

	public Integer getNewsInSearchResult() {
		return newsInSearchResult;
	}

	public void setNewsInSearchResult(Integer newsInSearchResult) {
		this.newsInSearchResult = newsInSearchResult;
	}

	public String getSameSubjectTitle() {
		return sameSubjectTitle;
	}

	public void setSameSubjectTitle(String sameSubjectTitle) {
		this.sameSubjectTitle = sameSubjectTitle;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getLegalDocument() {
		return legalDocument;
	}

	public void setLegalDocument(String legalDocument) {
		this.legalDocument = legalDocument;
	}

	public String getGoTop() {
		return goTop;
	}

	public void setGoTop(String goTop) {
		this.goTop = goTop;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
