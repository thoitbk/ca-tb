package com.catb.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.catb.common.Constants;
import com.catb.common.web.MenuLoader;
import com.catb.common.web.ResReader;

public class InitListener implements ServletContextListener {
	
	static Logger logger = Logger.getLogger(InitListener.class);
	
	@Autowired
	private MenuLoader menuLoader;
	
	public void setMenuLoader(MenuLoader menuLoader) {
		this.menuLoader = menuLoader;
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("------------------------- Begin stopping web application -------------------------");
		// Destroy cache manager instance
//		DefaultSecurityManager securityManager = (DefaultSecurityManager) SecurityUtils.getSecurityManager();
//		EhCacheManager cacheManager = (EhCacheManager) securityManager.getCacheManager();
//		cacheManager.destroy();
		logger.info("------------------------- Stop web application successfully -------------------------");
	}

	public void contextInitialized(ServletContextEvent event) {
		PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));
		logger.info("\n------------------------- Begin starting web application -------------------------");
		
		logger.info("Loading resources from conf.properties...");
		Constants.load();
		logger.info("Reading xml resources from res...");
		ServletContext context = event.getServletContext();
		context.setAttribute("CM_MENU", ResReader.readCMMenuConfig(context.getRealPath(Constants.CM_MENU_CONFIG_FILE)));
		context.setAttribute("COMMONINFO", ResReader.readCommonInfo(context.getRealPath(Constants.COMMONINFO_CONFIG_FILE)));
		context.setAttribute("DISPLAY_LOCATION", ResReader.readDisplayLocation(context.getRealPath(Constants.DISPLAY_LOCATION_CONFIG_FILE)));
		context.setAttribute("NEWS_STATUSES", ResReader.readNewsStatuses(context.getRealPath(Constants.NEWS_STATUSES_CONFIG_FILE)));
		
		context.setAttribute("ct", event.getServletContext().getContextPath());
		WebApplicationContextUtils.getRequiredWebApplicationContext(context).getAutowireCapableBeanFactory().autowireBean(this);;
		menuLoader.print();
		
		logger.info("\n------------------------- Complete starting web application successfully -------------------------");
	}
}
