package com.example.wikipediagang.service;


import com.example.wikipediagang.menu.*;
import com.example.wikipediagang.ScannerHelper;
import com.example.wikipediagang.model.Article;
import com.example.wikipediagang.model.ArticleReservationQueue;
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
            //developerMenu();
        }
        else {
            userMenu();
        }



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
                case ADD_USER -> pService.createUser(currentUser);
                //case REMOVE_USER -> pService.deleteUser(currentUser);
                case EDIT_USER -> pService.editAuthor();
                case SEARCH -> searchArticleMenu();
                case WRITE -> articleService.createArticle(currentUser.get());
                // case DELETE -> articleService.deleteAnArticleByAdmin();
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
                case CHANGE_PASSWORD -> pService.inputNewPassword();
                case CHANGE_EMAIL -> pService.inputNewEmail();
                case SEARCH -> searchArticleMenu();
                case WRITE -> articleService.createArticle(currentUser.get());
                case CHANGE -> { Article article = articleService.editAnArticleByUser(currentUser.get());
                    if ( article != null){
                        editArticleMenu(article);
                    }
                }
                // case SHOW_RESERVED -> articleService.showReservedArticles(currentUser.get());
                //case RETURN_RESERVED -> articleService.returnReservedArticle(currentUser.get());
            }

        }

    }

    public void searchArticleMenu(){
        boolean exit = false;
        while(!exit){
            log.menu("""
                    ----------------------------------------------------------------------------------------
                    
                    Here you can search an article!
                    """);
             SearchArticleMenu userChoice =getUserchoice(SearchArticleMenu.values());
            switch (userChoice){
                case EXIT -> exit = true;
                case TITLE -> articleService.searchArticleByTitle(currentUser.get());
                case AUTHOR -> articleService.searchArticleByAuthor(currentUser.get());
            }
        }

    }


    public void editArticleMenu(Article article){
        boolean exit = false;
        while(!exit){
            log.menu("""
                    ----------------------------------------------------------------------------------------
                    
                    Here you can edit an article!
                    """);
            EditArticleMenu userChoice = getUserchoice(EditArticleMenu.values());
            switch (userChoice){
                case EXIT -> exit= true;
                case TITLE -> articleService.editTitle(article);
                case CONTENT -> articleService.editContent(article);
            }
        }
    }

    public <T extends MenuOption> T getUserchoice(T[] options) {
        log.menu("Choose from the following tasks- \n");
        printchoices(options);
        log.message("\nEnter your choice:");
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

