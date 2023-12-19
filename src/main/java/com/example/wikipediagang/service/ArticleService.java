package com.example.wikipediagang.service;


import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.repo.PersonRepository;
import com.example.wikipediagang.ScannerHelper;
import com.example.wikipediagang.model.Article;
import com.example.wikipediagang.model.ArticleCategory;
import com.example.wikipediagang.repo.ArticleCategoryRepo;
import com.example.wikipediagang.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
                case 4 -> deleteAnArticle();
                case 5 -> reserveAHardCopy();
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
                1. Search/Read an Article
                2. Write an Article
                3. Edit an Article
                4. Delete an Article
                5. Reserve a hard-copy of an Article""");
        System.out.print("\nEnter your choice: ");
        return ScannerHelper.getIntInput(4);
    }

    public void createArticle(Person loggedInPerson){

        System.out.print("Enter article's title: ");
        String title = ScannerHelper.getStringInput();

        System.out.println("Enter article's content (please write THE-END at the end of your content): ");
        String content =  ScannerHelper.getTextInput();

        System.out.println("Input Received = " + content);

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
        System.out.println("!! New Article \'" + newArticle.getTitle() + "\'" + " is successfully saved !!");
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
                    readAnArticle(desiredArticles);
                }
                case 2 -> {
                    List<Person> authorsWithSameNameList = findAuthorByName();    //more than one author can have the same name
                    if (authorsWithSameNameList.isEmpty()) {
                        System.out.println("!! Desired author NOT found !!");
                        return;
                    }

                    List<Article> desiredArticles = new ArrayList<>();
                    for (Person p : authorsWithSameNameList) {
                        List<Article> allArticlesByAnAuthor = articleRepo.findArticleByPerson(p);
                        for (int i = 0; i < allArticlesByAnAuthor.size(); i++) {
                            desiredArticles.add(allArticlesByAnAuthor.get(i));
                        }
                    }
                    readAnArticle(desiredArticles);
                }
                default -> System.out.println("!! Invalid input !!");
            }
        } while(done);
    }

    private List<Article> findArticleByTitle() {
        System.out.print("Enter title of the article: ");
        String chosenTitle = ScannerHelper.getStringInput();
        List<Article> articlesWithSameName = articleRepo.findArticleByTitle(chosenTitle);
        return articlesWithSameName;
    }

    private List<Person> findAuthorByName() {
        System.out.print("Enter author's first name: ");
        String firstName = ScannerHelper.getStringInput();

        System.out.print("Enter author's last name: ");
        String lastName = ScannerHelper.getStringInput();

        List<Person> listOfAuthorsWithSameName = personRepo.allAuthorsWithSameName(firstName, lastName);
        return listOfAuthorsWithSameName;
    }

    private void readAnArticle(List<Article> articlesList) {
        System.out.println("\nChoose one of the following articles to read: ");
        for (int i = 0; i < articlesList.size(); i++) {
            System.out.println(i + ". " + articlesList.get(i).getTitle());
        }
        System.out.print("Enter article number: ");
        int chosenArticleNum = ScannerHelper.getIntInput(articlesList.size() - 1);
        Article chosenArticle = articlesList.get(chosenArticleNum);
        int numOfViews = chosenArticle.getNumOfViews();
        chosenArticle.setNumOfViews(numOfViews + 1);
        articleRepo.save(chosenArticle);
        System.out.println("!! You can now read the desired article below !!");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Title: " + chosenArticle.getTitle().toUpperCase() + "\nWritten by: " + chosenArticle.getPerson().getFirstName() +
                " " + chosenArticle.getPerson().getLastName() + "\n\n" + chosenArticle.getContent());
        System.out.println("\n------------------------------------------------------------------------------------------");
    }

    private void editAnArticle() {
        List<Article> articlesList = findArticleByTitle();
        if (articlesList.isEmpty()) {
            System.out.println("!! Article NOT found !!");
            return;
        }
        Article chosenArticle;
        if (articlesList.size() > 1) {
            System.out.println("Choose one of the following: ");
            for (int i = 0; i < articlesList.size(); i++) {
                System.out.println(i + ". " + articlesList.get(i).getTitle());
            }
            System.out.print("Enter article number: ");
            int chosenArticleNum = ScannerHelper.getIntInput(articlesList.size() - 1);
            chosenArticle = articlesList.get(chosenArticleNum);
        } else {
            chosenArticle = articlesList.get(0);
        }
        boolean isDone = true;
        do {
            System.out.print("""
                    Edit one of the following:
                    1. Title
                    2. Content
                    ENTER 0, to exit
                    Enter you choice: """);
            int userChoice = ScannerHelper.getIntInput(2);
            switch (userChoice) {
                case 0 -> isDone = false;
                case 1 -> {
                    System.out.println("Current title: " + chosenArticle.getTitle());
                    System.out.print("Enter new title: ");
                    String updatedTitle = ScannerHelper.getStringInput();
                    chosenArticle.setTitle(updatedTitle);
                    articleRepo.save(chosenArticle);
                    System.out.println("!! Article's TITLE is successfully updated !!");
                }
                case 2 -> {
                    System.out.print("Enter new content (please write THE-END at the end of your content): ");
                    String updatedContent = ScannerHelper.getTextInput();
                    chosenArticle.setContent(updatedContent);
                    articleRepo.save(chosenArticle);
                    System.out.println("!! Article's CONTENT is successfully updated !!");
                }
            }
        } while (isDone);
    }

    private void deleteAnArticle() {
        List<Article> listOfAllArticlesWithSameName = findArticleByTitle();
        if (listOfAllArticlesWithSameName.isEmpty()) {
            System.out.println("!! Article NOT found !!");
            return;
        }
        Article chosenArticle;
        if (listOfAllArticlesWithSameName.size() > 1) {
            System.out.println("Desired article is written by following authors: ");
            for (int i = 0; i < listOfAllArticlesWithSameName.size(); i++) {
                Person person = listOfAllArticlesWithSameName.get(i).getPerson();
                String authorsFullName = person.getFirstName() + " " + person.getLastName();
                System.out.println(i + ". " + authorsFullName);
            }
            System.out.print("Enter number to remove article written by that author: ");
            int userChoice = ScannerHelper.getIntInput(listOfAllArticlesWithSameName.size() - 1);
            chosenArticle = listOfAllArticlesWithSameName.get(userChoice);
        } else {
            chosenArticle = listOfAllArticlesWithSameName.get(0);
        }

        System.out.println("!! Article \'" + chosenArticle.getTitle() + "\' is successfully DELETED !!");
        articleRepo.delete(chosenArticle);
    }

    private void reserveAHardCopy() {

    }

}