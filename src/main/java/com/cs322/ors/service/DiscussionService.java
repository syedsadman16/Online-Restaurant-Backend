package com.cs322.ors.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.CommentRepository;
import com.cs322.ors.db.DiscussionRepository;
import com.cs322.ors.db.DishKeyWordRepository;
import com.cs322.ors.db.DishRepository;
import com.cs322.ors.model.Comment;
import com.cs322.ors.model.Discussion;
import com.cs322.ors.model.Dish;
import com.cs322.ors.model.Transaction;
import com.cs322.ors.model.User;

@Service
public class DiscussionService {


	@Autowired
	private DiscussionRepository discussionRepository;
	
	@Autowired
	private CensorService censorService;
	
	public List<Discussion> getAllDiscussions() {
		return discussionRepository.findAll();
	}
	
//	public Discussion createDiscussion(String topic, User customer) {	
//		Discussion discussion = new Discussion(customer, topic);
//		return discussionRepository.save(discussion);
//	}
	
	public Discussion createDiscussion(Discussion discussion, User user) {	
		discussion.setTopic(censorService.censor(discussion.getTopic(), user));
		return discussionRepository.save(discussion);
	}

	
	public Discussion getDiscussion(long id) {
		return discussionRepository.findById(id).get();
	}
	

}
