package com.catb.web.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.catb.bo.NewsBO;
import com.catb.bo.NewsCatalogBO;
import com.catb.common.PropertiesUtil;
import com.catb.model.CommonInfo;
import com.catb.model.News;
import com.catb.model.NewsCatalog;
import com.catb.web.component.MenuLoader;

@Controller
public class ComponentController {
	
	@Autowired
	private NewsCatalogBO newsCatalogBO;
	
	@Autowired
	private NewsBO newsBO;
	
	@RequestMapping(value = "/rightBar", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getRightBar(ModelMap model, HttpServletRequest request) {
		CommonInfo commonInfo = (CommonInfo) request.getServletContext().getAttribute("COMMONINFO");
		Integer rightTopSize = 10;
		Integer rightCenterSize = 10;
		if (commonInfo != null && commonInfo.getRightTopSize() != null) {
			rightTopSize = commonInfo.getRightTopSize();
		}
		if (commonInfo != null && commonInfo.getRightCenterSize() != null) {
			rightCenterSize = commonInfo.getRightCenterSize();
		}
		
		List<NewsCatalog> rightTopNewsCatalogs = newsCatalogBO.getNewsCatalogs(
				MenuLoader.DisplayLocation.RIGHT_TOP.getPosition(), null, 0, true, rightTopSize);
		
		NewsCatalog comment = new NewsCatalog(PropertiesUtil.getProperty("comment.name"), PropertiesUtil.getProperty("comment.url"));
		NewsCatalog administrativeProcedures = new NewsCatalog(PropertiesUtil.getProperty("administrativeProcedures.name"), PropertiesUtil.getProperty("administrativeProcedures.url"));
		NewsCatalog criminalDenouncement = new NewsCatalog(PropertiesUtil.getProperty("criminalDenouncement.name"), PropertiesUtil.getProperty("criminalDenouncement.url"));
		NewsCatalog document = new NewsCatalog(PropertiesUtil.getProperty("document.name"), PropertiesUtil.getProperty("document.url"));
		rightTopNewsCatalogs.addAll(Arrays.asList(comment, administrativeProcedures, criminalDenouncement, document));
		
		model.addAttribute("rightTopNewsCatalogs", rightTopNewsCatalogs);
		
		List<News> rightCenterNewses = newsBO.getRightCenterNews(rightCenterSize);
		model.addAttribute("rightCenterNewses", rightCenterNewses);
		
		return new ModelAndView("rightBar");
	}
}
