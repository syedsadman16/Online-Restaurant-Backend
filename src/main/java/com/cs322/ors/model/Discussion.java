package com.cs322.ors.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  
    
	@ManyToOne
	//@JsonIdentityReference(alwaysAsId = true)
	private User creator;
	
    private String topic;
    
    @JsonIgnore
	@OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList<>();

	public Discussion() {}
	
	public Discussion(User creator, String topic) {
		super();
		this.creator = creator;
		this.topic = topic;
	}	
	
	public Discussion(String topic) {
		super();
		this.topic = topic;
	}




	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
    
	

}
