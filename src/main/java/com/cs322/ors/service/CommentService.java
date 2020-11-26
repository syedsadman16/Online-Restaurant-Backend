package com.cs322.ors.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs322.ors.db.CommentRepository;
import com.cs322.ors.model.Comment;
import com.cs322.ors.model.Discussion;
import com.cs322.ors.model.User;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;

	public List<Comment> getCommentsForDiscussion(long discussionId) {
		return commentRepository.findByDiscussion_Id(discussionId);
	}

	public Comment createComment(Comment comment) {
		return commentRepository.save(comment);
	}
}
