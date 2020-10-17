package com.cs322.ors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name  = "User")
public class Taboo_Words{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String word; //PK

    public Taboo_Words() {
    }

    public Taboo_Words(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Taboo_Words [word=" + word + "]";
    }
}
