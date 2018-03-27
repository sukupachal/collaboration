package com.niit.bridge.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.bridge.model.Friend;

@EnableTransactionManagement
@Repository(value="friendDAO")
public class FriendDaoImpl implements FriendDao{
	
	@Autowired	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected Session getSession() {
		return sessionFactory.openSession();
	}

	
	public FriendDaoImpl() { 		
		
	}	
	public FriendDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public boolean save(Friend friend) {
		try {
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Friend friend) {
		try {
			Session s=getSession();
			s.update(friend);
			s.flush();
			s.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Friend get(String userId, String friendId) {
		String hql = "from Friend where userId = '" + friendId + "' and friendId = '" + userId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	
	
	@Transactional
	public List<Friend> getMyFriends(String userId) {
		String hql = "from Friend where ( userId = '" + userId + "' and status = 'A' ) or ( friendId = '" + userId + "' and status = 'A' )";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		return list;
	}
	
	
			
			
		@Transactional
	public List<Friend> getNewFriendRequests(String friendId){
			String hql = "from Friend where friendId = '" + friendId + "' and status = 'N'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Friend> list = (List<Friend>) query.list();
			
			return list;
		}
		
		
		@Transactional
	public void setOnline(String userId) {
		// TODO Auto-generated method stub
		
		String hql = "update Friend set isOnline = 'Y' where userId = '" + userId + "'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
	}
	
	

       @Transactional
      public void setOffline(String userId) {
		// TODO Auto-generated method stub
		String hql = "update Friend set isOnline = 'N' where userId = '" + userId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	
	}		

	}
	
	