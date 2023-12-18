package com.example.wikipediagang;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "error_log")
public class ErrorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String text;
    @Temporal(TemporalType.DATE)
    Date date;
    int amount;
    String status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    Person person;

    public ErroLog(String text, Date date, int amount, String status, Person person) {
        this.text = text;
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.person = person;
    }

    public ErroLog() {
    }

    @Override
    public String toString() {
        return "ErroLog{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", person=" + person +
                '}';
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
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
