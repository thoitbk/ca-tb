package com.catb.web.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.catb.bo.CommentBO;
import com.catb.bo.QACatalogBO;
import com.catb.common.PropertiesUtil;
import com.catb.model.Comment;
import com.catb.model.CommentStatus;
import com.catb.model.QACatalog;
import com.catb.web.tag.PageInfo;
import com.catb.web.util.Util;
import com.catb.web.validator.CreateCommentValidator;
import com.catb.web.viewmodel.CreateCommentViewModel;
import com.google.code.kaptcha.Constants;

@Controller
public class CommentController {
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private QACatalogBO qaCatalogBO;
	
	@Autowired
	private CreateCommentValidator createCommentValidator;
	
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
	
	@RequestMapping(value = "/dat-cau-hoi", method = RequestMethod.POST)
	public ModelAndView processCreateComment(
								@Valid @ModelAttribute("createCommentViewModel") CreateCommentViewModel createCommentViewModel, 
								BindingResult bindingResult, HttpServletRequest request, ModelMap model) {
		String validCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		createCommentValidator.setValidCode(validCode);
		createCommentValidator.validate(createCommentViewModel, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("askQuestion");
		} else {
			Comment comment = new Comment();
			comment.setAddress(createCommentViewModel.getAddress());
			comment.setCommentedDate(new Date());
			comment.setContent(createCommentViewModel.getContent());
			comment.setEmail(createCommentViewModel.getEmail());
			comment.setName(createCommentViewModel.getName());
			comment.setPhoneNumber(createCommentViewModel.getPhoneNumber());
			comment.setTitle(createCommentViewModel.getTitle());
			comment.setStatus(CommentStatus.PENDING_FOR_ANSWER.getStatus());
			
			commentBO.addComment(comment, createCommentViewModel.getQaCatalogId());
			
			request.getSession().setAttribute("msg", PropertiesUtil.getProperty("comment.created.successfully"));
			
			return new ModelAndView(new RedirectView(request.getContextPath() + "/dat-cau-hoi"));
		}
	}
	
	@RequiresPermissions(value = {"comment:manage"})
	@RequestMapping(value = "/cm/comment/show", method = RequestMethod.GET)
	public ModelAndView showComments(
			@RequestParam(value = "cId", required = false) Integer catalogId,
			@RequestParam(value = "t", required = false) String title,
			@RequestParam(value = "p", required = false, defaultValue = "1") Integer page, 
			ModelMap model, HttpServletRequest request) {
		Integer pageSize = Util.getPageSize(request);
		List<Comment> comments = commentBO.getComments(catalogId, title, page, pageSize);
		model.addAttribute("comments", comments);
		
		Map<String, String> params = new LinkedHashMap<String, String>();
		if (catalogId != null && catalogId >= 0) {
			params.put("cId", String.valueOf(catalogId));
		}
		if (title != null && !"".equals(title)) {
			params.put("t", title);
		}
		model.addAttribute("params", params);
		
		Long total = commentBO.countComments(catalogId, title);
		model.addAttribute("pageInfo", new PageInfo(total, page, pageSize));
		
		return new ModelAndView("cm/comment/show");
	}
}
