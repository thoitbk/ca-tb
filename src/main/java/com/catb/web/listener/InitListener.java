package com.catb.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.catb.common.Constants;
import com.catb.common.web.ResReader;

public class InitListener implements ServletContextListener {
	
	static Logger logger = Logger.getLogger(InitListener.class);
	
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("------------------------- Stop web application -------------------------");
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
		
		logger.info("\n------------------------- Complete starting web application successfully -------------------------");
	}
}