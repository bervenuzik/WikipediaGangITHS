package com.example.wikipediagang.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="article_borrower_info")
public class ArticleBorrowerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="article_hard_copy_id")
    private ArticleHardCopy articleHardCopy;

    @ManyToOne
    @JoinColumn(name="borrower_id")
    private Person person;

    @Column(name="borrow_date")
    private LocalDate borrowDate;

    @Column(name="expected_return_date")
    private LocalDate expectedReturnDate;

    @Column(name="actual_return_date")
    private LocalDate actualReturnDate;

    public ArticleBorrowerInfo() {
    }

    public ArticleBorrowerInfo(ArticleHardCopy articleHardCopy, Person person) {
        this.articleHardCopy = articleHardCopy;
        this.person = person;
        this.borrowDate = LocalDate.now();
        this.expectedReturnDate = borrowDate.plusWeeks(2);              // adds two weeks to borrow date,
                                                                            // which makes loan period = 2 weeks/person
    }

    public int getId() {
        return id;
    }

    public ArticleHardCopy getArticleHardCopy() {
        return articleHardCopy;
    }

    public void setArticleHardCopy(ArticleHardCopy articleHardCopy) {
        this.articleHardCopy = articleHardCopy;
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

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate returnDate) {
        this.actualReturnDate = returnDate;
    }

    @Override
    public String toString() {
        return "ArticleBorrowerInfo{" +
                ", article=" + articleHardCopy.getArticle().getTitle() +
                ", person=" + person.getFirstName() + " " + person.getLastName() +
                ", borrowDate=" + borrowDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", actualReturnDate=" + actualReturnDate +
                '}' + "\n";
    }
}
