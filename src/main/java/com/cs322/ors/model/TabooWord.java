package com.cs322.ors.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TabooWord{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    

	@Column(nullable = false, unique = true)
    private String word;
	
	public TabooWord() {
	}


    public TabooWord(String word) {
        this.word = word;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "TabooWords [id=" + id + ", word=" + word + "]";
    }

    


}
