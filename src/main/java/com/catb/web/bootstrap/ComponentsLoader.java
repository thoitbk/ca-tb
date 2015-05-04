package com.catb.web.bootstrap;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.catb.bo.LinkCatalogBO;
import com.catb.common.Constants;
import com.catb.dao.statics.ResReader;
import com.catb.web.component.MenuLoader;

@Component
public class ComponentsLoader {
	
	static Logger logger = Logger.getLogger(ComponentsLoader.class);
	
	@Autowired
	private MenuLoader menuLoader;
	
	@Autowired
	private LinkCatalogBO linkCatalogBO;
	
	private ServletContext context;

	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	public void load() {
		if (context == null) {
			logger.error("context cannot be null");
			throw new Error("Context is null");
		} else {
			logger.info("Loading components...");
			
			logger.info("Loading resources from conf.properties...");
			Constants.load();
			
			logger.info("Reading xml resources from res...");
			context.setAttribute("CM_MENU", ResReader.readCMMenuConfig(context.getRealPath(Constants.CM_MENU_CONFIG_FILE)));
			context.setAttribute("COMMONINFO", ResReader.readCommonInfo(context.getRealPath(Constants.COMMONINFO_CONFIG_FILE)));
			context.setAttribute("DISPLAY_LOCATION", ResReader.readDisplayLocation(context.getRealPath(Constants.DISPLAY_LOCATION_CONFIG_FILE)));
			context.setAttribute("NEWS_STATUSES", ResReader.readNewsStatuses(context.getRealPath(Constants.NEWS_STATUSES_CONFIG_FILE)));
			
			logger.info("Save context path to context variable...");
			context.setAttribute("ct", context.getContextPath());
			
			logger.info("Loading menu hierarchy...");
			context.setAttribute("MENU_HIERARCHY", menuLoader.loadMenuTree());
			
			logger.info("Loading link list");
			context.setAttribute("LINK_LIST", linkCatalogBO.getLinkCatalogs());
		}
	}
}
