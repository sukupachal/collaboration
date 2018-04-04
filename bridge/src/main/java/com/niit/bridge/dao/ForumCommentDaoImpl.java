package com.niit.bridge.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.bridge.model.ForumComment;

@EnableTransactionManagement
@Repository(value="forumCommentDAO")
public class ForumCommentDaoImpl implements ForumCommentDao {
	@Autowired	//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public ForumCommentDaoImpl() { 		
		
	}	
	public ForumCommentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public boolean save(ForumComment forumComment) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(forumComment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(ForumComment forumComment) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(forumComment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOrUpdate(ForumComment forumComment) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(forumComment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
public boolean delete(ForumComment forumComment) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(forumComment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public ForumComment get(int id) {
		// TODO Auto-generated method stub
		String hql = "from ForumComment where forumCommentId = " + id ;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<ForumComment> list = query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<ForumComment> list(int id) {
		// TODO Auto-generated method stub
		String hql = "from ForumComment  where forumId="+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}	
}
