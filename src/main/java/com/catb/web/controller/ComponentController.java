package com.catb.web.controller;

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
		
		List<NewsCatalog> rightTopNewsCatalogs = newsCatalogBO.getNewsCatalogs(MenuLoader.DisplayLocation.RIGHT_TOP.getPosition(), null, 0, true, rightTopSize);
		model.addAttribute("rightTopNewsCatalogs", rightTopNewsCatalogs);
		
		//List<News> rightCenterNews = newsBO.getNewsByNewsesCatalogId(newsCatalogId, size)
		
		return new ModelAndView("rightBar");
	}
}
