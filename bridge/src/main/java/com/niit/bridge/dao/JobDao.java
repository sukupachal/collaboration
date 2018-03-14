package com.niit.bridge.dao;

import java.util.List;

import com.niit.bridge.model.Job;

public interface JobDao {
	public boolean saveJob(Job job);
	
	public List<Job> getAllJobs();
	
	public Job getJobByJobId (int jobId);

}
