package com.niit.bridge.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.bridge.model.Forum;
@Repository("Forumdao")
@Transactional
public class ForumDaoImpl implements ForumDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}


	public boolean saveForum(Forum forum) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			forum.setForumStatus("N");
			session.save(forum);
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
		finally{
			session.flush();
			session.close();
		}
	}

	public boolean deleteForum(Forum forum) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(forum);
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
		finally{
			session.flush();
			session.close();
		}
	}

	public boolean updateForum(Forum forum) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(forum);
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
		finally{
			session.flush();
			session.close();
		}
	}

	public Forum getForumByForumId(int forumId) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Query query= session.createQuery("from Forum where forumId =?");
		query.setInteger(0, forumId);
		Forum forum=(Forum)query.uniqueResult();
		return forum;
		}

	

	public List<Forum> getAllForums() {
		// TODO Auto-generated method stub
		Session session = getSession();

		Query query = session.createQuery("from Forum ");
		
		List<Forum> forumList = query.list();

		return forumList;

	}

	public List<Forum> getAllApprovedForums() {
		Session session = getSession();

		Query query = session.createQuery("from Forum where forumStatus = 'A' ");
		List<Forum> ApprovedForumList = query.list();
        session.close();
		return ApprovedForumList;
	}
	}
	
	


