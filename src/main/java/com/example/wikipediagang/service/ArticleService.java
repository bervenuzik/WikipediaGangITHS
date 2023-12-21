package com.example.wikipediagang.service;


import com.example.wikipediagang.ScannerHelper;
import com.example.wikipediagang.model.Article;
import com.example.wikipediagang.model.ArticleBorrowerInfo;
import com.example.wikipediagang.model.ArticleCategory;
import com.example.wikipediagang.model.ArticleHardCopy;
import com.example.wikipediagang.model.ArticleReservationQueue;
import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.repo.ArticleBorrowerInfoRepo;
import com.example.wikipediagang.repo.ArticleCategoryRepo;
import com.example.wikipediagang.repo.ArticleHardCopyRepo;
import com.example.wikipediagang.repo.ArticleRepo;
import com.example.wikipediagang.repo.ArticleReservationQueueRepo;
import com.example.wikipediagang.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleService {

    private static final int MAX_NUM_HARD_COPIES_PER_ARTICLE = 6;

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private ArticleHardCopyRepo hardCopyRepo;

    @Autowired
    private ArticleBorrowerInfoRepo borrowerInfoRepo;

    @Autowired
    private ArticleCategoryRepo categoryRepo;

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private MessageHandlerService log;

    @Autowired
    private MenuService menuService;

    @Autowired
    private ArticleReservationQueueRepo queueRepo;

    public void createArticle(Person loggedInPerson){

        log.message("Enter article's title: ");
        String title = ScannerHelper.getStringInput();

        log.message("Enter article's content (please write THE-END at the end of your content): ");
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
        System.out.println("!! New Article '" + newArticle.getTitle() + "'" + " is successfully saved !!");
    }

   /* public void searchAnArticle(Person loggedInPerson) {
        menuService.searchArticleMenu();

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
                    Article chosenArticle;
                    if (desiredArticles.isEmpty()) {
                        System.out.println("!! Desired article NOT found !!");
                        return;
                    } else if (desiredArticles.size() == 1) {
                        chosenArticle = desiredArticles.get(0);
                    } else {
                        System.out.println("Desired article(s): " + desiredArticles);
                        chosenArticle = chooseAnArticleFromAList(desiredArticles);
                    }
                    optionsForReadingAnArticle(chosenArticle, loggedInPerson);
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
                    Article chosenArticle = chooseAnArticleFromAList(desiredArticles);
                    optionsForReadingAnArticle(chosenArticle, loggedInPerson);
                }
                default -> System.out.println("!! Invalid input !!");
            }
        } while(done);
    }*/

    public void searchArticleByTitle(Person loggedInPerson){
        List<Article> desiredArticles = findArticleByTitle();
        Article chosenArticle;
        if (desiredArticles.isEmpty()) {
            log.error("!! Desired article NOT found !!");
            return;
        } else if (desiredArticles.size() == 1) {
            chosenArticle = desiredArticles.get(0);
        } else {
            log.message("Desired article(s): " + desiredArticles);
            chosenArticle = chooseAnArticleFromAList("\\nChoose one of the following articles to read: ",desiredArticles);
        }
        optionsForReadingAnArticle(chosenArticle, loggedInPerson);

    }

    public void searchArticleByAuthor(Person loggedInPerson){
        List<Person> authorsWithSameNameList = findAuthorByName();    //more than one author can have the same name
        if (authorsWithSameNameList.isEmpty()) {
            log.error("!! Desired author NOT found !!");
            return;
        }
        List<Article> desiredArticles = new ArrayList<>();
        for (Person p : authorsWithSameNameList) {
            List<Article> allArticlesByAnAuthor = articleRepo.findArticleByPerson(p);
            for (int i = 0; i < allArticlesByAnAuthor.size(); i++) {
                desiredArticles.add(allArticlesByAnAuthor.get(i));
            }
        }
        Article chosenArticle = chooseAnArticleFromAList("\\nChoose one of the following articles to read: ", desiredArticles);
        optionsForReadingAnArticle(chosenArticle, loggedInPerson);
    }
    private List<Article> findArticleByTitle() {
        log.message("Enter title of the article: ");
        String chosenTitle = ScannerHelper.getStringInput();
        return articleRepo.findArticleByTitle(chosenTitle);
    }

    private List<Person> findAuthorByName() {
        log.message("Enter author's first name: ");
        String firstName = ScannerHelper.getStringInput();

        log.message("Enter author's last name: ");
        String lastName = ScannerHelper.getStringInput();

        return personRepo.allAuthorsWithSameName(firstName, lastName);
    }

    private Article chooseAnArticleFromAList(String message, List<Article> articlesList) {
        log.menu(message);
        printOptions(articlesList);
        log.message("Enter article number: ");
        int choosenArticleNum = ScannerHelper.getIntInput(articlesList.size()-1);

        return articlesList.get(choosenArticleNum-1);
    }
    private void printOptions(List<Article> articleList){
        for (int i = 0; i < articleList.size(); i++) {
            log.menu(i+1 + ". " + articleList.get(i).getTitle());
        }
    }

    private void readAnArticleOnline(Article chosenArticle) {
        int numOfViews = chosenArticle.getNumOfViews();
        chosenArticle.setNumOfViews(numOfViews + 1);
        articleRepo.save(chosenArticle);
        log.success("!! You can now read the desired article below !!");
        log.success("------------------------------------------------------------------------------------------");
        log.message("Title: " + chosenArticle.getTitle().toUpperCase() + "\nWritten by: " +
                chosenArticle.getPerson().getFirstName() +
                " " + chosenArticle.getPerson().getLastName() + "\n\n" + chosenArticle.getContent());
        log.success("\n------------------------------------------------------------------------------------------");
    }

    public void editAnArticleByUser(Person person) {
        List<Article> articlesList  = articleRepo.findArticleByPerson(person);

        if (articlesList.isEmpty()) {
            System.out.println("!! Article(s) NOT found !!");
            return;
        }
        Article chosenArticle;
        if (articlesList.size() > 1) {
            chosenArticle = chooseAnArticleFromAList("Choose one of the following", articlesList);
            System.out.println("Choose one of the following: ");
         /*   for (int i = 0; i < articlesList.size(); i++) {
                System.out.println(i + ". " + articlesList.get(i).getTitle());
            }
            System.out.print("Enter article number: ");
            int chosenArticleNum = ScannerHelper.getIntInput(articlesList.size() - 1);
            chosenArticle = articlesList.get(chosenArticleNum);*/
        } else {
            chosenArticle = articlesList.get(0);
        }

        boolean isDone = true;
        do {
            System.out.println("""
                    Edit one of the following:
                    1. Title
                    2. Content
                    ENTER 0, to exit""");
            System.out.print("Enter you choice: ");
            int userChoice = ScannerHelper.getIntInput(2);
            switch (userChoice) {
                case 0 -> isDone = false;
                case 1 -> editTitle(chosenArticle);
                case 2 -> editContent(chosenArticle);
            }
        } while (isDone);
    }
    public void editTitle(Article chosenArticle){
        log.message("Current title: " + chosenArticle.getTitle());
        log.message("Enter new title: ");
        String updatedTitle = ScannerHelper.getStringInput();
        chosenArticle.setTitle(updatedTitle);
        articleRepo.save(chosenArticle);
        log.success("!! Article's TITLE is successfully updated !!");
    }

    public void editContent(Article chosenArticle){
        log.message("Enter new content (please write THE-END at the end of your content): ");
        String updatedContent = ScannerHelper.getTextInput();
        chosenArticle.setContent(updatedContent);
        articleRepo.save(chosenArticle);
        log.success("!! Article's CONTENT is successfully updated !!");
    }


    public void deleteAnArticleByAdmin() {
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

        System.out.println("!! Article '" + chosenArticle.getTitle() + "' is successfully DELETED !!");
        articleRepo.delete(chosenArticle);
    }

    private void optionsForReadingAnArticle(Article article,Person person) {
        //TODO: make menu
        boolean isDone = true;
        do {
            System.out.println("""
                \nChoose one of the following:
                1. Read Online
                2. Reserve a Hard-Copy
                ENTER 0, to exit""");
            System.out.print("Enter your choice: ");
            int readingChoice = ScannerHelper.getIntInput(2);

            switch (readingChoice) {
                case 0 -> isDone = false;
                case 1 -> {
                    readAnArticleOnline(article);
                    isDone = false;
                }
                case 2 -> reserveHardCopy(article, person);
                default -> System.out.println("Invalid Input");
            }
        } while (isDone);
    }

    /*
    private void reserveHardCopy(Article article, Person person) {
        List<ArticleHardCopy> availableHardCopies = hardCopyRepo.findNumOfHardCopiesByArticleAndStatus(article, "available");
        if (availableHardCopies.isEmpty()) {
            LocalDate latestDateToReserveHardCopy = getLatestReturnDateFromReservedHardCopiesOfAnArticle(article).plusDays(1);
            System.out.println("!! No hard-copies available now but you can reserve one on " + latestDateToReserveHardCopy + " !!");
            return;
        }
        ArticleHardCopy hardCopyToBeReserved = availableHardCopies.get(0);
        hardCopyToBeReserved.setStatus("reserved");
        ArticleBorrowerInfo borrowerInfo = new ArticleBorrowerInfo(hardCopyToBeReserved, person);
        borrowerInfoRepo.save(borrowerInfo);
        hardCopyRepo.save(hardCopyToBeReserved);
        System.out.println("!! A hard-copy has been RESERVED successfully till the following date " +
                borrowerInfo.getReturnDate() + " !!");
    }

     */

    //get latest return date from all 6 reservations of an article's hard-copies
    private LocalDate getLatestReturnDateFromReservedHardCopiesOfAnArticle(Article article) {
        List<ArticleHardCopy> numOfReservedHardCopies =
                hardCopyRepo.findNumOfHardCopiesByArticleAndStatus(article, "reserved");
        if (numOfReservedHardCopies.size() < 6) {
            int numOfAvailableHardCopies = 6 - numOfReservedHardCopies.size();
            System.out.println("No. of hard-copies available to reserve: " + numOfAvailableHardCopies);
        }

        List<ArticleBorrowerInfo> borrowerInfoList = borrowerInfoRepo.sortHardCopiesByReturnDate(article);
        return borrowerInfoList.get(0).getReturnDate();
    }

    private void reserveHardCopy(Article article, Person person) {
        //int totalNumOfHardCopies = hardCopyRepo.findNumberOfHardCopiesByArticleId(article.getId());
        List<ArticleHardCopy> listOfAvailableHardCopies = hardCopyRepo.findNumOfHardCopiesByArticleAndStatus(article, "available");
        List<ArticleHardCopy> listOfReservedHardCopies = hardCopyRepo.findNumOfHardCopiesByArticleAndStatus(article, "reserved");

        if (!listOfAvailableHardCopies.isEmpty()) {
            ArticleHardCopy articleHardCopyToBeReserved = listOfAvailableHardCopies.get(0);
            articleHardCopyToBeReserved.setStatus("reserved");
            hardCopyRepo.save(articleHardCopyToBeReserved);
            ArticleBorrowerInfo articleBorrowerInfo = new ArticleBorrowerInfo(articleHardCopyToBeReserved, person);
            borrowerInfoRepo.save(articleBorrowerInfo);
            System.out.println("\n!! A hard-copy has been RESERVED successfully till the following date " +
                    articleBorrowerInfo.getReturnDate() + " !!");
        } else if (!listOfReservedHardCopies.isEmpty() && listOfReservedHardCopies.size() == MAX_NUM_HARD_COPIES_PER_ARTICLE) {
            ArticleReservationQueue articleReservationQueue = new ArticleReservationQueue(article, person);
            queueRepo.save(articleReservationQueue);
            System.out.println("""
                    NOTE- 
                    No hard-copy is available right now!
                    You've been added in a queue & 
                    a RESERVATION will be made as soon 
                    as any hard-copy gets available. \n""");
        } else {
            ArticleHardCopy articleHardCopy = new ArticleHardCopy(article);
            articleHardCopy.setStatus("reserved");
            hardCopyRepo.save(articleHardCopy);
            article.setAvailableAsHardCopy("yes");
            articleRepo.save(article);
            ArticleBorrowerInfo articleBorrowerInfo = new ArticleBorrowerInfo(articleHardCopy, person);
            borrowerInfoRepo.save(articleBorrowerInfo);
            System.out.println("\n!! A hard-copy has been RESERVED successfully till the following date " +
                    articleBorrowerInfo.getReturnDate() + " !!");
        }
    }

    public void showReservedArticles(Person person) {

    }

    public void returnReservedArticle(Person person) {

    }
}
