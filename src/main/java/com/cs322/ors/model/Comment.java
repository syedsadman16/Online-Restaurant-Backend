package com.cs322.ors.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne

    Discussion discussion;
    
    @OneToOne
    @JsonIdentityReference(alwaysAsId = false)
    User commenter;
    
    String message;

    public Comment() {}
    
    
	public Comment(Discussion discussion, User commenter, String message) {
		super();
		this.discussion = discussion;
		this.commenter = commenter;
		this.message = message;
	}

	public Comment(Discussion discussion, String message) {
		super();
		this.discussion = discussion;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Discussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}

	public User getCommenter() {
		return commenter;
	}

	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}

    
}
