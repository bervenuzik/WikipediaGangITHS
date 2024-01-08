package com.example.wikipediagang.service;

import com.example.wikipediagang.model.Person;

public class UnknowException extends RuntimeException{
    public UnknowException(String message, Person person){
        super(message);
    }
}
