package com.catb.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catb.bo.ImageCatalogBO;
import com.catb.dao.ImageCatalogDAO;
import com.catb.model.ImageCatalog;

@Service
public class ImageCatalogBOImpl implements ImageCatalogBO {
	
	@Autowired
	private ImageCatalogDAO imageCatalogDAO;
	
	@Transactional
	public void addImageCatalog(ImageCatalog imageCatalog) {
		imageCatalogDAO.addImageCatalog(imageCatalog);
	}
	
	@Transactional
	public List<ImageCatalog> getImageCatalogs() {
		return imageCatalogDAO.getImageCatalogs();
	}
	
	@Transactional
	public ImageCatalog getImageCatalogById(Integer id) {
		return imageCatalogDAO.getImageCatalogById(id);
	}
	
	@Transactional
	public void updateImageCatalog(ImageCatalog imageCatalog) {
		imageCatalogDAO.updateImageCatalog(imageCatalog);
	}
	
	@Transactional
	public void deleteImageCatalogs(Integer[] ids) {
		if (ids != null && ids.length > 0) {
			for (Integer id : ids) {
				imageCatalogDAO.deleteImageCatalog(id);
			}
		}
	}
}
