package com.niit.bridge.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.bridge.dao.UserDetailsDao;
import com.niit.bridge.model.UserDetails;

@RestController
public class UserDetailsController {
	@Autowired
	UserDetailsDao userDetailsDao;
	@Autowired
	UserDetails userDetails;
	
	
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(){
		return "Test";
	}
	
	

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity<List<UserDetails>> getAllUser(){
		List<UserDetails> users=userDetailsDao.getAllUser();
		if(users.isEmpty()){
			return new ResponseEntity<List<UserDetails>>(users,HttpStatus.NO_CONTENT);
		}
		System.out.println("kkkkkk");
		return new ResponseEntity<List<UserDetails>>(users,HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/", method=RequestMethod.POST)
	public ResponseEntity<UserDetails> creatUser(@RequestBody UserDetails users){
		if(userDetailsDao.getUserByUserId(users.getUserId())==null){
			users.setRole("USER");
			userDetailsDao.saveUser(users);
			return new ResponseEntity<UserDetails>(users,HttpStatus.OK);
		}
		users.setErrorMessage("User already exist with id : "+users.getUserId());
		return new ResponseEntity<UserDetails>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.PUT)
	public ResponseEntity<UserDetails> updateuser(@PathVariable("id") String id, @RequestBody UserDetails users){
		if(userDetailsDao.getAllUser()==null){
			users =new UserDetails();
			users.setErrorMessage("User does not exist with id : " +users.getUserId());
			return new ResponseEntity<UserDetails>(users, HttpStatus.NO_CONTENT);
		}
		userDetailsDao.updateUser(users);
		return new ResponseEntity<UserDetails>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<UserDetails>deleteUser(@PathVariable("id")String id){
		UserDetails users=userDetailsDao.getUserByUserId(id);
		if(users == null){
			users = new UserDetails();
			users.setErrorMessage("User does not exist with id : " + id);
			return new ResponseEntity<UserDetails>(users, HttpStatus.NOT_FOUND);
			
		}
		userDetailsDao.deleteUser(users);
		return new ResponseEntity<UserDetails>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/user/{id}",method=RequestMethod.GET)
	public ResponseEntity<UserDetails>getAllUser(@PathVariable("id")String id){
		UserDetails users = userDetailsDao.getUserByUserId(id);
		if (users == null){
			users = new UserDetails();
			users.setErrorMessage("User does not exist with id : " + id);
				return new ResponseEntity<UserDetails>(users, HttpStatus.NOT_FOUND);
				
		}
		return new ResponseEntity<UserDetails>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/user/logout",method=RequestMethod.GET)
	public ResponseEntity<UserDetails>logout(HttpSession session){
		
			session.invalidate();
			 
			
				return new ResponseEntity<UserDetails>(new UserDetails(), HttpStatus.OK);
				
		}
		
	
	
	
	
	
	@RequestMapping(value = "/user/authenticate/", method = RequestMethod.POST)
	public ResponseEntity<UserDetails> UserAuthentication(@RequestBody UserDetails users, HttpSession session){
		users = userDetailsDao.UserAuthentication(users.getUserId(), users.getPassword());
		if(users == null){
			users = new UserDetails();
			users.setErrorMessage("Invalid userId or password...");
		}
		else {
			session.setAttribute("loggedInUser", users);
			System.out.println("logged session set ");
			session.setAttribute("loggedInUserID", users.getUserId());
		}
		return new ResponseEntity<UserDetails>(users, HttpStatus.OK);
	}
	
	
	
}
