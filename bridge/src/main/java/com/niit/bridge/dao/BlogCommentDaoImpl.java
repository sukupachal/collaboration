package com.niit.bridge.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.bridge.model.BlogComment;
@EnableTransactionManagement
@Repository(value="blogCommentDao")
public class BlogCommentDaoImpl implements BlogCommentDao{
	@Autowired	//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public BlogCommentDaoImpl() { 		
		
	}	
	public BlogCommentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Transactional
	public boolean save(BlogComment blogComment) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(BlogComment blogComment) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(blogComment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOrUpdate(BlogComment blogComment) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(blogComment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(BlogComment blogComment) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	@Transactional
	public BlogComment get(int id) {
		// TODO Auto-generated method stub
		String hql = "from BlogComment where blogCommentId = " + id ;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<BlogComment> list = query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<BlogComment> list(int id) {
		// TODO Auto-generated method stub
		String hql = "from BlogComment  where blogId="+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}	
}
