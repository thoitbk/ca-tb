package com.catb.common;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class Constants {
	
	static Logger logger = Logger.getLogger(Constants.class);
	
	public static String CM_MENU_CONFIG_FILE;
	public static String COMMONINFO_CONFIG_FILE;
	public static String DISPLAY_LOCATION_CONFIG_FILE;
	public static String NEWS_IMAGE_LOCATION;
	public static String NEWS_IMAGE_PATH;
	
	public static void load() {
		try {
			Configuration config = new PropertiesConfiguration("conf.properties");
			CM_MENU_CONFIG_FILE = config.getString("CM_MENU_CONFIG_FILE");
			COMMONINFO_CONFIG_FILE = config.getString("COMMONINFO_CONFIG_FILE");
			DISPLAY_LOCATION_CONFIG_FILE = config.getString("DISPLAY_LOCATION_CONFIG_FILE");
			NEWS_IMAGE_LOCATION = config.getString("NEWS_IMAGE_LOCATION");
			NEWS_IMAGE_PATH = config.getString("NEWS_IMAGE_PATH");
		} catch (ConfigurationException ex) {
			logger.error("Loading configuration failed: ", ex);
		}
	}
}
