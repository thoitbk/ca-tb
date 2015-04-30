package com.catb.common.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.catb.bo.NewsCatalogBO;

//@Component
public class MenuLoader {
	
	@Autowired
	private NewsCatalogBO newsCatalogBO;
	
	public void print() {
		newsCatalogBO.getNewsCatalogById(1);
		System.out.println("OK");
	}
}
