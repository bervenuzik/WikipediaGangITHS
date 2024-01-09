package com.example.wikipediagang.service;

import com.example.wikipediagang.ScannerHelper;
import com.example.wikipediagang.model.*;
import com.example.wikipediagang.repo.*;
import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.Date;
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
    @Autowired
    ArticleBorrowerInfoRepo articleBorrowerInfoRepo;
    @Autowired
    ErrorLogService errorLogService;

    MessageHandlerService log = new MessageHandlerService();
    Scanner input = new Scanner(System.in);


    public Optional<Person> login() {

        String login;
        String password;
        boolean tryAgain;
        List<LoginInformation> loginInfo;
        Console console = System.console();


        while (true) {

            log.message("\nWrite in your login :");
            login = input.nextLine().trim();
            input.nextLine();
            log.message("Write in your password:");
            if(console == null) {
                password = input.nextLine().trim();
                input.nextLine();
            }else {
                password = String.valueOf(console.readPassword());
            }

            loginInfo = loginRepo.findByUserNameAndPassword(login, password);

            if (loginInfo.size() == 1) {
                Optional<Person> user = personRepo.findByLoginInfo(loginInfo.get(0));
                if (user.isPresent()) {
                    String defaultUserEmail = "default@mail.com";
                    if(user.get().getEmail().equals(defaultUserEmail)){
                        log.error("You try loggin as default user. It is not allowed");
                        if (!log.tryAgain()) return Optional.empty();
                        continue;
                    }
                    log.success("Login is success ");
                    log.success("Welcome, " + user.get().getFirstName() + " " + user.get().getLastName());
                    System.out.flush();
                    return user;
                } else {
                    log.error("Login is failed ");
                    log.error("Wrong login or password ");
                    if (!log.tryAgain()) return Optional.empty();
                }
            }else {
                log.error("There is no such user. Control login and password");
                if(!log.tryAgain()) return Optional.empty();
                System.out.flush();
            }
        }
    }



    public Optional<Person> createUser(Optional<Person> user) {
        String login;
        String password;
        String email;
        String firstName;
        String lastName;
        Optional<UserType> type;
        Person newUser;
        LoginInformation loginInfo;


        //System.out.println(user.get().getType().getName());
        if (user.isPresent() && user.get().getType().getName().equals("Admin")) {
            firstName = inputNewFirstName();
            if( firstName.isEmpty()) return  Optional.empty();

            lastName = inputNewLastName();
            if( lastName.isEmpty()) return  Optional.empty();

            email = inputNewEmail();
            if( email.isEmpty()) return  Optional.empty();

            type =  inputNewUserType();
            if(type.isEmpty()) return  Optional.empty();

            login = inputNewLogin();
            if(login.isEmpty()) return Optional.empty();

            password = inputNewPassword();
            if (password.isEmpty())return  Optional.empty();

            loginInfo = new LoginInformation(login, password);
            loginInfo = loginRepo.save(loginInfo);
            newUser = new Person(firstName, lastName, email, type.get(), loginInfo);
            System.out.println(newUser);
            newUser.setLoginInfo(user.get(), loginInfo);
            personRepo.save(newUser);
            return Optional.of(newUser);
        } else {
            if(user.isPresent()) {
                log.error("You have no enough rights for this action");
                ErrorLogService.saveErroLog("No right",user.get());
            } else{
                log.error("You have to login to make actions");
            }
        }
        return Optional.empty();
    }


    public Optional<Person> deleteUser(Optional<Person> currentUser){
        List<Comment> comments;
        List<ErrorLog> errorLogs;
        List<Article> articles;
        List<ArticleBorrowerInfo> borrowedCopies;
        Optional<Person> defaultUser = Optional.empty();
        Optional<Person> userToDelete = Optional.empty();
        String defaultPersonEmail = "default@mail.com";
        List<Person> admins;
        defaultUser = personRepo.findByEmail(defaultPersonEmail);
        if (currentUser.isEmpty()){
            log.error("You have to login to make actions");
            return Optional.empty();
        }

        if(defaultUser.isEmpty()){
            log.error("Opssss, we have a little problem with servern. Try later");
            ErrorLogService.saveErroLog("Defaultuser not exist",currentUser.get());

            return Optional.empty();
        }



        while (true) {
            String email = inputEmail("Write in user's email , that you want to delete");
            if (email.isEmpty()) return Optional.empty();
            if(email.equals(defaultUser.get().getEmail())){
                log.error("You can't delete this user, try again");

                ErrorLogService.saveErroLog("Try to delete defaultuser",currentUser.get());
                continue;
            }
            userToDelete = personRepo.findByEmail(email);


            if (userToDelete.isEmpty()) {
                log.error("There is no user with such email");
                if (!log.tryAgain()) return Optional.empty();
                continue;
            }

            borrowedCopies = articleBorrowerInfoRepo.findByPerson(userToDelete.get());

            if(!borrowedCopies.isEmpty()){
                log.error("You can't delete this user. He have to return a hard copy(s)");
                for (ArticleBorrowerInfo copyInfo: borrowedCopies) {
                    log.warning(copyInfo.toString());
                }
                if (!log.tryAgain()) return Optional.empty();
                continue;
            }

            if(userToDelete.get().getType().getName().equals("admin")) {
                if (userToDelete.get().getEmail().equals(currentUser.get().getEmail())) {
                    log.error("You can't delete yourself");

                    ErrorLogService.saveErroLog("Admin is trying to delete itself",currentUser.get());
                    continue;
                }
                admins = personRepo.findByType(currentUser.get().getType());
                if(admins.size() == 1){
                    log.error("You can't delete the last admin");
                    ErrorLogService.saveErroLog("Admin is trying to delete last admin",currentUser.get());
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
        while (true) {
            log.question("Write in first name");
            firstName = input.nextLine().trim();
            input.nextLine();
            if (Person.firstNameValidator(firstName)) {
                return firstName;
            }
            log.error("Wrong format of first name");

            if (!log.tryAgain()) {
                return "";
            }
        }
    }


    private String inputNewLastName(){
        String lastName;
        while (true) {
            log.question("Write in last name");
            lastName = input.nextLine().trim();
            input.nextLine();
            if (Person.lastNameValidator(lastName)) return lastName;
            log.error("Wrong format of last name");

            if (!log.tryAgain()) {
                return "";
            }
        }
    }

    public String inputNewEmail(){
        String email;
        Optional<Person> isInUse;
        while (true) {
            log.question("Write in email");
            email = input.nextLine().trim();
            input.nextLine();
            if (Person.emailValidator(email)) {
                isInUse = personRepo.findByEmail(email);
                if(isInUse.isPresent()){
                    log.error("Such email is already in use");
                    if(log.tryAgain()) continue;
                    return "";
                }
                return email;
            }
            if (!log.tryAgain()) {
                return "";
            }
        }
    }

    private String inputEmail(String message){
        String email;
        while (true) {
            if(!message.isEmpty())log.question(message);

            email = input.nextLine().trim();
            input.nextLine();
            if (Person.emailValidator(email)) {
                return email;
            }
            log.error("Wrong format of email");
            if (!log.tryAgain()) {
                return "";
            }
        }
    }






    private  Optional<UserType> inputNewUserType(){
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
            input.nextLine();
            typeSearch = userTypeRepo.findByName(choise);
            if (typeSearch.isPresent()) {
                return typeSearch;
            }else{
                log.error("Wrong input , choose from list");
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
            input.nextLine();
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
        String username = person.getLoginInfo().getUserName();
        Optional<LoginInformation> opLoginInfo = loginRepo.findByUserName(username);
        if (opLoginInfo.isPresent()) {
            LoginInformation loginInfo = opLoginInfo.get();
            loginInfo.setPassword(newPassword);
            loginRepo.save(loginInfo);
            log.success("!! Password has been CHANGED successfully !!");
        }
//        person.getLoginInfo().setPassword(newPassword);
//        personRepo.save(person);
    }

    private void changeFirstName(Person person){
        String newFirstName = inputNewFirstName();
        if(!newFirstName.isEmpty() && Person.firstNameValidator(newFirstName)) {
            person.setFirstName(newFirstName);
            personRepo.save(person);
            log.success("First name is changed");
        }else {
            log.error("Changing is unsuccessful");
        }
    }

    private void changeLastName(Person person){
        String newLastName = inputNewLastName();
        if(!newLastName.isEmpty() && Person.lastNameValidator(newLastName)) {
            person.setLastName(newLastName);
            personRepo.save(person);
            log.success("Last name is changed");
        }else {
            log.error("Changing is unsuccessful");
        }
    }

    public void changeEmail(Person person){
        String newEmail = inputNewEmail();
        if(!newEmail.isEmpty() && Person.emailValidator(newEmail)){
        person.setEmail(newEmail);
        personRepo.save(person);
        log.success("Email is changed");
        }else {
            log.error("Changing is unsuccessful");
        }
    }

    private void changeUserType(Person person){
        Optional<UserType> newUserType = inputNewUserType();
        if(newUserType.isPresent()) {
            person.setType(newUserType.get());
            personRepo.save(person);
        }else {
            log.error("Changing is unsuccessful");
        }
    }


    private Optional<Person> findUser() {
        log.message("Input info to find user: ");
        System.out.println("1. Find by user firstname");
        System.out.println("2. Find by user Type");
        System.out.println("3. Find by user Email");
        System.out.println("4. Find by lastname");
        boolean flag = false;
        while (!flag) {
            System.out.println("input your choice");
            int choice = ScannerHelper.getIntInput();
            if (choice == 1) {
                System.out.println("input firstname");
                String firstname = ScannerHelper.getStringInput();
                List<Person> persons = personRepo.findByFirstName(firstname);
                Optional<Person> person = transferListToOptional(persons);
                return  person;
            }
            if (choice == 2) {
                System.out.println("input Type");
                int id = ScannerHelper.getIntInput();
                List<Person> persons = personRepo.findByType(id);
                Optional<Person> person = transferListToOptional(persons);
                return person;
            }
            if (choice == 3) {
                System.out.println("input email");
                String email = ScannerHelper.getStringInput();
                Optional<Person> persons = personRepo.findByEmail(email);
                if (persons.isPresent()) {
                    return persons;
                } else {
                    log.error("User is not exist");
                    return null;
                }
            }
            if (choice == 4) {
                System.out.println("input lastname");
                String lastname = ScannerHelper.getStringInput();
                List<Person> persons = personRepo.findByLastName(lastname);
                Optional<Person> person = transferListToOptional(persons);
                return person;
            } else {
                log.error("Wrong input, try again");
            }
        }
        return null;
    }

        public void editUser(Optional<Person> person){
        Optional<Person> findUser = findUser();
            try {
                if (findUser.isPresent()){
                    Person userToChange = findUser.get();
                    boolean flag = false;
                    while (!flag) {
                        log.menu("Which part do you want to update: ");
                        log.menu("1.Email");
                        log.menu("2.Firstname");
                        log.menu("3.Lastname");
                        log.menu("4.Privilege");
                        log.menu("5.Quit");
                        log.menu("input ny choice: ");
                        int choice = ScannerHelper.getIntInput(5);
                        switch (choice){
                            case 1 ->changeEmail(userToChange);
                            case 2 ->changeFirstName(userToChange);
                            case 3 ->changeLastName(userToChange);
                            case 4 ->changeUserType(userToChange);
                            case 5 ->flag = true;
                            default -> log.error("Wrong input, try again");
                        }
                    }
                }
            } catch (Exception e) {
                log.error("User not exist");
                ErrorLogService.saveErroLog("User not exist",person.get());
            }
        }


    public Optional<Person> transferListToOptional(List<Person> person){
        int index = 0;
        if (!person.isEmpty()) {
            for(Person p : person){
                System.out.println("option "+index+ "."+p);
                index++;
            }
            System.out.println("Choose who to update");
            int input = ScannerHelper.getIntInput(person.size()-1);
            Optional<Person> optionalPerson = Optional.of(person.get(input));
            return optionalPerson;
     }else{
            return null;
        }
    }
            public  List<Person> getAllUsers(){
        return personRepo.findAll();
    }

}

