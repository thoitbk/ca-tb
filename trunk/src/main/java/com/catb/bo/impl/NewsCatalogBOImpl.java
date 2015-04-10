package com.catb.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catb.bo.NewsCatalogBO;
import com.catb.dao.NewsCatalogDAO;
import com.catb.model.NewsCatalog;

@Service
public class NewsCatalogBOImpl implements NewsCatalogBO {
	
	@Autowired
	private NewsCatalogDAO newsCatalogDAO;
	
	@Transactional
	public List<NewsCatalog> getNewsCatalog(String displayLocation, Integer parent) {
		return newsCatalogDAO.getNewsCatalog(displayLocation, parent);
	}
	
	@Transactional
	public void addNewsCatalog(NewsCatalog newsCatalog) {
		newsCatalogDAO.addNewsCatalog(newsCatalog);
	}
	
	@Transactional
	public NewsCatalog getNewsCatalogById(Integer newsCatalogId) {
		return newsCatalogDAO.getNewsCatalogById(newsCatalogId);
	}
}
