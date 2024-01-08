package com.example.wikipediagang.service;


import com.example.wikipediagang.menu.*;
import com.example.wikipediagang.ScannerHelper;
import com.example.wikipediagang.model.Article;
import com.example.wikipediagang.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class MenuService {
    @Autowired
    PersonService pService;
    @Autowired
    ArticleService articleService;
    Optional<Person> currentUser = Optional.empty();
    @Autowired
    MessageHandlerService log;


    public void startMenu() {
        log.menu("""
                ----------------------------------------------------------------------------------------
                \nHey, Welcome to ITHS Wikipedia!
                                
                """);

        main:
        while (true) {
            while (currentUser.isEmpty()) {
                // System.out.println(pService.getAllUsers());
                StartMenu userChoice = getUserChoice(StartMenu.values());
                switch (userChoice) {
                    case EXIT -> {
                        System.out.println("Thank you for using ITHS wikipedia! ");
                        break main;
                    }
                    case LOGIN -> login();
                    default -> log.error("wrong input");
                }
            }


            if (currentUser.get().getType().getName().equals("Admin")) {
                adminMenu();
            } else if (currentUser.get().getType().getName().equals("developer")) {
                developerMenu();
            } else {
                userMenu();
            }

        }
    }

    public void adminMenu() {
        log.menu("""
                ----------------------------------------------------------------------------------------
                                
                Welcome to the Admin view!              
                """);

        while (currentUser.isPresent()) {
            AdminMenu userChoice = getUserChoice(AdminMenu.values());
            switch (userChoice) {
                case LOGOUT -> currentUser = Optional.empty();
                case ADD_USER -> pService.createUser(currentUser);
                case REMOVE_USER -> pService.deleteUser(currentUser);
                case EDIT_USER -> pService.editUser(currentUser);
                case SEARCH -> searchArticleMenu();
                case WRITE -> articleService.createArticle(currentUser.get());
                case DELETE -> articleService.deleteAnArticle();
                case EDIT_CATEGORY_LIST -> articleService.updateNameOfAnExistingCategory();
                case REVIEW ->  articleService.reviewArticle();
            }
        }

    }

    public void userMenu() {
        log.menu("""
                ----------------------------------------------------------------------------------------
                               
                Welcome to the user view!                
                """);
        while (currentUser.isPresent()) {
            UserMenu userChoice = getUserChoice(UserMenu.values());
            switch (userChoice) {
                case LOGOUT -> currentUser = Optional.empty();
                case CHANGE_PASSWORD -> pService.changePassword(currentUser.get());
                case CHANGE_EMAIL -> pService.changeEmail(currentUser.get());
                case SEARCH -> searchArticleMenu();
                case WRITE -> articleService.createArticle(currentUser.get());
                case EDIT -> {
                    Article article = articleService.editAnArticleByUser(currentUser.get());
                    editArticleMenu(article);
                }
                case SHOW_RESERVED_HARD_COPIES -> articleService.showHardCopiesReservedByAPerson(currentUser.get());
                case SHOW_RESERVATIONS_IN_QUEUE -> articleService.showArticlesReservedInQueue(currentUser.get());
                case RETURN_RESERVED -> articleService.returnReservedHardCopyOfAnArticle(currentUser.get());
            }

        }

    }



    public void developerMenu(){
        log.menu("""
                ----------------------------------------------------------------------------------------
                                
                Welcome to the developer view!                
                """);
        boolean logout = false;
        while (!logout){
            DeveloperMenu userChoice = getUserChoice(DeveloperMenu.values());
            switch (userChoice){
                case LOGOUT -> logout= true;
//                case ERROR_LOG -> printErrors();
//                case ERROR_HANDLE -> handleErrors();
            }
        }

    }

    public void searchArticleMenu(){
        boolean exit = false;
        while(!exit){
            log.menu("""
                    ----------------------------------------------------------------------------------------
                    
                    Here you can SEARCH an article!
                    """);
             SearchArticleMenu userChoice = getUserChoice(SearchArticleMenu.values());
            switch (userChoice){
                case EXIT -> exit = true;
                case TITLE -> articleService.searchArticleByTitle(currentUser.get());
                case AUTHOR -> articleService.searchArticleByPerson(currentUser.get());
            }
        }

    }


    public void editArticleMenu(Article article){
        boolean exit = false;
        while(!exit){
            log.menu("""
                    ----------------------------------------------------------------------------------------
                    
                    Here you can EDIT an article!
                    """);
            EditArticleMenu userChoice = getUserChoice(EditArticleMenu.values());
            switch (userChoice){
                case EXIT -> exit= true;
                case TITLE -> articleService.editTitle(article);
                case CONTENT -> articleService.editContent(article);
                case CATEGORY -> articleService.editCategory(article);
            }
        }
    }

    public <T extends MenuOption> T getUserChoice(T[] options) {
        log.menu("\nChoose from the following tasks- \n");
        printChoices(options);
        log.message("\nEnter your choice:");
        int userChoice = ScannerHelper.getIntInput(options.length);
        return options[userChoice - 1];

    }

    private <T extends MenuOption> void printChoices(T[] options) {
        int choicenumber = 1;
        for (T menuChoice : options) {
            log.menu(choicenumber + ". " + menuChoice.getDisplayText());
            choicenumber++;
        }
    }

    private void login() {
        Optional<Person> opPerson = Optional.empty();
        if(currentUser.isEmpty()) opPerson = pService.login();
        else {
            log.error("You are already in-logged");
            return;
        }
        if (opPerson.isPresent()) {
            currentUser = opPerson;
        }

    }
}

