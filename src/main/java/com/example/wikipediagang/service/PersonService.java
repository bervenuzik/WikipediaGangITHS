package com.example.wikipediagang.service;

import com.example.wikipediagang.ScannerHelper;
import com.example.wikipediagang.model.*;
import com.example.wikipediagang.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class PersonService {
    @Autowired
    PersonRepository personRepo;
    @Autowired
    UserTypeRepository userTypeRepo;
    @Autowired
    LoginInformationRepo loginRepo;
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    ErrorLogRepo errorLogRepo;
    @Autowired
    ArticleRepo articleRepo;

    MessageHandlerService log = new MessageHandlerService();
    Scanner input = new Scanner(System.in);


    public Optional<Person> loginOut(){
        return Optional.empty();
    }
    public Optional<Person> login() {

        String login;
        String password;
        boolean tryAgain;
        List<LoginInformation> loginInfo;

        while (true) {
            log.message("\nWrite in your login :");
            login = input.nextLine().trim();
            log.message("Write in your password:");
            password = input.nextLine().trim();
            System.out.flush();
            loginInfo = loginRepo.findByUserNameAndPassword(login, password);

            if (loginInfo.size() == 1) {
                Optional<Person> user = personRepo.findByLoginInfo(loginInfo.get(0));
                if (user.isPresent()) {
                    log.success("Login is success ");
                    log.success("Welcome, " + user.get().getFirstName() + " " + user.get().getLastName());
                    System.out.flush();
                    return user;
                } else {
                    log.error("Login is failed ");
                    log.error("Wrong login or password ");
                    tryAgain = log.tryAgain();
                    if (!tryAgain) return Optional.empty();
                }
            }else {
                log.error("There is no such user. Control login and password");
                if(!log.tryAgain()) return Optional.empty();
                System.out.flush();
            }
        }
    }

    public Optional<Person> createUser(Optional<Person> admin) {
        String login;
        String password;
        String email;
        String firstName;
        String lastName;
        Optional<UserType> type;
        Optional<Person> newUser;
        LoginInformation loginInfo;
        LoginInformation loginInfoDB;

        //System.out.println(admin.get().getType().getName());
        if (admin.isPresent() && admin.get().getType().getName().equals("admin")) {
            firstName = inputNewFirstName();
            if( firstName.isEmpty()) return  Optional.empty();

            lastName = inputLastName();
            if( lastName.isEmpty()) return  Optional.empty();

            email = inputNewEmail();
            if( email.isEmpty()) return  Optional.empty();

            type =  inputUserType();
            if(type.isEmpty()) return  Optional.empty();

            login = inputNewLogin();
            if(login.isEmpty()) return Optional.empty();

            password = inputNewPassword();
            if (password.isEmpty())return  Optional.empty();
            loginInfo = new LoginInformation(login, password);
            loginInfo = loginRepo.save(loginInfo);
            newUser = Optional.of(new Person(firstName, lastName, email, type.get(), loginInfo));
            newUser.get().setLoginInfo(admin.get(), loginInfo);
            personRepo.save(newUser.get());
            return newUser;
        } else {
            log.error("You have no enough rights for this action");
        }
        return Optional.empty();
    }


    public Optional<Person> deleteUser(Person currentUser){
        List<Comment> comments;
        List<ErrorLog> errorLogs;
        List<Article> articles;
        Optional<Person> defaultUser = Optional.empty();
        Optional<Person> userToDelete = Optional.empty();
        List<Person> admins;
        defaultUser = personRepo.findByEmail("default@mail.com");
        if(defaultUser.isEmpty()){
            log.error("Opssss, we have a little problem with servern. Try later");
            return Optional.empty();
        }
        System.out.println(defaultUser);

        while (true) {
            log.message("Write in user's email , that you want to delete");
            String email = inputNewEmail();
            if (email.isEmpty()) return Optional.empty();
            if(email.equals(defaultUser.get().getEmail())){
                log.error("You can't delete this user, try again");
                continue;
            }
            userToDelete = personRepo.findByEmail(email);

            if (userToDelete.isEmpty()) {
                log.error("There is no user with such email");
                if (!log.tryAgain()) return Optional.empty();
                continue;
            }

            if(userToDelete.get().getType().getName().equals("admin")) {
                if (userToDelete.get().getEmail().equals(currentUser.getEmail())) {
                    log.error("You can't delete yourself");
                    continue;
                }
                admins = personRepo.findByType(currentUser.getType());
                if(admins.size() == 1){
                    log.error("You can't delete the last admin");
                    continue;
                }
            }
            break;
        }

        System.out.println(userToDelete);

            comments = commentRepo.findCommentByPerson(userToDelete.get());
            if(!comments.isEmpty()){
                for (Comment comment: comments) {
                    System.out.println(comment.toString());
                    comment.setPerson(defaultUser.get());
                    commentRepo.save(comment);
                }
            }

            errorLogs = errorLogRepo.findByPerson(userToDelete.get());
            if(!errorLogs.isEmpty()){
                for (ErrorLog logg: errorLogs) {
                    logg.setPerson(defaultUser.get());
                    errorLogRepo.save(logg);
                }
            }

        articles = articleRepo.findArticleByPerson(userToDelete.get());
        if(!articles.isEmpty()){
            for (Article article: articles) {
                article.setPerson(defaultUser.get());
                articleRepo.save(article);
            }
        }

        personRepo.delete(userToDelete.get());

        return userToDelete;
    }


    private String inputNewFirstName() {
        String firstName;
        boolean tryAgain;
        while (true) {
            log.question("Write in first name");
            firstName = input.nextLine().trim();
            if (Person.firstNameValidator(firstName)) {
                return firstName;
            }
            log.error("Wrong format of first name");

            if (!log.tryAgain()) {
                return "";
            }
        }
    }


    private String inputLastName(){
        String lastName;
        boolean tryAgain;
        while (true) {
            log.question("Write in last name");
            lastName = input.nextLine().trim();
            if (Person.lastNameValidator(lastName)) return lastName;
            log.error("Wrong format of last name");

            if (!log.tryAgain()) {
                return "";
            }
        }
    }

    public String inputNewEmail(){
        String email;
        boolean tryAgain;
        Optional<Person> isInUse;
        while (true) {
            log.question("Write in email");
            email = input.nextLine().trim();
            if (Person.emailValidator(email)) {
                isInUse = personRepo.findByEmail(email);
                if(isInUse.isPresent()){
                    log.error("Such email is already in use");
                    if(log.tryAgain()) continue;
                    return "";
                }
                return email;
            }
            log.error("Wrong format of email");
            tryAgain = log.tryAgain();
            if (!tryAgain) {
                return "";
            }
        }
    }


    private  Optional<UserType> inputUserType(){
        String choise;
        Optional<UserType> typeSearch;
        List<UserType> types;
        while (true) {

            log.message("Choose type of user, write in a name");
            types = userTypeRepo.findAll();

            for (int i = 0; i < types.size(); i++) {
                log.message(i+1 + ". "+types.get(i).toString());
            }

            log.message("Write in a name :");
            choise = input.nextLine().trim().toLowerCase();
            typeSearch = userTypeRepo.findByName(choise);
            if (typeSearch.isPresent()) {
                return typeSearch;
            }
            if (!log.tryAgain()) return Optional.empty();
        }
    }

    private  String inputNewLogin(){
        String login;
        boolean tryAgain;
        Optional<LoginInformation> isInUse;
        while (true) {
            log.message("Write in a login");
            login = input.nextLine().trim();
            if (LoginInformation.userNameValidator(login)){
                isInUse = loginRepo.findByUserName(login);
                if(isInUse.isPresent()){
                    log.error("Such login is already in use");
                    if(log.tryAgain()) continue;
                    return "";
                }
                return login;
            }
            log.error("Wrong format of login");
            if (!log.tryAgain()) {
                return "";
            }
        }
    }

    public String inputNewPassword(){
        String password;
        boolean tryAgain;
        while (true) {
            log.message("Write in a password");
            password = input.nextLine().trim();
            if (LoginInformation.passwordValidator(password)) return password;
            log.error("Wrong format of password");
            if (!log.tryAgain()) {
                return "";
            }
        }
    }

    public void changePassword(Person person){
        String newPassword= inputNewPassword();
        person.getLoginInfo().setPassword(newPassword);
        personRepo.save(person);
    }

    private void changeFirstName(Person person){
        String newFirstName = inputNewFirstName();
        person.setFirstName(newFirstName);
        personRepo.save(person);
    }

    private void changeLastName(Person person){
        String newLastName = inputLastName();
        person.setLastName(newLastName);
        personRepo.save(person);
    }

    public void changeEmail(Person person){
        String newEmail = inputNewEmail();
        person.setEmail(newEmail);
        personRepo.save(person);
    }

    private void changeUserType(Person person){
        UserType newUSerType = inputUserType().get();
        person.setType(newUSerType);
        personRepo.save(person);
    }
    public void editUser(){
        log.menu("Input userId which you want to edit: ");
        int id = ScannerHelper.getIntInput();
        Optional<Person> findUser = personRepo.findById(id);
        if(findUser.isPresent()){
            Person userToChange = findUser.get();
            boolean flag = false;
            while (!flag) {
                log.menu("Which part do you want to update: ");
                log.menu("1.Email");
                log.menu("2.Firstname");
                log.menu("3.Lastname");
                log.menu("4.Privilage");
                log.menu("5.Quit");
                log.menu("input ny choice: ");
                int choice = ScannerHelper.getIntInput(5);
                switch (choice){
                    case 0 ->changeEmail(userToChange);
                    case 1 ->changeFirstName(userToChange);
                    case 2 ->changeLastName(userToChange);
                    case 3 ->changeUserType(userToChange);
                    case 4 ->flag = true;
                }
            }
        }
    }

    public  List<Person> getAllUsers(){
        return personRepo.findAll();
    }

}

