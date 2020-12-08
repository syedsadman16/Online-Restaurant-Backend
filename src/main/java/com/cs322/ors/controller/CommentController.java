package com.cs322.ors.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs322.ors.model.Comment;
import com.cs322.ors.model.User;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;

	@PostMapping
	@PreAuthorize("hasAnyRole('CUSTOMER', 'VIP')")
	public Comment createComment(@RequestBody Comment comment, Authentication authUser){
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		comment.setCommenter(currentUser);
		return commentService.createComment(comment, currentUser);
	}
	
	@GetMapping("/{discussionId}")
	public List<Comment> getComments(@PathVariable long discussionId){
		return commentService.getCommentsForDiscussion(discussionId);
	}
}
