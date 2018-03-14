package com.niit.bridge.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;
@Entity
@Component
public class Job extends BaseDomain implements Serializable{
	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_JOB_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")

	private int jobId;
	private String jobProfile;
	private String forumComment;
	private Date forumCommentDate;
	private String userId;
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobProfile() {
		return jobProfile;
	}
	public void setJobProfile(String jobProfile) {
		this.jobProfile = jobProfile;
	}
	public String getForumComment() {
		return forumComment;
	}
	public void setForumComment(String forumComment) {
		this.forumComment = forumComment;
	}
	public Date getForumCommentDate() {
		return forumCommentDate;
	}
	public void setForumCommentDate(Date forumCommentDate) {
		this.forumCommentDate = forumCommentDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String userName;

}
