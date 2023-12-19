package com.example.wikipediagang.model;

import jakarta.persistence.*;

@Entity
@Table(name = "serach_words")

public class SearchWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    private int count;

    public SearchWord() {
    }

    public SearchWord(String text, int count) {
        this.text = text;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SearchWord{" +
                "text='" + text + '\'' +
                ", count=" + count +
                '}';
    }
}
