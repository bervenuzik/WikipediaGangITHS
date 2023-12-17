package com.example.wikipediagang.service;


import com.example.wikipediagang.Person;
import com.example.wikipediagang.PersonRepository;
import com.example.wikipediagang.ScannerHelper;
import com.example.wikipediagang.model.Article;
import com.example.wikipediagang.model.ArticleCategory;
import com.example.wikipediagang.repo.ArticleCategoryRepo;
import com.example.wikipediagang.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private ArticleCategoryRepo categoryRepo;

    @Autowired
    private PersonRepository personRepo;

    public void startMenu(Person loggedInPerson){
        boolean isDone = true;

        do {
            int userInput = receiveUserInput();
            switch (userInput) {
                case 0 -> isDone = false;
                case 1 -> searchAnArticle();
                case 2 -> createArticle(loggedInPerson);
                case 3 -> editAnArticle();
                case 4 -> reserveAHardCopy();
                default -> System.out.println("Invalid Input");
            }
        } while (isDone);
    }

    private int receiveUserInput() {
        System.out.println("""
                ----------------------------------------------------------------------------------------
                \nHey, Welcome to ITHS Wikipedia!
                \nChoose from the following tasks-
                To EXIT, enter 0
                1. Search an Article
                2. Write an Article
                3. Edit an Article
                4. Reserve a hard-copy of an Article""");
        System.out.print("\nEnter your choice: ");
        return ScannerHelper.getIntInput(4);
    }

    public void createArticle(Person loggedInPerson){

        System.out.print("Enter article's title: ");
        String title = ScannerHelper.getStringInput();

        System.out.print("Enter article's content: ");
        String content =  ScannerHelper.getStringInput();

        System.out.println("Choose article's category from the following: ");
        List<ArticleCategory> availableCategories = categoryRepo.findAll();

        for(int i=0; i< availableCategories.size(); i++){
            System.out.println(i + ") " + availableCategories.get(i).getName());
        }

        System.out.print("\nEnter category's number: ");
        int categoryId = ScannerHelper.getIntInput(availableCategories.size()-1);

        ArticleCategory chosenCategory = availableCategories.get(categoryId);

        Article newArticle = new Article(title, content, loggedInPerson, chosenCategory);
        articleRepo.save(newArticle);
        System.out.println("New Article \'" + newArticle.getTitle() + "\'" + " is successfully saved");
    }

    public void searchAnArticle() {

        boolean done = true;
        do {
            System.out.println("""
                \nSearch an article by:
                1. Title
                2. Author
                ENTER 0, to exit""");
            System.out.print("\nEnter your choice: ");
            int userChoice = ScannerHelper.getIntInput(2);

            switch(userChoice) {
                case 0 -> done = false;
                case 1 -> {
                    List<Article> desiredArticles = findArticleByTitle();
                    if (desiredArticles.isEmpty()) {
                        System.out.println("!! Desired article NOT found !!");
                        return;
                    }
                    System.out.println("Desired article(s): " + desiredArticles);
                }
                case 2 -> {
                    List<Person> allAuthorsWithDesiredName = findAuthorByName();    //more than one author can have the same name
                    if (allAuthorsWithDesiredName.isEmpty()) {
                        System.out.println("!! Desired author NOT found !!");
                        return;
                    }

                    for (Person p : allAuthorsWithDesiredName) {
                        List<Article> allArticlesByAnAuthor = articleRepo.findArticleByPerson(p);
                        System.out.println("List of articles: \n" + allArticlesByAnAuthor);
                    }
                }
                default -> System.out.println("!! Invalid input !!");
            }
        } while(done);

    }

    private List<Article> findArticleByTitle() {
        System.out.print("Enter title of the article: ");
        String chosenTitle = ScannerHelper.getStringInput();
        List<Article> articlesWithSameName = articleRepo.findArticleByTitle(chosenTitle);
        if (articlesWithSameName.isEmpty()) {
            System.out.println("!! Article with desired title NOT found !!");
        }
        return articlesWithSameName;
    }

    private List<Person> findAuthorByName() {
        System.out.print("Enter author's first name: ");
        String firstName = ScannerHelper.getStringInput();

        System.out.print("Enter author's last name: ");
        String lastName = ScannerHelper.getStringInput();

        List<Person> personsWithSameName = personRepo.allAuthorsWithName(firstName, lastName);
        if (personsWithSameName.isEmpty()) {
            System.out.println("!! Desired author NOT found !!");
        }
        return personsWithSameName;
    }

    private void editAnArticle() {

    }

    private void reserveAHardCopy() {

    }

}
