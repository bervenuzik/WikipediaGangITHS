package com.example.wikipediagang;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/*@Entity
@Table(name = "error_log")
public class ErroLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String text;
    @Temporal(TemporalType.DATE)
    Date date;
    @Column(name = "person_id")
    int personid;


    public ErroLog(int id, String text, Date date, int personid) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.personid = personid;
        //this.person = person;
    }

    public ErroLog() {
    }



    @Override
    public String toString() {
        return "ErroLog{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", personid=" + personid +
                //", person=" + person +
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

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }
}*/


/*
spring.datasource.url = jdbc:mysql://sql11.freesqldatabase.com/sql11670538
        spring.datasource.username = sql11670538
        spring.datasource.password = WJw7qWaT2Z
*/


@Entity
@Table(name = "error_log")
public class ErroLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String text;
    @Temporal(TemporalType.DATE)
    Date date;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    Person person;

    public ErroLog(int id, String text, Date date, Person person) {
        this.id = id;
        this.text = text;
        this.date = date;
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
}
