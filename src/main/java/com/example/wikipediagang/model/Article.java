package com.example.wikipediagang.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(name="text")
    private String content;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name="category_id")
    private ArticleCategory category;

    @Column(name="available_as_hard_copy")
    private String availableAsHardCopy;

    @Column(name="num_views")
    private int numOfViews;

    @Column(name="status")
    private String status;

    public Article() {
    }

    public Article(String title, String content, Person person, ArticleCategory category) {
        this.title = title;
        this.content = content;
        this.person = person;
        this.category = category;
        this.availableAsHardCopy = "no";
        this.numOfViews = 0;
        this.status = "review";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    public String getAvailableAsHardCopy() {
        return availableAsHardCopy;
    }

    public void setAvailableAsHardCopy(String availableAsHardCopy) {
        this.availableAsHardCopy = availableAsHardCopy;
    }

    public int getNumOfViews() {
        return numOfViews;
    }

    public void setNumOfViews(int numOfViews) {
        this.numOfViews = numOfViews;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + getTitle() + '\'' + ",\n" +
                " author=" + getPerson().getFirstName() + " " + getPerson().getLastName() +
                ", category=" + getCategory().getName() +
                ", available as HardCopy='" + getAvailableAsHardCopy() + '\'' +
                ", numOfViews=" + getNumOfViews() +
                ", status=" + getStatus() +
                '}' + "\n";
    }
}
