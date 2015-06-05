package com.catb.common;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class Constants {
	
	static Logger logger = Logger.getLogger(Constants.class);
	
	// Location of resources config files
	public static String CM_MENU_CONFIG_FILE;
	public static String COMMONINFO_CONFIG_FILE;
	public static String DISPLAY_LOCATION_CONFIG_FILE;
	public static String NEWS_STATUSES_CONFIG_FILE;
	// Logical path of directory storing uploaded images
	public static String NEWS_IMAGE_LOCATION;
	// Relative path of uploaded images
	public static String NEWS_IMAGE_PATH;
	// Number of page links generated
	public static Integer NUMBER_OF_PAGE_LINK;
	public static String ADMINISTRATIVE_PROCEDURE_LOCATION;
	public static String DOCUMENT_LOCATION;
	public static final Integer MAX_SQ_NUMBER = 2000000000;
	public static final String NEWS_PREFIX = "/tin-tuc";
	public static final String HOMEPAGE = "Trang chủ";
	
	public static void load() {
		try {
			Configuration config = new PropertiesConfiguration("conf.properties");
			CM_MENU_CONFIG_FILE = config.getString("CM_MENU_CONFIG_FILE");
			COMMONINFO_CONFIG_FILE = config.getString("COMMONINFO_CONFIG_FILE");
			DISPLAY_LOCATION_CONFIG_FILE = config.getString("DISPLAY_LOCATION_CONFIG_FILE");
			NEWS_STATUSES_CONFIG_FILE = config.getString("NEWS_STATUSES_CONFIG_FILE");
			NEWS_IMAGE_LOCATION = config.getString("NEWS_IMAGE_LOCATION");
			NEWS_IMAGE_PATH = config.getString("NEWS_IMAGE_PATH");
			NUMBER_OF_PAGE_LINK = config.getInt("NUMBER_OF_PAGE_LINK");
			ADMINISTRATIVE_PROCEDURE_LOCATION = config.getString("ADMINISTRATIVE_PROCEDURE_LOCATION");
			DOCUMENT_LOCATION = config.getString("DOCUMENT_LOCATION");
		} catch (ConfigurationException ex) {
			logger.error("Loading configuration failed: ", ex);
		}
	}
}
