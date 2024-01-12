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

import java.time.LocalDate;

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

    private LocalDate date;

    public Comment() {
    }

    public Comment(String text, Person person, Article article) {
        this.text = text;
        this.person = person;
        this.article = article;
        this.date = LocalDate.now();
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

    public LocalDate getDate() {
        return date;
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
        return getId() + ". Comment: "+ getText() + "\nFrom: " + getPerson().getFirstName() +" "+
                getPerson().getLastName() + "\nOn article: " + getArticle().getTitle() +
                "\nDate: " + getDate();
    }
}
