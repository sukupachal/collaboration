package com.niit.bridge.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.bridge.dao.JobDao;

import com.niit.bridge.model.Job;
import com.niit.bridge.model.JobApplication;
import com.niit.bridge.model.UserDetails;

@RestController
public class JobController {

	@Autowired
	JobDao jobDao;
	
	@GetMapping(value = "/jobs")
	public ResponseEntity<List<Job>> listJobs() {
		List<Job> job = jobDao.list();
		if(job.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
	}
	
	@GetMapping(value = "/jobApplications")
	public ResponseEntity<List<JobApplication>> listJobApplications() {
		System.out.println("**********Starting of listJobApplications() method.");
		
		List<JobApplication> jobApplications = jobDao.listJobApplications();
		
		System.out.println("**********End of listJobApplications() method.");
		return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
	}
	
	@PostMapping(value = "/job/")
	public ResponseEntity<Job> saveJob(@RequestBody Job job, HttpSession session) {
		System.out.println("**********Starting of saveJob() method.");
		
		{
			
			/*job.setId(100);
			job.setCompanyName("");
			job.setLocation("");
			job.setDescription("");*/
			job.setJobDate(new Date());
			job.setStatus("v");
			jobDao.save(job);
			System.out.println("**********End of saveJob() method.");
			return new ResponseEntity<Job>(job, HttpStatus.OK);
			
		}		
	}

	@PutMapping(value = "/updateJob/{id}")   // in URL we give/updateJob/1
	public ResponseEntity<Job> updateJob(@PathVariable("id") int id, @RequestBody Job job) {
		System.out.println("**********Starting of updateJob() method.");
		if (jobDao.get(id) == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " + job.getJobId());
			System.out.println("No job exist with id : " + job.getJobId());
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		jobDao.update(job);
		System.out.println("**********End of updateJob() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
		/*{
			Job job1=jobDao.get(id);
			job1.setStatus(job.getStatus());
			jobDao.update(job1);
				log.debug("**********End of updateBlog() method.");
			return new ResponseEntity<Job>(job1, HttpStatus.OK);
		}*/
	
	@DeleteMapping(value = "/deleteJob/{id}")
	public ResponseEntity<Job> deleteJob(@PathVariable("id") int id) {
		System.out.println("**********Starting of deleteJob() method.");
		Job job = jobDao.get(id);
		if(job == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " + id);
			System.out.println("No job exist with id : " + id);
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		jobDao.delete(job);
		System.out.println("**********End of deleteJob() method.");
		return new ResponseEntity<Job>(HttpStatus.OK);		
	}
	@GetMapping(value = "/getJob/{id}")
	public ResponseEntity<Job> getJob(@PathVariable("id") int id) {
		System.out.println("**********Starting of getJob() method.");
		Job job = jobDao.get(id);
		if(job == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " + id);
			System.out.println("No job exist with id : " + id);
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		System.out.println("**********End of getJob() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getMyAppliedJobs")
	public ResponseEntity<List<Job>> getMyAppliedJobs(HttpSession httpSession) {
		System.out.println("**********Starting of getMyAppliedJobs() method.");
		UserDetails loggedInUser = (UserDetails) httpSession.getAttribute("loggedInUser");
		String loggedInUserId = loggedInUser.getUserId();
		
		@SuppressWarnings("unchecked")
		List<Job> jobs = (List<Job>) jobDao.getMyAppliedJobs(loggedInUserId);
		System.out.println("**********End of getMyAppliedJobs() method.");
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}
	
	@PutMapping(value = "/callForInterview/{userId}/{jobId}")
	public ResponseEntity<Job> callForInterview(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @RequestBody JobApplication jobApplication) {
		System.out.println("**********Starting of callForInterview() method.");
		jobApplication = jobDao.get(userId, jobId);
		jobApplication.setStatus("C");
		Job job = new Job();
		if (jobDao.updateJobApplication(jobApplication) == false) {
			job.setErrorCode("404");
			job.setErrorMessage("Not able to change job application status 'call for interview'...");
			System.out.println("Not able to change job application status 'call for interview'...");
		}
		System.out.println("**********End of callForInterview() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@PutMapping(value = "/rejectJobApplication/{userId}/{jobId}")
	public ResponseEntity<Job> rejectJobApplication(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @RequestBody JobApplication jobApplication) {
		System.out.println("**********Starting of rejectJobApplication() method.");
		jobApplication = jobDao.get(userId, jobId);
		jobApplication.setStatus("R");
		Job job = new Job();
		if (jobDao.updateJobApplication(jobApplication) == false) {
			
			job.setErrorCode("404");
			job.setErrorMessage("Not able to reject the application...");
			System.out.println("Not able to reject the application...");
		}
		System.out.println("**********End of rejectJobApplication() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/listVacantJobs")
	public ResponseEntity<List<Job>> listVacantJobs() {
		System.out.println("**********Starting of listVacantJobs() method.");
		List<Job> vacantJobs = jobDao.listVacantJobs();
		if (vacantJobs.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		System.out.println("**********End of listVacantJobs() method.");
		return new ResponseEntity<List<Job>>(vacantJobs, HttpStatus.OK);
	}
	
	@PostMapping(value = "/jobApplied")
	public ResponseEntity<Job> applyForJob(@RequestBody Job job, HttpSession httpSession) {		
		System.out.println("**********Starting of applyForJob() method.");
		JobApplication jobApplication = new JobApplication();
		UserDetails loggedInUser = (UserDetails) httpSession.getAttribute("loggedInUser");
		jobApplication.setUserid(loggedInUser.getUserId());
		jobApplication.setJobid(job.getJobId());
		jobApplication.setStatus("A"); // A = Applied ||R = Rejected ||C = Call for Interview 

		jobDao.applyForJob(jobApplication);

		System.out.println("**********End of applyForJob() method.");
		return new ResponseEntity<Job>(HttpStatus.OK);
	}
}

