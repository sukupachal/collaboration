package com.niit.bridge.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.bridge.model.Job;
@Repository("JobDao")
@Transactional
public class JobDaoImpl implements JobDao{
	
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	

	public boolean saveJob(Job job) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(job);
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

	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		Session session = getSession();

		Query query = session.createQuery("from Job ");
		
		List<Job> jobList = query.list();

		return jobList;

	}

	public Job getJobByJobId(int jobId) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Query query= session.createQuery("from Job where jobId =?");
		query.setInteger(0, jobId);
		Job job=(Job)query.uniqueResult();
		return job;
		}
	}
	
	
	
	
	
	


