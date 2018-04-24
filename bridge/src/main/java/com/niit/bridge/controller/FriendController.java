package com.niit.bridge.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.bridge.dao.FriendDao;
import com.niit.bridge.model.Friend;
import com.niit.bridge.model.UserDetails;

@RestController
public class FriendController {
	@Autowired
	Friend friend;
	
	@Autowired
	FriendDao friendDao;
	
	@GetMapping(value = "/myFriends")
	public ResponseEntity<List<Friend>> myFriends(HttpSession session) {
		System.out.println("**********Starting of myFriends()vngngb method");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		System.out.println("nnn0"+loggedInUser);
		List<Friend> myFriends = friendDao.getMyFriends(loggedInUser.getUserId());
		System.out.println("**********End of myFriends() method");
		return new ResponseEntity<List<Friend>> (myFriends, HttpStatus.OK);
	}
	
	@PostMapping(value = "/addFriend/{friendId}")			
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendId") String friendId, HttpSession session) {
		System.out.println("**********Starting of sendFriendRequest() method");
		Friend friend = new Friend();
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		
		Friend f=friendDao.get(loggedInUser.getUserId(), friendId);
		
		System.out.println(loggedInUser.getUserId()+"=======++++   "+friendId);
		if(f==null)
		{
		friend.setUserId(loggedInUser.getUserId());
		
		friend.setFriendId(friendId);
		friend.setStatus("N");	// N = New, A = Accepted, R = Rejected, U = Unfriend 
		friendDao.save(friend);
		}
		
		else
		{
			f.setStatus("N");
			friendDao.update(f);
		}
		System.out.println("**********End of sendFriendRequest() method");
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	
	@PutMapping(value = "user/unFriend/{friendId}")			
	public ResponseEntity<Friend> unFriend(@PathVariable("friendId") String friendId, HttpSession session) {
		System.out.println("**********Starting of unFriend() method");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		String uid=loggedInUser.getUserId();
		Friend friend = friendDao.get(uid, friendId);
		friend.setUserId(loggedInUser.getUserId());
		friend.setFriendId(friendId);
		friend.setStatus("U");	// N = New, A = Accepted, R = Rejected, U = Unfriend 
		friendDao.update(friend);
		System.out.println("**********End of unFriend() method");
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	
	@PutMapping(value = "/rejectFriend/{friendId}")				
	public ResponseEntity<Friend> rejectFriendRequest(@PathVariable("friendId") String friendId, HttpSession session) {
		System.out.println("**********Starting of rejectFriendRequest() method");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		String uid=loggedInUser.getUserId();
		Friend friend = friendDao.get(uid, friendId);
		friend.setUserId(loggedInUser.getUserId());
		friend.setFriendId(friendId);
		friend.setStatus("R");	// N = New, A = Accepted, R = Rejected, U = Unfriend  
		friendDao.update(friend);
		System.out.println("**********End of rejectFriendRequest() method");
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	
	
	@PutMapping(value = "user/acceptFriend/{friendId}")			
	public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("friendId") String friendId, HttpSession session) {
		System.out.println("**********Starting of acceptFriendRequest() method");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		String uid=loggedInUser.getUserId();
		System.out.println("iiiiii" + uid );
		Friend friend = friendDao.getRequest(uid, friendId);
		System.out.println("ppppp "+ friend);
		friend.setUserId(loggedInUser.getUserId());
		System.out.println("bbbbbb");
		friend.setFriendId(uid);
		friend.setUserId(friendId);
		friend.setStatus("A");	// N = New, A = Accepted, R = Rejected, U = Unfriend 
		friendDao.update(friend);
		System.out.println("**********End of acceptFriendRequest() method");
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	
	
@GetMapping(value = "/newFriendRequests")	
	
	public ResponseEntity<List<Friend>> newFriendRequests(HttpSession session) {
		System.out.println("**********Starting of listFriends() method.");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		List<Friend> friend = friendDao.getNewFriendRequests(loggedInUser.getUserId());
		if(friend.isEmpty()) {
			return new ResponseEntity<List<Friend>>(HttpStatus.NO_CONTENT);
		}
		System.out.println("**********End of listFriends() method.");
		return new ResponseEntity<List<Friend>>(friend, HttpStatus.OK);
	}



/*public ResponseEntity<Friend> newFriendRequests(HttpSession session) {
log.debug("**********Starting of newFriendRequests() method");
User loggedInUser = (User) session.getAttribute("loggedInUser");
  friendDao.getNewFriendRequests(loggedInUser.getId());
log.debug("**********End of newFriendRequests() method");
return new ResponseEntity<Friend>(friend, HttpStatus.OK);
}*/	
}
	
	
	
	
	




