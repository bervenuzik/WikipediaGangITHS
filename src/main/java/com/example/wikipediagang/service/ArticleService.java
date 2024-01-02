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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ArticleService {

    private static final int MAX_NUM_HARD_COPIES_PER_ARTICLE = 3;            //allows max 3 hard-copies of an article

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
    private ArticleReservationQueueRepo queueRepo;

    @Autowired
    private SearchWordService searchWordService;

    public void createArticle(Person loggedInPerson){

        log.message("\nEnter article's title: ");
        String title = ScannerHelper.getStringInput();

        log.message("\nEnter article's content (please write THE-END at the end of your content): ");
        String content =  ScannerHelper.getTextInput();
        System.out.println("\nInput Received: \n" + content);

        ArticleCategory chosenCategory = chooseCategoryFromAvailableCategoriesOrCreateNewCategory();

        Article newArticle = new Article(title, content, loggedInPerson, chosenCategory);
        articleRepo.save(newArticle);
        log.success("!! New Article '" + newArticle.getTitle() + "'" + " is successfully saved !!");
    }
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
            chosenArticle = chooseAnArticleFromAList("\nChoose one of the following articles to read: ",desiredArticles);
        }
        optionsForReadingAnArticle(chosenArticle, loggedInPerson);
    }
    public void searchArticleByPerson(Person loggedInPerson){
        List<Person> listOfPersonsWithSameName = findPersonByName();    //more than one author can have the same name
        if (listOfPersonsWithSameName.isEmpty()) {
            log.error("!! Desired author NOT found !!");
            return;
        }
        List<Article> desiredArticles = new ArrayList<>();
        for (Person p : listOfPersonsWithSameName) {
            List<Article> allArticlesByAnAuthor = articleRepo.findArticleByPerson(p);
            for (Article article : allArticlesByAnAuthor) {
                desiredArticles.add(article);
            }
        }
        Article chosenArticle = chooseAnArticleFromAList("\nChoose one of the following articles to read: ", desiredArticles);
        optionsForReadingAnArticle(chosenArticle, loggedInPerson);
    }
    private List<Article> findArticleByTitle() {
        log.message("Enter title of the article: ");
        String chosenTitle = ScannerHelper.getStringInput();
        searchWordService.searchWordsInDataBase(chosenTitle);
        return articleRepo.findArticleByTitle(chosenTitle);
    }
    private List<Person> findPersonByName() {
        log.message("Enter author's first name: ");
        String firstName = ScannerHelper.getStringInput();
        searchWordService.searchWordsInDataBase(firstName);

        log.message("Enter author's last name: ");
        String lastName = ScannerHelper.getStringInput();
        searchWordService.searchWordsInDataBase(lastName);

        return personRepo.allAuthorsWithSameName(firstName, lastName);
    }
    private Article chooseAnArticleFromAList(String message, List<Article> articlesList) {
        log.menu(message);
        printOptions(articlesList);
        log.message("Enter article number: ");
        int chosenArticleNum = ScannerHelper.getIntInput(articlesList.size());

        return articlesList.get(chosenArticleNum - 1);
    }
    private void printOptions(List<Article> articleList){
        for (int i = 0; i < articleList.size(); i++) {
            log.menu(i + 1 + ". " + articleList.get(i).getTitle());
        }
    }
    public void readAnArticleOnline(Article chosenArticle) {
        int numOfViews = chosenArticle.getNumOfViews();
        chosenArticle.setNumOfViews(numOfViews + 1);
        articleRepo.save(chosenArticle);
        log.success("!! You can now READ the desired article below !!");
        log.success("------------------------------------------------------------------------------------------");
        log.message("Title: " + chosenArticle.getTitle().toUpperCase() + "\nWritten by: " +
                chosenArticle.getPerson().getFirstName() +
                " " + chosenArticle.getPerson().getLastName() + "\n\n" + chosenArticle.getContent());
        log.success("\n------------------------------------------------------------------------------------------");
    }
    public Article editAnArticleByUser(Person person) {
        List<Article> articlesList  = articleRepo.findArticleByPerson(person);
        Article chosenArticle;
        if (articlesList.size() == 1) {
            chosenArticle = articlesList.get(0);
            System.out.println("You have written ONE article with the following title: \n" + chosenArticle.getTitle());
        } else {
            chosenArticle = chooseAnArticleFromAList("Choose one of the following: ", articlesList);
        }
        return chosenArticle;
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
        log.message("Original content: \n" + chosenArticle.getContent());
        log.message("Enter new content (please write THE-END at the end of your content): ");
        String updatedContent = ScannerHelper.getTextInput();
        System.out.print("Choose 1 to save new content, 2 otherwise \nEnter your choice: ");
        int userChoice = ScannerHelper.getIntInput(2);
        if (userChoice == 1) {
            chosenArticle.setContent(updatedContent);
            articleRepo.save(chosenArticle);
            log.success("!! Article's CONTENT is successfully updated !!");
        } else {
            log.message("!! Article's content is NOT updated !!");
        }
    }
    public void editCategory(Article chosenArticle) {
        List<ArticleCategory> articleCategoryList = categoryRepo.findAll();

        log.message("Current category: " + chosenArticle.getCategory().getName());
        if (articleCategoryList.size() == 1) {
            log.message("!! No other Category available !!");
            log.message("Choose from the following:\n1.Back to Edit Article Menu\n2. Add a new category\nEnter a number: ");
            int userChoice = ScannerHelper.getIntInput(2);
            if (userChoice == 1) {
                log.message("!! Article Category remains the SAME !!");
            } else {
                chosenArticle.setCategory(createNewArticleCategory());
                articleRepo.save(chosenArticle);
                log.success("!! Article's Category has been UPDATED successfully !!");
            }
        } else if (articleCategoryList.size() > 1) {
            ArticleCategory desiredCategory = chooseCategoryFromAvailableCategoriesOrCreateNewCategory();
            chosenArticle.setCategory(desiredCategory);
            articleRepo.save(chosenArticle);
            log.success("!! Article's Category has been UPDATED successfully !!");
        }
    }
    public void updateNameOfAnExistingCategory() {
        List<ArticleCategory> articleCategoryList = categoryRepo.findAll();
        if (articleCategoryList.isEmpty()) {
            System.out.println("!! No category available !!");
            return;
        }
        printAvailableCategories(articleCategoryList);

        System.out.print("Enter category's number: ");
        int categoryChoice = ScannerHelper.getIntInput(articleCategoryList.size());
        ArticleCategory desiredCategory = articleCategoryList.get(categoryChoice - 1);

        categoryRepo.save(desiredCategory);
        log.success("!! Category Name has been UPDATED successfully !!");
    }
    private void printAvailableCategories(List<ArticleCategory> articleCategoryList) {
        log.message("Available categories: ");
        for (int i = 0; i < articleCategoryList.size(); i++) {
            System.out.println(i + 1 + ". " + articleCategoryList.get(i).getName());
        }
    }
    private ArticleCategory chooseCategoryFromAvailableCategoriesOrCreateNewCategory() {
        List<ArticleCategory> articleCategoryList = categoryRepo.findAll();
        printAvailableCategories(articleCategoryList);
        log.menu("\nYou wish to\n1. Choose from the available categories\n2. Add a new category\nEnter a number: ");
        int userChoice = ScannerHelper.getIntInput(2);

        if (userChoice == 1) {
            log.message("Enter category number: ");
            int desiredCategory = ScannerHelper.getIntInput(articleCategoryList.size());
            return articleCategoryList.get(desiredCategory - 1);
        } else {
            return createNewArticleCategory();
        }
    }
    private ArticleCategory createNewArticleCategory() {
        log.message("Enter new category: ");
        String newCategory = ScannerHelper.getStringInput();
        ArticleCategory category = new ArticleCategory(newCategory);
        return categoryRepo.save(category);
    }
    public void deleteAnArticle() {
        List<Article> listOfAllArticlesWithSameName = findArticleByTitle();
        if (listOfAllArticlesWithSameName.isEmpty()) {
            log.error("!! Article NOT found !!");
            return;
        }
        Article chosenArticle;
        if (listOfAllArticlesWithSameName.size() > 1) {
            System.out.println("Desired article is written by following authors: ");
            for (int i = 0; i < listOfAllArticlesWithSameName.size(); i++) {
                Person person = listOfAllArticlesWithSameName.get(i).getPerson();
                String authorsFullName = person.getFirstName() + " " + person.getLastName();
                System.out.println(i + 1 + ". " + authorsFullName);
            }
            System.out.print("Enter number to remove an article written by that author: ");
            int userChoice = ScannerHelper.getIntInput(listOfAllArticlesWithSameName.size());
            chosenArticle = listOfAllArticlesWithSameName.get(userChoice - 1);
        } else {
            chosenArticle = listOfAllArticlesWithSameName.get(0);
        }

        log.success("!! Article '" + chosenArticle.getTitle() + "' is successfully DELETED !!");
        articleRepo.delete(chosenArticle);
    }
    private void optionsForReadingAnArticle(Article article,Person person) {
        boolean isDone = true;
        do {
            log.menu("""
                \nChoose from the following tasks-
                \n1. Back to the SEARCH menu
                2. Read Online
                3. Reserve a Hard-Copy
                4. Order a personal Hard-Copy""");
            System.out.print("\nEnter your choice: ");
            int readingChoice = ScannerHelper.getIntInput(4);

            switch (readingChoice) {
                case 1 -> isDone = false;
                case 2 -> readAnArticleOnline(article);
                case 3 -> reserveHardCopy(article, person);
                case 4 -> orderPersonalHardCopy(article, person);
                default -> System.out.println("!! Invalid Input !!");
            }
        } while (isDone);
    }
    private void reserveHardCopy(Article article, Person person) {
        List<ArticleHardCopy> listOfAvailableHardCopies = hardCopyRepo.findArticleHardCopiesByArticleAndStatus(article, "available");
        List<ArticleHardCopy> listOfReservedHardCopies = hardCopyRepo.findArticleHardCopiesByArticleAndStatus(article, "reserved");

        if (!listOfAvailableHardCopies.isEmpty()) {
            ArticleHardCopy articleHardCopyToBeReserved = listOfAvailableHardCopies.get(0);
            articleHardCopyToBeReserved.setStatus("reserved");
            hardCopyRepo.save(articleHardCopyToBeReserved);
            ArticleBorrowerInfo articleBorrowerInfo = new ArticleBorrowerInfo(articleHardCopyToBeReserved, person);
            borrowerInfoRepo.save(articleBorrowerInfo);
            log.success("\n!! A hard-copy has been RESERVED successfully till the following date " +
                    articleBorrowerInfo.getReturnDate() + " !!");

        } else if (!listOfReservedHardCopies.isEmpty() && listOfReservedHardCopies.size() == MAX_NUM_HARD_COPIES_PER_ARTICLE) {
            ArticleReservationQueue articleReservationQueue = new ArticleReservationQueue(article, person);
            queueRepo.save(articleReservationQueue);
            log.message("""
                    \nNOTE-
                    No hard-copy is available right now!
                    You've been added in a queue &
                    a RESERVATION will be made as soon
                    as any hard-copy is available.""");
        } else {
            ArticleHardCopy articleHardCopy = new ArticleHardCopy(article);
            articleHardCopy.setStatus("reserved");
            hardCopyRepo.save(articleHardCopy);
            article.setAvailableAsHardCopy("yes");
            articleRepo.save(article);
            ArticleBorrowerInfo articleBorrowerInfo = new ArticleBorrowerInfo(articleHardCopy, person);
            borrowerInfoRepo.save(articleBorrowerInfo);
            log.success("\n!! A hard-copy has been RESERVED successfully till the following date " +
                    articleBorrowerInfo.getReturnDate() + " !!");
        }
    }
    public void showHardCopiesReservedByAPerson(Person person) {
        List<ArticleBorrowerInfo> listOfHardCopiesReservedByAUser = borrowerInfoRepo.findArticleBorrowerInfoByPerson(person);

        if (listOfHardCopiesReservedByAUser.isEmpty()) {
            log.error("!! No hard-copies reserved !!");
            log.message("Recommended: To RESERVE a hard-copy, ENTER 6\n");
            return;
        }

        log.message("You have RESERVED the following hard copies: ");
        for (ArticleBorrowerInfo ab : listOfHardCopiesReservedByAUser) {
            System.out.println("Hard Copy ID: " + ab.getArticleHardCopy().getId() + ", Article: " +
                    ab.getArticleHardCopy().getArticle().getTitle());
        }

    }
    public void showArticlesReservedInQueue(Person person) {
        List<ArticleReservationQueue> listOfArticlesReservedByAUser = queueRepo.findArticleReservationQueueByPerson(person);
        if (listOfArticlesReservedByAUser.isEmpty()) {
            log.error("!! No reservations found in queue !!");
            return;
        }
        log.message("RESERVATION(S) in queue: ");
        for (ArticleReservationQueue ar : listOfArticlesReservedByAUser) {
            System.out.println("Reserved On: " + ar.getTimestamp().toLocalDate() + ", Article: " + ar.getArticle().getTitle());
        }
    }
    public void returnReservedArticle(Person person) {
        List<ArticleBorrowerInfo> listOfHardCopiesReservedByAUser = borrowerInfoRepo.findArticleBorrowerInfoByPerson(person);
        if (listOfHardCopiesReservedByAUser.isEmpty()) {
            log.error("!! You haven't reserved any articles yet !!");
            return;
        }

        log.message("You have RESERVED the following hard copies: ");
        for (ArticleBorrowerInfo ab : listOfHardCopiesReservedByAUser) {
            System.out.println("Hard Copy ID: " + ab.getArticleHardCopy().getId() + ", Article: " +
                    ab.getArticleHardCopy().getArticle().getTitle());
        }

        List<Integer> listOfAllHardCopyIds = new ArrayList<>();
        for (ArticleBorrowerInfo ab : listOfHardCopiesReservedByAUser) {
            listOfAllHardCopyIds.add(ab.getArticleHardCopy().getId());
        }
        int desiredHardCopyId;
        for (int attempt = 0; attempt < 3; attempt++) {                     // allows max 3 chances to write correct ID
            System.out.print("\nEnter hard copy id you wish to return: ");
            desiredHardCopyId = ScannerHelper.getIntInput();

            if (listOfAllHardCopyIds.contains(desiredHardCopyId)) {
                Optional<ArticleHardCopy> optionalHardCopy = hardCopyRepo.findArticleHardCopyById(desiredHardCopyId);
                if (optionalHardCopy.isPresent()) {
                    ArticleHardCopy hardCopy = optionalHardCopy.get();
                    Optional<ArticleBorrowerInfo> optionalBorrowerInfo = borrowerInfoRepo.findArticleBorrowerInfoByArticleHardCopy(hardCopy);
                    if (optionalBorrowerInfo.isPresent()) {
                        ArticleBorrowerInfo desiredBorrowerInfo = optionalBorrowerInfo.get();
                        borrowerInfoRepo.delete(desiredBorrowerInfo);
                    }
                    hardCopy.setStatus("available");
                    hardCopyRepo.save(hardCopy);

                    if (thereIsAQueueOnArticle(hardCopy.getArticle())) {        //check if there is a queue on this article
                        assignAvailableHardCopyToFirstPersonInQueue(hardCopy);
                    }
                }
                log.success("\n!! Hard Copy has been RETURNED successfully !!\n");
                break;
            } else {
                log.error("!! Invalid Id !!");
            }
        }
    }
    public void orderPersonalHardCopy(Article article, Person person) {
        ArticleHardCopy personalHardCopy = new ArticleHardCopy(article);
        personalHardCopy = hardCopyRepo.save(personalHardCopy);
        personalHardCopy.setStatus("personal");                     // set status of personal hard-copy => personal
        hardCopyRepo.save(personalHardCopy);

        ArticleBorrowerInfo borrowerInfo = new ArticleBorrowerInfo(personalHardCopy, person);
        borrowerInfo.setReturnDate(null);                           // return-date => null, for personal hard-copy
        borrowerInfoRepo.save(borrowerInfo);
        log.success("\n!! A Personal Hard Copy of the article '" + article.getTitle() + "' has been ORDERED successfully !!");
    }
    private void assignAvailableHardCopyToFirstPersonInQueue(ArticleHardCopy hardCopy) {
        Article desiredArticle = hardCopy.getArticle();
        List<ArticleReservationQueue> listOfAllReservationsOfAnArticle = queueRepo.findArticleReservationQueueByArticleOrderByTimestamp(desiredArticle);

            ArticleReservationQueue firstReservation = listOfAllReservationsOfAnArticle.get(0);
            Person borrower = firstReservation.getPerson();
            ArticleBorrowerInfo borrowerInfo = new ArticleBorrowerInfo(hardCopy, borrower);
            borrowerInfoRepo.save(borrowerInfo);            // first reservation from queue gets hard-copy, info is saved in borrower-table
            hardCopy.setStatus("reserved");
            hardCopyRepo.save(hardCopy);
            queueRepo.delete(firstReservation);            // delete first reservation from queue
    }
    private boolean thereIsAQueueOnArticle(Article article) {
        List<ArticleReservationQueue> listOfAllArticlesInQueue = queueRepo.findAll();
        for (ArticleReservationQueue ar : listOfAllArticlesInQueue) {
            if (ar.getArticle().getId() == article.getId()) {
                return true;
            }
        }
        return false;
    }
    public void reviewArticle() {
        List<Article> listOfArticlesToBeReviewed = articleRepo.findAllByStatusIs("review");
        int counter = 0;

        System.out.println("The following articles are waiting to be reviewed: ");
        for (int i = 0; i < listOfArticlesToBeReviewed.size(); i++) {
            System.out.println(i+1 + ". " + listOfArticlesToBeReviewed.get(i).getTitle());
        }

        while (listOfArticlesToBeReviewed.size() >= counter) {
            System.out.print("\nENTER " + (listOfArticlesToBeReviewed.size() + 1) + ", to EXIT\nor\nEnter article number you want to review: ");
            int adminChoice = ScannerHelper.getIntInput(listOfArticlesToBeReviewed.size() + 1);

            if (adminChoice == (listOfArticlesToBeReviewed.size() + 1)) {
                break;
            }

            Article chosenArticle = listOfArticlesToBeReviewed.get(adminChoice - 1);
            if (chosenArticle.getStatus().equalsIgnoreCase("publish")) {
                System.out.println("Chosen Article has already been PUBLISHED\n!! Please choose another Article !!");
            } else {
                System.out.println("---------------------------------------------------------------------------" + "\nTitle: " +
                        chosenArticle.getTitle().toUpperCase() + "\n" + "Written By: " +
                        chosenArticle.getPerson().getFirstName() + " " + chosenArticle.getPerson().getLastName() + "\n\n" +
                        chosenArticle.getContent() + "\n---------------------------------------------------------------------------");
                int numOfViews = chosenArticle.getNumOfViews();
                chosenArticle.setNumOfViews(numOfViews + 1);
                System.out.print("Do you want to publish the article? (y/n): ");
                String userChoice = ScannerHelper.getStringInput();

                if (userChoice.equalsIgnoreCase("y")) {
                    chosenArticle.setStatus("publish");
                    log.success("!! Article '" + chosenArticle.getTitle() + "' has been PUBLISHED successfully !!");
                    counter++;
                } else if (userChoice.equalsIgnoreCase("n")) {
                    chosenArticle.setStatus("review");
                    log.message("!! Article '" + chosenArticle.getTitle() + "' still needs to be REVIEWED !!");
                } else {
                    log.error("!! Invalid Input !!");
                }
                articleRepo.save(chosenArticle);
            }
        }
    }
}
