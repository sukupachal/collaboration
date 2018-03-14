package com.niit.bridge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.bridge.dao.JobDao;

import com.niit.bridge.model.Job;

@RestController
public class JobController {

	@Autowired
	JobDao jobDao;
	
	@RequestMapping(value="/jobs", method=RequestMethod.GET)
	public ResponseEntity<List<Job>> getAllJobs(){
		List<Job> job=jobDao.getAllJobs();
		if(job.isEmpty()){
			return new ResponseEntity<List<Job>>(job, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/job", method=RequestMethod.POST)
	public ResponseEntity<Job> createForum(@RequestBody Job job){
		if(jobDao.getJobByJobId(job.getJobId())==null){
			jobDao.saveJob(job);
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		job.setErrorMessage("Blog already exist with id : "+ job.getJobId());
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
}
