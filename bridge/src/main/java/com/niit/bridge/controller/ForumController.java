package com.niit.bridge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.bridge.dao.ForumDao;
import com.niit.bridge.model.Forum;


@RestController
public class ForumController {

	@Autowired
	ForumDao forumDao;
	
	@RequestMapping(value="/forums", method=RequestMethod.GET)
	public ResponseEntity<List<Forum>> getAllForums(){
		List<Forum> forum=forumDao.getAllForums();
		if(forum.isEmpty()){
			return new ResponseEntity<List<Forum>>(forum, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Forum>>(forum, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/forum", method=RequestMethod.POST)
	public ResponseEntity<Forum> createForum(@RequestBody Forum forum){
		if(forumDao.getForumByForumId(forum.getForumId())==null){
			forumDao.saveForum(forum);
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		forum.setErrorMessage("Blog already exist with id : "+ forum.getForumId());
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/forum/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Forum> updateForum(@PathVariable("id")int id, @RequestBody Forum forum){
		if(forumDao.getForumByForumId(id)== null){
			forum=new Forum();
			forum.setErrorMessage("No blog exist with id : "+forum.getForumId());
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
			
		}
		forumDao.updateForum(forum);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/forum/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Forum> deleteForum(@PathVariable("id")int id){
		Forum forum = forumDao.getForumByForumId(id);
		if(forum==null){
			forum=new Forum();
			forum.setErrorMessage("No blog exist with id : "+id);
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
			
		}
		forumDao.deleteForum(forum);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	@RequestMapping(value="/forum/{id}", method=RequestMethod.GET)
	public ResponseEntity<Forum> getForum(@PathVariable("id") int id){
		Forum forum = forumDao.getForumByForumId(id);
		if(forum==null){
			forum=new Forum();
			forum.setErrorMessage("No blog exist with id : "+id);
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/approvedForums", method=RequestMethod.GET)
	public ResponseEntity<List<Forum>> approvedForums(){
		List<Forum> forum=forumDao.getAllApprovedForums();
		if(forum.isEmpty()){
			return new ResponseEntity<List<Forum>>(forum, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Forum>>(forum, HttpStatus.OK);
	}
	
	

	@PutMapping(value = "/approveForum/{id}")				
	public ResponseEntity<Forum> approveForum( @PathVariable("id") int id,@RequestBody Forum forum) {
		System.out.println("AAA");
		System.out.println("**********Starting of approveForum() method.");
		Forum forum1=forumDao.getForumByForumId(id);   
		forum1.setForumStatus("A");	// A = Approve, R = Reject, N = New
		forumDao.updateForum(forum1);
		
		System.out.println("**********End of approveBlog() method.");
		return new ResponseEntity<Forum> (forum1, HttpStatus.OK);
	}
	
}

