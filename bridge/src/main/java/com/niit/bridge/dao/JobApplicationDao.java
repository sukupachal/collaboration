package com.niit.bridge.dao;

import java.util.List;

import com.niit.bridge.model.JobApplication;

public interface JobApplicationDao {
	public boolean saveJobApplication(JobApplication jobApplication);
	
	public boolean updateJobApplication(JobApplication jobApplication);
	
	public JobApplication getJobApplicationByJobApplicationId(int jobApplicationId);
	
	public List<JobApplication> jobApplicationByJobId(int jobId);
	
	public boolean isJobExist(String userId, int jobId);
	
	public List<JobApplication> getAllJobApplication();

}
