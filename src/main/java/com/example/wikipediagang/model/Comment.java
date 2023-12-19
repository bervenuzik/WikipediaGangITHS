package com.example.wikipediagang.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Date;



@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
    @Column(name = "text")
    private String text;


    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn( name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private Article article;

    private Date date;


    public Comment() {
    }

    public Comment(int id, String text, Person person, Article article, Date date) {
        this.id = id;
        this.text = text;
        this.person = person;
        this.article = article;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if(textValidator(text)){
            this.text = text;
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean textValidator(String text){
        if(text.isEmpty()){
            System.out.println("you can't write an empty comment!");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comment from "+ person.getFirstName() +" "+ person.getLastName() + " On article :" + article.getTitle() +" comment: " + text + "Date: " + date;
    }
}
