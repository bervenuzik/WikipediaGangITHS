package com.example.wikipediagang.Model;
import com.example.wikipediagang.Model.Article;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import  com.example.wikipediagang.Model.Person;

import java.time.LocalDate;

@Entity
@Table(name="article_borrower_info")
public class ArticleBorrowerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="article_id")
    private Article article;

    @OneToOne
    @JoinColumn(name="borrower_id")
    private Person person;

    @Column(name="borrow_date")
    private LocalDate borrowDate;

    @Column(name="return_date")
    private LocalDate returnDate;

    public ArticleBorrowerInfo() {
    }

    public ArticleBorrowerInfo(Article article, Person person, LocalDate borrowDate) {
        this.article = article;
        this.person = person;
        this.borrowDate = borrowDate;
        this.returnDate = borrowDate.plusWeeks(2);               //adds two weeks to borrow date,
                                                                            // which makes loan period = 2 weeks/person
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

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "ArticleBorrowerInfo{" +
                "id=" + id +
                ", article=" + article.getTitle() +
                ", person=" + person +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}' + "\n";
    }
}
