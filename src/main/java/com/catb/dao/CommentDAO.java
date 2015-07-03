package com.catb.dao;

import java.util.List;

import com.catb.model.Comment;

public interface CommentDAO {
	
	public void addComment(Comment comment);
	public List<Comment> getComments(Integer catalogId, String title, Integer page, Integer pageSize);
	public Long countComments(Integer catalogId, String title);
}
