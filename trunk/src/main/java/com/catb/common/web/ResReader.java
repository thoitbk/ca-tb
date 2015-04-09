package com.catb.common.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.catb.common.CommonInfo;
import com.catb.common.exception.AppException;

public class ResReader {
	
	static Logger logger = Logger.getLogger(ResReader.class);
	
	@SuppressWarnings("rawtypes")
	public static List<String[]> readCMMenuConfig(String fileName) {
		SAXBuilder builder = new SAXBuilder();
		File file = new File(fileName);
		
		try {
			Document document = (Document) builder.build(file);
			Element rootNode = document.getRootElement();
			List menuNodes = rootNode.getChildren("menu");
			
			List<String[]> menuList = new ArrayList<String[]>();
			for (int i = 0; i < menuNodes.size(); i++) {
				Element menuNode = (Element) menuNodes.get(i);
				String menuId = menuNode.getAttributeValue("id");
				
				List menuItemNodes = menuNode.getChildren("menuItem");
				for (int j = 0; j < menuItemNodes.size(); j++) {
					Element menuItemNode = (Element) menuItemNodes.get(j);
					String menuItemId = menuItemNode.getAttributeValue("id");
					
					List linkNodes = menuItemNode.getChildren("link");
					for (int k = 0; k < linkNodes.size(); k++) {
						Element linkNode = (Element) linkNodes.get(k);
						String link = linkNode.getValue();
						menuList.add(new String[] {link, menuId, menuItemId});
					}
				}
			}
			
			return menuList;
		} catch (Exception ex) {
			logger.error("Reading menuConfig failed: ", ex);
			throw new AppException(ex);
		}
	}
	
	public static CommonInfo readCommonInfo(String fileName) {
		SAXBuilder builder = new SAXBuilder();
		File file = new File(fileName);
		
		CommonInfo commonInfo = new CommonInfo();
		try {
			Document document = (Document) builder.build(file);
			Element rootNode = document.getRootElement();
			
			commonInfo.setWebTitle(rootNode.getChildText("webTitle"));
			commonInfo.setRecentNews(Integer.parseInt(rootNode.getChildText("recentNews")));
			commonInfo.setQuestionAnswer(Integer.parseInt(rootNode.getChildText("questionAnswer")));
			commonInfo.setTcCatalogs(Integer.parseInt(rootNode.getChildText("tcCatalogs")));
			commonInfo.setSameSubjects(Integer.parseInt(rootNode.getChildText("sameSubjects")));
			commonInfo.setHeadlines(Integer.parseInt(rootNode.getChildText("headlines")));
			commonInfo.setHeadlineCaption(rootNode.getChildText("headlineCaption"));
			commonInfo.setImageCaption(rootNode.getChildText("imageCaption"));
			commonInfo.setVideoCaption(rootNode.getChildText("videoCaption"));
			commonInfo.setAudioCaption(rootNode.getChildText("audioCaption"));
			commonInfo.setDetailsCaption(rootNode.getChildText("detailsCaption"));
			commonInfo.setAdministrativeProcedures(rootNode.getChildText("administrativeProcedures"));
			commonInfo.setAdministrativeProceduresInstruction(rootNode.getChildText("administrativeProceduresInstruction"));
			commonInfo.setViews(rootNode.getChildText("views"));
			commonInfo.setIntroduction(rootNode.getChildText("introduction"));
			commonInfo.setOrganizationalStructure(rootNode.getChildText("organizationalStructure"));
			commonInfo.setMostViewed(Integer.parseInt(rootNode.getChildText("mostViewed")));
			commonInfo.setAdAmount(Integer.parseInt(rootNode.getChildText("adAmount")));
			commonInfo.setNewsInSameCatalog(Integer.parseInt(rootNode.getChildText("newsInSameCatalog")));
			commonInfo.setNewsInSearchResult(Integer.parseInt(rootNode.getChildText("newsInSearchResult")));
			commonInfo.setSameSubjectTitle(rootNode.getChildText("sameSubjectTitle"));
			commonInfo.setToday(rootNode.getChildText("today"));
			commonInfo.setPostedDate(rootNode.getChildText("postedDate"));
			commonInfo.setAuthor(rootNode.getChildText("author"));
			commonInfo.setPrint(rootNode.getChildText("print"));
			commonInfo.setHomePage(rootNode.getChildText("homePage"));
			commonInfo.setDocument(rootNode.getChildText("document"));
			commonInfo.setLegalDocument(rootNode.getChildText("legalDocument"));
			commonInfo.setGoTop(rootNode.getChildText("goTop"));
			commonInfo.setDuty(rootNode.getChildText("duty"));
			commonInfo.setAchievement(rootNode.getChildText("achievement"));
			commonInfo.setPageSize(Integer.parseInt(rootNode.getChildText("pageSize")));
		} catch (Exception ex) {
			logger.error("Reading commonInfo failed: ", ex);
			throw new AppException(ex);
		}
		
		return commonInfo;
	}
}
