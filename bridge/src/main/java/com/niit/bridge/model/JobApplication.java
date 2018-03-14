package com.niit.bridge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;
@Entity
@Component
public class JobApplication extends BaseDomain implements Serializable{
	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_JOB_APPLICATION_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")

	private int jobApplicationId;
	private String UserId;
	private int jobId;
	public int getJobApplicationId() {
		return jobApplicationId;
	}
	public void setJobApplicationId(int jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	

}
