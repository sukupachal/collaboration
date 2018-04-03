package com.niit.bridge.dao;

import java.util.List;

import com.niit.bridge.model.ForumComment;

public interface ForumCommentDao {
	public boolean save(ForumComment forumComment);
	public boolean update(ForumComment forumComment);
	public boolean saveOrUpdate(ForumComment forumComment);
	public boolean delete(ForumComment forumComment);
	public ForumComment get(String id);
	public List<ForumComment> list();
	

}
