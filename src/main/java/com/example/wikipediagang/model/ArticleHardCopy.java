package com.example.wikipediagang.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    public ArticleHardCopy() {
    }

    public ArticleHardCopy(Article article) {
        this.article = article;
        this.status = "available";
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

    @Override
    public String toString() {
        return "ArticleHardCopy{" +
                ", article=" + article +
                ", status='" + status + '\'' +
                '}' + "\n";
    }
}
