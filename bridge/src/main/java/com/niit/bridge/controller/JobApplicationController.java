package com.niit.bridge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.bridge.dao.JobApplicationDao;

import com.niit.bridge.model.JobApplication;

@RestController
public class JobApplicationController {

	@Autowired
	JobApplicationDao jobApplicationDao;
	
	@RequestMapping(value="/jobApplications", method=RequestMethod.GET)
	public ResponseEntity<List<JobApplication>> getAllJobApplication(){
		List<JobApplication> jobApplication=jobApplicationDao.getAllJobApplication();
		if(jobApplication.isEmpty()){
			return new ResponseEntity<List<JobApplication>>(jobApplication, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<JobApplication>>(jobApplication, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/jobApplication", method=RequestMethod.POST)
	public ResponseEntity<JobApplication> createForum(@RequestBody JobApplication jobApplication){
		if(jobApplicationDao.getJobApplicationByJobApplicationId(jobApplication.getJobApplicationId())==null){
			jobApplicationDao.saveJobApplication(jobApplication);
			return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
		}
		jobApplication.setErrorMessage("Blog already exist with id : "+ jobApplication.getJobApplicationId());
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}

	
	
	@RequestMapping(value="/jobApplication/{id}", method=RequestMethod.PUT)
	public ResponseEntity<JobApplication> updateBlog(@PathVariable("id")int id, @RequestBody JobApplication jobApplication){
		if(jobApplicationDao.getJobApplicationByJobApplicationId(id)== null){
			jobApplication=new JobApplication();
			jobApplication.setErrorMessage("No blog exist with id : "+jobApplication.getJobApplicationId());
			return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.NOT_FOUND);
			
		}
		jobApplicationDao.updateJobApplication(jobApplication);
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}
	

	
		
}
