package com.example.wikipediagang.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="article_reservation_queue")
public class ArticleReservationQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name="borrower_id")
    private Person person;

    @Column(name="timestamp")
    private LocalDateTime timestamp;

    public ArticleReservationQueue() {
    }

    public ArticleReservationQueue(Article article, Person person) {
        this.article = article;
        this.person = person;
        this.timestamp = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ArticleReservationQueue{" +
                ", article=" + getArticle().getTitle() +
                ", person=" + getPerson().getFirstName() + " " + getPerson().getLastName() +
                ", timestamp=" + getTimestamp() +
                '}' + "\n";
    }
}
