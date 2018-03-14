package com.niit.bridge.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.bridge.model.JobApplication;
@Repository("JobApplication")
@Transactional

public class JobApplicationDaoImpl implements JobApplicationDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}


	public boolean saveJobApplication(JobApplication jobApplication) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(jobApplication);
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

	public boolean updateJobApplication(JobApplication jobApplication) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(jobApplication);
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

	public JobApplication getJobApplicationByJobApplicationId(int jobApplicationId) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Query query= session.createQuery("from JobApplication where jobApplicationId =?");
		query.setInteger(0, jobApplicationId);
		JobApplication jobApplication=(JobApplication)query.uniqueResult();
		return jobApplication;
		}


	

	public List<JobApplication> jobApplicationByJobId(int jobId) {
		// TODO Auto-generated method stub
		Session session = getSession();
      
		Query query = session.createQuery("from JobApplication where jobId =?" );
		query.setParameter("JobApplicationId", jobId);
		List<JobApplication> jobList = query.list();
        session.close();
		return jobList;


	}

	public boolean isJobExist(String userId, int jobId) {
		// TODO Auto-generated method stub
		Session session = getSession();
		try{
		Query query = session.createQuery("from JobApplication where userId=? and jobId=?");
		query.setString(0, userId);
		query.setInteger(1, jobId);
		return!query.list().isEmpty();
	}catch(HibernateException e){
		e.printStackTrace();
		return false;
	}
	}

	public List<JobApplication> getAllJobApplication() {
		Session session = getSession();

		Query query = session.createQuery("from JobApplication ");
		
		List<JobApplication> jobApplicationList = query.list();

		return jobApplicationList;

	}

	}

	
	


