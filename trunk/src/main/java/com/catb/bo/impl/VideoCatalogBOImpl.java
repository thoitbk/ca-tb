package com.catb.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catb.bo.VideoCatalogBO;
import com.catb.dao.VideoCatalogDAO;
import com.catb.model.VideoCatalog;

@Service
public class VideoCatalogBOImpl implements VideoCatalogBO {
	
	@Autowired
	private VideoCatalogDAO videoCatalogDAO;
	
	@Transactional
	public void addVideoCatalog(VideoCatalog videoCatalog) {
		videoCatalogDAO.addVideoCatalog(videoCatalog);
	}
	
	@Transactional
	public List<VideoCatalog> getVideoCatalogs() {
		return videoCatalogDAO.getVideoCatalogs();
	}
	
	@Transactional
	public VideoCatalog getVideoCatalogById(Integer id) {
		return videoCatalogDAO.getVideoCatalogById(id);
	}
	
	@Transactional
	public void updateVideoCatalog(VideoCatalog videoCatalog) {
		videoCatalogDAO.updateVideoCatalog(videoCatalog);
	}
	
	@Transactional
	public void deleteVideoCatalogs(Integer[] ids) {
		if (ids != null && ids.length > 0) {
			for (Integer id : ids) {
				videoCatalogDAO.deleteVideoCatalog(id);
			}
		}
	}
}
