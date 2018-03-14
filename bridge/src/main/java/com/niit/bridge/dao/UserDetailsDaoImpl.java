package com.niit.bridge.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.bridge.model.UserDetails;
@Repository("UserDetailsdao")
@Transactional

public class UserDetailsDaoImpl implements UserDetailsDao{

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public boolean saveUser(UserDetails user) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(user);
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

	public boolean updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(user);
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

	public boolean deleteUser(UserDetails user) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(user);
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

	public List<UserDetails> getAllUser() {
		Session session = getSession();

		Query query = session.createQuery("from UserDetails");
		
		List<UserDetails> userList = query.list();

		return userList;
	}

	public UserDetails UserAuthentication(String userId, String userPassword) {
		// TODO Auto-generated method stub
		Session session = getSession();
		
		Query query = session.createQuery("from UserDetails where userId =? and password =?");
	    query.setParameter(0, userId);
	    query.setParameter(1, userPassword);
	    UserDetails user=(UserDetails)query.uniqueResult();
	    return user;
		
	}

	public UserDetails getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		Session session = getSession();

		Query query = session.createQuery("from UserDetails where userId = ?");
		query.setString(0, userId);

		return (UserDetails) query.uniqueResult();

	}

}
