package com.example.wikipediagang;

import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.repo.PersonRepository;
import com.example.wikipediagang.service.ArticleService;
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


    @Override
    public void run(String... args) throws Exception {

        boolean isDone = true;

        do {
            System.out.println("""
                ----------------------------------------------------------------------------------------
                \nHey, Welcome to ITHS Wikipedia!
                \nChoose from the following tasks-
                To EXIT, enter 0
                1. Create New User Account
                2. Login""");
            System.out.print("\nEnter your choice: ");
            int userInput = ScannerHelper.getIntInput(2);
            switch (userInput) {
                case 0 -> isDone = false;
                case 1 -> createUserAccount();
                case 2 -> login();
                default -> System.out.println("Invalid Input");
            }
        } while (isDone);

        ScannerHelper.close();
        System.out.println("""
                \nThank you for using the program.
                Have a nice day! :)
                ----------------------------------------------------------------------------------------""");

    }

    private void createUserAccount(){

    }

    private void login(){
        //TODO add code to validate username and password
        // If valid user, then get the User from the PersonRepo and
        // call showUserMenu() with the User

        Optional<Person> opPerson = personRepo.findById(3);
        if (opPerson.isPresent()) {
            Person p = opPerson.get();
            showUserMenu(p);
        }


    }

    private void showUserMenu(Person loggedInPerson){
        boolean isDone = true;

        do {
            System.out.println("""
                ----------------------------------------------------------------------------------------
                \nHello Friend, Welcome back!
                Choose from the following tasks-
                To EXIT, enter 0
                1. Edit Account
                2. See Article Menu""");
            System.out.print("\nEnter your choice: ");
            int userInput = ScannerHelper.getIntInput(2);
            switch (userInput) {
                case 0 -> isDone = false;
                case 1 -> editUserAccount(loggedInPerson);           //update first name, last name, email
                case 2 -> showArticleMenu(loggedInPerson);
                default -> System.out.println("Invalid Input");
            }
        } while (isDone);
    }

    private void editUserAccount(Person p) {

    }

    private void showArticleMenu(Person p) {
        articleService.startMenu(p);
    }


}
