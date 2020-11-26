package com.cs322.ors.controller;

import java.util.Arrays;
import java.util.List;

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
import com.cs322.ors.model.Discussion;
import com.cs322.ors.model.User;
import com.cs322.ors.security.UserPrincipal;
import com.cs322.ors.service.DiscussionService;

@RestController
@RequestMapping("/api/discussion")
public class DiscussionController {
@Autowired
	public DiscussionService discussionService;

	@PostMapping
	@PreAuthorize("hasAnyRole('CUSTOMER', 'VIP')")
	public Discussion createDiscussion(@RequestBody Discussion discussion, Authentication authUser){
		User currentUser = ((UserPrincipal) authUser.getPrincipal()).getUser();
		discussion.setCreator(currentUser);
		return discussionService.createDiscussion( discussion );

	}
	
	@GetMapping
	public List<Discussion> getDiscussions(){
		return discussionService.getAllDiscussions();
	}
	
	@GetMapping("/{discussionId}")
	public Discussion getDiscussion(@PathVariable long discussionId){
		return discussionService.getDiscussion(discussionId);
	}
	
//	@GetMapping("/{discussionId}")
//	public List<Comment> getDiscussion(@PathVariable long discussionId){
//		return discussionService.getDiscussionComments(discussionId);
//	}
}

