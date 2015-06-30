package com.catb.web.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.catb.bo.CommentBO;
import com.catb.bo.QACatalogBO;
import com.catb.model.QACatalog;
import com.catb.web.viewmodel.CreateCommentViewModel;

@Controller
public class CommentController {
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private QACatalogBO qaCatalogBO;
	
	@ModelAttribute("qaCatalogMap")
	public Map<Integer, String> populateQACatalogs() {
		List<QACatalog> qaCatalogs = qaCatalogBO.getQACatalogs();
		Map<Integer, String> qaCatalogMap = new LinkedHashMap<Integer, String>();
		for (QACatalog qaCatalog : qaCatalogs) {
			qaCatalogMap.put(qaCatalog.getId(), qaCatalog.getName());
		}
		
		return qaCatalogMap;
	}
	
	@RequestMapping(value = "/dat-cau-hoi", method = RequestMethod.GET)
	public ModelAndView showCreateComment(ModelMap model) {
		CreateCommentViewModel createCommentViewModel = new CreateCommentViewModel();
		model.addAttribute("createCommentViewModel", createCommentViewModel);
		
		return new ModelAndView("askQuestion");
	}
}
