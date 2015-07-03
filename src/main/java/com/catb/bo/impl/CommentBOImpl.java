package com.catb.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catb.bo.CommentBO;
import com.catb.dao.CommentDAO;
import com.catb.dao.QACatalogDAO;
import com.catb.model.Comment;
import com.catb.model.QACatalog;

@Service
public class CommentBOImpl implements CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private QACatalogDAO qaCatalogDAO;
	
	@Transactional
	public void addComment(Comment comment, Integer catalogId) {
		if (catalogId != null) {
			QACatalog qaCatalog = qaCatalogDAO.getQACatalogById(catalogId);
			if (qaCatalog != null) {
				comment.setQaCatalog(qaCatalog);
			}
		}
		
		commentDAO.addComment(comment);
	}
	
	@Transactional
	public List<Comment> getComments(Integer catalogId, String title,
			Integer page, Integer pageSize) {
		return commentDAO.getComments(catalogId, title, page, pageSize);
	}
	
	@Transactional
	public Long countComments(Integer catalogId, String title) {
		return commentDAO.countComments(catalogId, title);
	}
}
