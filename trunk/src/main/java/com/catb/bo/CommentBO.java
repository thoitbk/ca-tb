package com.catb.bo;

import java.util.List;

import com.catb.model.Comment;

public interface CommentBO {
	
	public void addComment(Comment comment, Integer catalogId);
	public List<Comment> getComments(Integer catalogId, String title, Integer page, Integer pageSize);
	public Long countComments(Integer catalogId, String title);
}
