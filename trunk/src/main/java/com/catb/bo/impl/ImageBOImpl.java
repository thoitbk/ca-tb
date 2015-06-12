package com.catb.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catb.bo.ImageBO;
import com.catb.dao.ImageDAO;
import com.catb.model.Image;

@Service
public class ImageBOImpl implements ImageBO {
	
	@Autowired
	private ImageDAO imageDAO;
	
	@Transactional
	public List<Image> getImages(Integer imageCatalogId, Integer page, Integer pageSize) {
		return imageDAO.getImages(imageCatalogId, page, pageSize);
	}
	
	@Transactional
	public Long countImages(Integer imageCatalogId) {
		return imageDAO.countImages(imageCatalogId);
	}
}
