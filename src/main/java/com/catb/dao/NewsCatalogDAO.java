package com.catb.dao;

import java.util.List;

import com.catb.model.NewsCatalog;

public interface NewsCatalogDAO {
	
	public List<NewsCatalog> getNewsCatalog(String displayLocation, Integer parent);
	public void addNewsCatalog(NewsCatalog newsCatalog);
	public NewsCatalog getNewsCatalogById(Integer newsCatalogId);
	public void updateNewsCatalog(NewsCatalog newsCatalog);
	public void deleteNewsCatalog(Integer id);
}
