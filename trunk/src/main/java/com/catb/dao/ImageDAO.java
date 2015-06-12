package com.catb.dao;

import java.util.List;

import com.catb.model.Image;

public interface ImageDAO {
	
	public List<Image> getImages(Integer imageCatalogId, Integer page, Integer pageSize);
	public Long countImages(Integer imageCatalogId);
}
