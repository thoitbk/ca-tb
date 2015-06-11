package com.catb.bo;

import java.util.List;

import com.catb.model.VideoCatalog;

public interface VideoCatalogBO {
	
	public void addVideoCatalog(VideoCatalog videoCatalog);
	public List<VideoCatalog> getVideoCatalogs();
	public VideoCatalog getVideoCatalogById(Integer id);
	public void updateVideoCatalog(VideoCatalog videoCatalog);
	public void deleteVideoCatalogs(Integer[] ids);
}
