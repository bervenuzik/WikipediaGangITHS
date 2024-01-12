package com.example.wikipediagang.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="article_hard_copy")
public class ArticleHardCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    private String status;

    @Column(name="date_created")
    private LocalDate dateCreated;

    public ArticleHardCopy() {
    }

    public ArticleHardCopy(Article article) {
        this.article = article;
        this.status = "available";
        this.dateCreated = LocalDate.now();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ArticleHardCopy{" +
                ", article=" + getArticle().getTitle() +
                ", status=" + getStatus() +
                ", dateCreated=" + getDateCreated() +
                '}' + "\n";
    }
}
