package com.example.wikipediagang;

import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.repo.PersonRepository;
import com.example.wikipediagang.service.ArticleService;
import com.example.wikipediagang.service.MenuService;
import com.example.wikipediagang.service.MessageHandlerService;
import com.example.wikipediagang.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyMain implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private PersonService pService;

    @Autowired
    private MenuService menuService;

    private final MessageHandlerService log = new MessageHandlerService();


    @Override
    public void run(String... args) throws Exception {
        menuService.startMenu();
    }



}
