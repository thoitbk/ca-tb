package com.catb.common.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ResReader {
	
	static Logger logger = Logger.getLogger(ResReader.class);
	
	@SuppressWarnings("rawtypes")
	public static Map<String, CMMenu> readCMMenuConfig(String fileName) {
		SAXBuilder builder = new SAXBuilder();
		File file = new File(fileName);
		
		try {
			Document document = (Document) builder.build(file);
			Element rootNode = document.getRootElement();
			List menuNodes = rootNode.getChildren("menu");
			
			CMMenu cmMenu = null;
			Map<String, CMMenu> cmMenuMap = new HashMap<String, CMMenu>();
			for (int i = 0; i < menuNodes.size(); i++) {
				Element menuNode = (Element) menuNodes.get(i);
				String menuId = menuNode.getAttributeValue("id");
				
				List menuItemNodes = menuNode.getChildren("menuItem");
				for (int j = 0; j < menuItemNodes.size(); j++) {
					Element menuItemNode = (Element) menuItemNodes.get(j);
					String menuItemId = menuItemNode.getAttributeValue("id");
					
					cmMenu = new CMMenu(menuId, menuItemId);
					List linkNodes = menuItemNode.getChildren("link");
					
					for (int k = 0; k < linkNodes.size(); k++) {
						Element linkNode = (Element) linkNodes.get(k);
						String link = linkNode.getChildTextTrim("link");
						
						if (link != null && link.length() > 0) {
							cmMenuMap.put(link, cmMenu);
						}
					}
				}
			}
			
			return cmMenuMap;
		} catch (Exception ex) {
			logger.error("Reading menuConfig failed: ", ex);
			return null;
		}
	}
}
