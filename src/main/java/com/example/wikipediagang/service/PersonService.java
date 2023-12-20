package com.example.wikipediagang.service;

import com.example.wikipediagang.model.LoginInformation;
import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.repo.PersonRepository;
import com.example.wikipediagang.model.UserType;
import com.example.wikipediagang.repo.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.wikipediagang.repo.LoginInformationRepo;
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
    MessageHandlerService log = new MessageHandlerService();
    Scanner input = new Scanner(System.in);


    public void loginOut(Optional<Person> currentUser){
        currentUser = Optional.empty();
    }
    public Optional<Person> login() {

        String login;
        String password;
        boolean tryAgain;
        List<LoginInformation> loginInfo;

        while (true) {
            System.out.println("Write in your login :");
            login = input.nextLine().trim();
            System.out.println("Write in your password:");
            password = input.nextLine().trim();
            loginInfo = loginRepo.findByUserNameAndPassword(login, password);


            if (loginInfo.size() == 1) {
                Optional<Person> user = personRepo.findByLoginInfo(loginInfo.get(0));
                if (user.isPresent()) {
                    log.success("Login is success ");
                    log.success("Welcome, " + user.get().getFirstName() + " " + user.get().getLastName());
                    return user;
                } else {
                    log.error("Login is failed ");
                    log.error("Wrong login or password ");
                    tryAgain = log.tryAgain();
                    if (!tryAgain) return Optional.empty();
                }
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
            firstName = getNewFirstName();
            if( firstName.isEmpty()) return  Optional.empty();

            lastName = getNewLastName();
            if( lastName.isEmpty()) return  Optional.empty();

            email = getNewEmail();
            if( email.isEmpty()) return  Optional.empty();

            type =  getNewUserType();
            if(type.isEmpty()) return  Optional.empty();

            login = getNewLogin();
            if(login.isEmpty()) return Optional.empty();

            password = getNewPassword();
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


    private String getNewFirstName() {
        String firstName;
        boolean tryAgain;
        while (true) {
            log.question("Write in first name for new user");
            firstName = input.nextLine().trim();
            if (Person.firstNameValidator(firstName)) {
                return firstName;
            }
            log.error("Wrong format of first name");
            tryAgain = log.tryAgain();
            if (!tryAgain) {
                return "";
            }
        }
    }


    private String getNewLastName(){
        String lastName;
        boolean tryAgain;
        while (true) {
            log.question("Write in last name for new user");
            lastName = input.nextLine().trim();
            if (Person.lastNameValidator(lastName)) return lastName;
            log.error("Wrong format of last name");
            tryAgain = log.tryAgain();
            if (!tryAgain) {
                return "";
            }
        }
    }

    private String getNewEmail(){
        String email;
        boolean tryAgain;
        while (true) {
            log.question("Write in email for new user");
            email = input.nextLine().trim();
            if (Person.emailValidator(email)) return email;
            log.error("Wrong format of email");
            tryAgain = log.tryAgain();
            if (!tryAgain) {
                return "";
            }
        }
    }


    private  Optional<UserType> getNewUserType (){
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

    private  String getNewLogin (){
        String login;
        boolean tryAgain;
        while (true) {
            log.message("Write in a login");
            login = input.nextLine().trim();
            if (LoginInformation.userNameValidator(login)) return login;
            log.error("Wrong format of login");
            tryAgain = log.tryAgain();
            if (!tryAgain) {
                return "";
            }
        }
    }

    private String getNewPassword (){
        String password;
        boolean tryAgain;
        while (true) {
            log.message("Write in a password");
            password = input.nextLine().trim();
            if (LoginInformation.passwordValidator(password)) return password;
            log.error("Wrong format of password");
            tryAgain = log.tryAgain();
            if (!tryAgain) {
                return "";
            }
        }
    }

    public  List<Person> getAllUsers(){
        return personRepo.findAll();
    }

}

