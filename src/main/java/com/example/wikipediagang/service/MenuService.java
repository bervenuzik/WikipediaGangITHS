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

    //private final MessageHandlerService log = new MessageHandlerService();

    public void startMenu() {
        log.menu("""
                ----------------------------------------------------------------------------------------
                \nHey, Welcome to ITHS Wikipedia!
                
                """);


        while (currentUser.isEmpty()) {
            System.out.println(pService.getAllUsers());
            StartMenu userChoice = getUserchoice(StartMenu.values());
            switch (userChoice) {
                case EXIT -> System.exit(1);
                case LOGIN ->  login();
                default -> log.error("wrong input");
            }
        }

        if(currentUser.get().getType().getName().equals("admin")){
            adminMenu();
        } else if (currentUser.get().getType().getName().equals("developer")) {
            developerMenu();
        }
        else {
            userMenu();
        }


        //pService.createUser(currentUser);
        System.out.println("Thank you for using ITHS wikipedia! ");
    }

    public void adminMenu() {
        log.menu("""
                ----------------------------------------------------------------------------------------
                                
                Welcome to the Admin view!              
                """);
        boolean LoggedOut = false;
        while (!LoggedOut) {
            AdminMenu userchoice = getUserchoice(AdminMenu.values());
            switch (userchoice) {
                case LOGOUT -> LoggedOut = true;
                //case ADD_USER -> addUser(
                //case REMOVE_USER -> removeUser();
                //case EDIT_USER -> editUser();
                //case CHANGE_PRIVILEGES -> changePrivilages();
                case ARTICLE -> adminArticleMenu();
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
        boolean loggout = false;
        while (!loggout) {
            UserMenu userChoice = getUserchoice(UserMenu.values());
            switch (userChoice) {
                case LOUGOUT -> loggout = true;
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
        boolean logout = false;
        while (!logout){
            DeveloperMenu userChoice = getUserchoice(DeveloperMenu.values());
            switch (userChoice){
                case LOGOUT -> logout= true;
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

