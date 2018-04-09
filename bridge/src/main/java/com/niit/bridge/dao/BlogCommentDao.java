package com.niit.bridge.dao;

import java.util.List;

import com.niit.bridge.model.BlogComment;


public interface BlogCommentDao {
	
	public boolean save(BlogComment blogComment);
	public boolean update(BlogComment blogComment);
	public boolean saveOrUpdate(BlogComment blogComment);
	public boolean delete(BlogComment blogComment);
	public BlogComment get(int id);
	public List<BlogComment> list(int id);

}
