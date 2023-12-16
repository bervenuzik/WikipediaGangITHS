package com.example.wikipediagang;

import com.example.wikipediagang.repo.ArticleCategoryRepo;
import com.example.wikipediagang.repo.ArticleRepo;
import com.example.wikipediagang.repo.ArticleStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyMain implements CommandLineRunner {

    @Autowired
    ArticleRepo articleRepo;

    @Autowired
    ArticleStatusRepo statusRepo;

    @Autowired
    ArticleCategoryRepo categoryRepo;

    @Override
    public void run(String... args) throws Exception {

    }
}
