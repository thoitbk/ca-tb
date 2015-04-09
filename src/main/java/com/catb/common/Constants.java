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
	
	public static void load() {
		try {
			Configuration config = new PropertiesConfiguration("conf.properties");
			CM_MENU_CONFIG_FILE = config.getString("CM_MENU_CONFIG_FILE");
			COMMONINFO_CONFIG_FILE = config.getString("COMMONINFO_CONFIG_FILE");
			DISPLAY_LOCATION_CONFIG_FILE = config.getString("DISPLAY_LOCATION_CONFIG_FILE");
		} catch (ConfigurationException ex) {
			logger.error("Loading configuration failed: ", ex);
		}
	}
}
