package com.example.wikipediagang;

import com.example.wikipediagang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyMain implements CommandLineRunner {

    @Autowired
    private MenuService menuService;

    @Override
    public void run(String... args) throws Exception {
        menuService.startMenu();
    }
}
