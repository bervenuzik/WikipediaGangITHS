package com.example.wikipediagang;

import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.repo.PersonRepository;
import com.example.wikipediagang.service.ArticleService;
import com.example.wikipediagang.service.MassageHandlerService;
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
    private Optional<Person> currentUser = Optional.empty();
    private final MassageHandlerService log = new MassageHandlerService();


    @Override
    public void run(String... args) throws Exception {

        boolean isDone = true;

        do {
            log.menu(" ----------------------------------------------------------------------------------------");
            log.menu("\nHey, Welcome to ITHS Wikipedia!\nChoose from the following tasks-\nTo EXIT, enter 0");

            if(currentUser.isPresent())log.menu("1. To login out");
            else log.menu("1. To Login");

            System.out.print("\nEnter your choice: ");
            int userInput = ScannerHelper.getIntInput(2);
            switch (userInput) {
                case 0 -> isDone = false;
                case 1 -> {
                    if (currentUser.isPresent()) {
                        loginOut();
                    } else {
                        login();
                    }
                }
                default -> System.out.println("Invalid Input");
            }

            if(currentUser.isPresent()){
                showUserMenu();
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
        Optional<Person> user;
        user = pService.login();
        if(user.isPresent()){
            currentUser = user;
        }
    }
    private void loginOut(){
        currentUser = Optional.empty();
        log.warning("You now is logged out");
    }

    private void showUserMenu(){
        boolean isDone = true;
        if(currentUser.isPresent()) {
            do {
                log.menu("""
                        ----------------------------------------------------------------------------------------
                        \n
                        To EXIT, enter 0
                        1. Edit Account
                        2. See Article Menu
                        \nEnter your choice:""");

                int userInput = ScannerHelper.getIntInput(2);
                switch (userInput) {
                    case 0 -> isDone = false;
                    case 1 -> editUserAccount(currentUser.get());           //update first name, last name, email
                    case 2 -> showArticleMenu(currentUser.get());
                    default -> System.out.println("Invalid Input");
                }
            } while (isDone);
        }
    }

    private void editUserAccount(Person p) {
        //pService.createUser();
    }

    private void showArticleMenu(Person p) {
        articleService.startMenu(p);
    }


}
