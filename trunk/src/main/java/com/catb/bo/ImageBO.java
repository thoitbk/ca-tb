package com.catb.bo;

import java.util.List;

import com.catb.model.Image;

public interface ImageBO {
	
	public List<Image> getImages(Integer imageCatalogId, Integer page, Integer pageSize);
	public Long countImages(Integer imageCatalogId);
}
