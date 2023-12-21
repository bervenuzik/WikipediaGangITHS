package com.example.wikipediagang.service;


import com.example.wikipediagang.menu.*;
import com.example.wikipediagang.ScannerHelper;
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


       main: while (true) {
            while (currentUser.isEmpty()) {
                System.out.println(pService.getAllUsers());
                StartMenu userChoice = getUserchoice(StartMenu.values());
                switch (userChoice) {
                    case EXIT -> {break main;}
                    case LOGIN ->  currentUser = pService.login();
                    default -> log.error("wrong input");
                }
            }

            if(currentUser.isPresent() && currentUser.get().getType().getName().equals("admin")){
                adminMenu();
            } else if (currentUser.get().getType().getName().equals("developer")) {
                developerMenu();
            }
            else {
                userMenu();
            }

        }

        log.menu("""
    
    Thanks for using Wikipedia ITHS , see you later.
    --------------------GOOD BY---------------------
    """);

        System.out.println("Thank you for using ITHS wikipedia! ");
    }

    public void adminMenu() {
        log.menu("""
                ----------------------------------------------------------------------------------------
                                
                Welcome to the Admin view!              
                """);
        while (currentUser.isPresent() && currentUser.get().getType().getName().equals("admin")) {
            AdminMenu userchoice = getUserchoice(AdminMenu.values());
            switch (userchoice) {
                case LOGOUT -> currentUser = pService.loginOut();
                case ADD_USER -> pService.createUser(currentUser);
                //case REMOVE_USER -> pService.deleteUser(curentUser);
                //case EDIT_USER -> pService.editAuthor();
                //case CHANGE_PRIVILEGES -> changePrivilages();
                case ARTICLE -> adminArticleMenu();
                default -> log.error("\nWrong input, try again\n");
            }
        }

    }

    public void adminArticleMenu() {
        log.menu("""
                ----------------------------------------------------------------------------------------
                                
                Welcome to the Admin Article view!                
                """);
        boolean returnToAdminMenu = false;
        while (!returnToAdminMenu) {
            AdminArticleMenu userChoice = getUserchoice(AdminArticleMenu.values());
            switch (userChoice) {
                case RETURN -> returnToAdminMenu = true;
                case SEARCH -> articleService.searchAnArticle(currentUser.get());
                case WRITE -> articleService.createArticle(currentUser.get());
                case CHANGE ->  articleService.editAnArticle();
                case DELETE -> articleService.deleteAnArticle();
//                case REVIEW ->  reviewArticle();

            }
        }

    }

    public void userMenu() {
        log.menu("""
                ----------------------------------------------------------------------------------------
                               
                Welcome to the user view!                
                """);

        while (currentUser.isPresent() && currentUser.get().getType().getName().equals("reader")){
            UserMenu userChoice = getUserchoice(UserMenu.values());
            switch (userChoice) {
                case LOUGOUT -> currentUser = pService.loginOut();
//                case CHANGE_PASSWORD -> changePassword();
//                case CHANGE_EMAIL -> changeEmail();
               case ARTICLE -> userArticleMenu();
            }
        }
    }

    public void userArticleMenu() {
        log.menu("""
                ----------------------------------------------------------------------------------------
                                
                Welcome to the user Article view!                
                """);
        boolean returnToUserMenu = false;
        while (!returnToUserMenu) {
            UserArticleMenu userChoice = getUserchoice(UserArticleMenu.values());
            switch (userChoice) {
                case RETURN -> returnToUserMenu = true;
                case SEARCH -> articleService.searchAnArticle(currentUser.get());
                case WRITE -> articleService.createArticle(currentUser.get());
                case CHANGE ->  articleService.editAnArticle();
            }

        }
    }

    public void developerMenu(){
        log.menu("""
                ----------------------------------------------------------------------------------------
                                
                Welcome to the developer view!                
                """);

        while (currentUser.isPresent() && currentUser.get().getType().getName().equals("author")){
            DeveloperMenu userChoice = getUserchoice(DeveloperMenu.values());
            switch (userChoice){
                case LOGOUT -> currentUser = pService.loginOut();
//                case ERROR_LOG -> printErrors();
//                case ERROR_HANDLE -> handleErrors();
            }
        }

    }

    public void studentMenu(){

    }

    private <T extends MenuOption> T getUserchoice(T[] options) {
        log.menu("Choose from the following tasks- \n");
        printchoices(options);
        log.menu("\nEnter your choice:");
        int userChoice = ScannerHelper.getIntInput(options.length);
        return options[userChoice - 1];

    }

    private <T extends MenuOption> void printchoices(T[] options) {
        int choicenumber = 1;
        for (T menuChoice : options) {
            log.menu(choicenumber + ". " + menuChoice.getDisplayText());
            choicenumber++;
        }
    }


}

