package com.niit.bridge.controller;

import java.util.List;

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

import com.niit.bridge.dao.ForumCommentDao;
import com.niit.bridge.model.ForumComment;

@RestController
public class ForumCommentController {
	@Autowired
	ForumCommentDao forumCommentDao;
	@GetMapping(value = "/forumComments/{id}")
	public ResponseEntity<List<ForumComment>> listForumComments( @PathVariable("id") int id  ) {
		List<ForumComment> forumComment = forumCommentDao.list(id);
		if(forumComment.isEmpty()) {
			return new ResponseEntity<List<ForumComment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ForumComment>>(forumComment, HttpStatus.OK);
	}
	
	@PostMapping(value = "/forumComment/{id}")
	public ResponseEntity<ForumComment> createForumComment(@RequestBody ForumComment forumComment) {
		
		
		if(forumCommentDao.get(forumComment.getForumCommentId()) == null) {
			forumComment.setForumCommentDate(new java.util.Date(System.currentTimeMillis()));
			forumCommentDao.save(forumComment);
			return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
		}
		forumComment.setErrorMessage("ForumComment already exist with id : " +forumComment.getForumId());
		return new ResponseEntity<ForumComment>(HttpStatus.OK);
	}
	


@PutMapping(value = "/forumComment/{id}")
public ResponseEntity<ForumComment> updateForumComment(@PathVariable("id") int id, @RequestBody ForumComment forumComment) {
	if(forumCommentDao.get(id) == null) {
		forumComment = new ForumComment();
		forumComment.setErrorMessage("No forumComment exist with id : " +forumComment.getForumId());
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
	}
	forumCommentDao.update(forumComment);
	return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
}

@DeleteMapping(value = "/forumComment/{id}")
public ResponseEntity<ForumComment> deleteForumComment(@PathVariable("id") int id) {
	ForumComment forumComment = forumCommentDao.get(id);
	if(forumComment == null) {
		forumComment = new ForumComment();
		forumComment.setErrorMessage("No forumComment exist with id : " + id);
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
	}
	forumCommentDao.delete(forumComment);
	return new ResponseEntity<ForumComment>(HttpStatus.OK);		
}

/*@GetMapping(value = "/forumComments/{id}")
public ResponseEntity<ForumComment> getForumComment(@PathVariable("id") int id) {
	ForumComment forumComment = forumCommentDao.get(id);
	if(forumComment == null) {
		forumComment = new ForumComment();
		forumComment.setErrorMessage("No forumComment exist with id : " + id);
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
}*/

}
