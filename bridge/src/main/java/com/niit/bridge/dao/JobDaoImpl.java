package com.niit.bridge.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.bridge.model.Job;
import com.niit.bridge.model.JobApplication;
@Repository("JobDao")
@EnableTransactionManagement
public class JobDaoImpl implements JobDao{
	
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}
	
	public JobDaoImpl() {

	}

	public JobDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	public boolean save(Job job) {
		// TODO Auto-generated method stub
		try {
			job.setStatus("V");	//V-Vacant	F-Filled	P-Pending
			job.setJobDate(new Date(System.currentTimeMillis())); // set
			job.setNoOfApplicants(0);														// current
																	// time as

			// postDate
			
			Session session = getSession();

			session.save(job);

			session.flush();

			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Transactional
	public boolean update(Job job) {
		// TODO Auto-generated method stub
		try {
			Session session = getSession();
			session.update(job);
			session.flush();
			session.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
			return false;
		}


	@Transactional
	public boolean delete(Job job) {
		// TODO Auto-generated method stub
		try {
			Session session = getSession();
			session.delete(job);
			session.flush();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Job get(int id) {
		// TODO Auto-generated method stub
		Session session = getSession();

		Query query = session.createQuery("from Job where id = ?");

		query.setInteger(0, id);
		return (Job) query.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Job> list() {
		// TODO Auto-generated method stub
		Session session = getSession();

		Query query = session.createQuery("from Job");
		List<Job> jobList = query.list();
		session.close();
		return jobList;
	}

	@Transactional
	public List<Job> getMyAppliedJobs(String userid) {
		// TODO Auto-generated method stub
		String hql = "from Job where id in (select jobid from JobApplication where userid = '" + userid + "')";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	


	@Transactional
	public JobApplication get(String userid, String jobid) {
		// TODO Auto-generated method stub
		String hql = "from JobApplication where userid = '" + userid + "' and jobid = '" + jobid + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (JobApplication) query.list();
	}


	@Transactional
	public boolean updateJobApplication(JobApplication jobApplication) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(jobApplication);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Transactional
	public boolean applyForJob(JobApplication jobApplication) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(jobApplication);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Transactional
	public List<JobApplication> listJobApplications() {
		// TODO Auto-generated method stub
		String hql = "from JobApplication";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	@Transactional
	public List<Job> listVacantJobs() {
		// TODO Auto-generated method stub
		String hql = "from Job where status = 'V'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
}
	
	



	

	