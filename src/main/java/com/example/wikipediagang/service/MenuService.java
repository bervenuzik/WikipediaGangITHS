package com.example.wikipediagang.service;


import com.example.wikipediagang.menu.*;
import com.example.wikipediagang.ScannerHelper;

public class MenuService {
    public void startMenu() {
        System.out.println("""
                ----------------------------------------------------------------------------------------
                \nHey, Welcome to ITHS Wikipedia!
                Choose from the following tasks-
                """);

        boolean exitMenu = false;

        while (!exitMenu) {
            StartMenu userChoice = getUserchoice(StartMenu.values());
            switch (userChoice) {
                case EXIT -> exitMenu = true;
                //case ADMIN -> // login as admin
                //case USER ->  // login as user
            }

        }
        System.out.println("Thank you for using ITHS wikipedia! ");
    }

    public void adminMenu() {
        System.out.println("""
                ----------------------------------------------------------------------------------------
                                
                Welcome to the Admin view! 
                Choose from the following tasks- 
                """);
        boolean LoggedOut = false;
        while (!LoggedOut) {
            AdminMenu userchoice = getUserchoice(AdminMenu.values());
            switch (userchoice) {
                case LOGOUT -> LoggedOut = true;
                // case ADD_USER -> addUser()
                //case REMOVE_USER -> removeUser();
                //case EDIT_USER -> editUser();
                //case CHANGE_PRIVILEGES -> changePrivilages();
                case ARTICLE -> adminArticleMenu();
            }
        }

    }

    public void adminArticleMenu() {
        System.out.println("""
                ----------------------------------------------------------------------------------------
                                
                Welcome to the Admin Article view!
                Choose from the following tasks-
                """);
        boolean returnToAdminMenu = false;
        while (!returnToAdminMenu) {
            AdminArticleMenu userChoice = getUserchoice(AdminArticleMenu.values());
            switch (userChoice) {
                case RETURN -> returnToAdminMenu = true;
//                case SEARCH -> searchArticle();
//                case WRITE -> writeArticle();
//                case CHANGE ->  changeArticle();
//                case DELETE -> deleteArticle();
//                case REVIEW ->  reviewArticle();
//                case RESERVE -> reserveArticle();
//                case ORDER -> orderArticle();
            }
        }

    }

    public void userMenu() {
        System.out.println("""
                ----------------------------------------------------------------------------------------
                               
                Welcome to the user view!
                Choose from the following tasks-
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
        System.out.println("""
                ----------------------------------------------------------------------------------------
                                
                Welome to the user Article view!
                Choose from the following tasks-
                """);
        boolean returnToUserMenu = false;
        while (!returnToUserMenu) {
            UserArticleMenu userChoice = getUserchoice(UserArticleMenu.values());
            switch (userChoice) {
                case RETURN -> returnToUserMenu = true;
//                case SEARCH -> searchArticle();
//                case WRITE -> writeArticle();
//                case CHANGE ->  changeArticle();
//                case RESERVE -> reserveArticle();
//                case ORDER -> orderArticle();

            }

        }
    }

    public void developerMenu(){
        System.out.println("""
                ----------------------------------------------------------------------------------------
                                
                Welome to the developer view!
                Choose from the following tasks-
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
        System.out.println();
        System.out.println("Enter the number of your choice and press enter.");
        printchoices(options);
        System.out.println("Enter your choice:");
        int userChoice = ScannerHelper.getIntInput(options.length);
        return options[userChoice - 1];

    }

    private <T extends MenuOption> void printchoices(T[] options) {
        int choicenumber = 1;
        for (T menuChoice : options) {
            System.out.println(choicenumber + ". " + menuChoice.getDisplayText());
            choicenumber++;
        }
    }
}
