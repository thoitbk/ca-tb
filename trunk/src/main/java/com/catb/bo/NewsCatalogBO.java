package com.catb.bo;

import java.util.List;

import com.catb.model.NewsCatalog;

public interface NewsCatalogBO {
	
	public List<NewsCatalog> getNewsCatalog(String displayLocation, Integer parent);
	public void addNewsCatalog(NewsCatalog newsCatalog);
	public NewsCatalog getNewsCatalogById(Integer newsCatalogId);
}
