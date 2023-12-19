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



    public Optional<Person>login(){
        String login;
        String password;
        boolean tryAgain;
        List<LoginInformation> loginInfo;

        while(true){
            System.out.println("Write in your login :");
            login = input.nextLine().trim();
            System.out.println("Write in your password :");
            password = input.nextLine().trim();
            loginInfo = loginRepo.findByUserNameAndPassword(login,password);
            if (loginInfo.size() == 1){
                log.success("Login is success ");
                log.success("Welcome, " + loginInfo.get(0).getPerson().getFirstName() + " "+ loginInfo.get(0).getPerson().getLastName());
                return Optional.of(loginInfo.get(0).getPerson());
            }else {
                log.error("Login is failed ");
                log.error("Wrong login or password ");
                tryAgain = log.tryAgain();
                if(!tryAgain)return Optional.empty();
            }
        }
    }

    private Optional<Person> createUser(Person person) {
        //TODO divide into small parts , create own metods for every part for easier reusing
        String login;
        String password;
        String email;
        String firstName;
        String lastName;
        String choise;
        UserType type;
        Optional<Person> newUser;
        LoginInformation loginInfo;
        boolean tryAgain;
        if (person.getType().getName().equals("Admin")) {
            while (true) {
                log.question("Write in first name for new user");
                firstName = input.nextLine().trim();
                if (Person.firstNameValidator(firstName)) break;
                log.error("Wrong format of first name");
                tryAgain = log.tryAgain();
                if(!tryAgain){
                    return Optional.empty();
                }
            }

            while (true) {
                log.question("Write in last name for new user");
                lastName = input.nextLine().trim();
                if (Person.lastNameValidator(firstName)) break;
                log.error("Wrong format of last name");
                tryAgain = log.tryAgain();
                if(!tryAgain){
                    return Optional.empty();
                }
            }

            while (true) {
                log.question("Write in email for new user");
                email = input.nextLine().trim();
                if (Person.emailValidator(email)) break;
                log.error("Wrong format of email");
                tryAgain = log.tryAgain();
                if(!tryAgain){
                    return Optional.empty();
                }
            }


            while (true) {

                log.message("Choose type of user, write in a name");
                System.out.println((userTypeRepo.findAll()));
                log.message("Write in a name :");
                choise = input.nextLine().trim().toLowerCase();
                Optional<UserType> typeSearch = userTypeRepo.findByName(choise);
                if(typeSearch.isPresent()){
                    type = typeSearch.get();
                    break;
                }
                if(!log.tryAgain()) return Optional.empty();
            }

            while (true) {

                log.message("Write in a login");
                login = input.nextLine().trim();
                if (LoginInformation.userNameValidator(login)) break;
                log.error("Wrong format of login");
                tryAgain = log.tryAgain();
                if(!tryAgain){
                    return Optional.empty();
                }
            }

            while (true) {

                log.message("Write in a password");
                password = input.nextLine().trim();
                if (LoginInformation.passwordValidator(password)) break;
                log.error("Wrong format of login");
                tryAgain = log.tryAgain();
                if(!tryAgain){
                    return Optional.empty();
                }
            }

            newUser = Optional.of(new Person(firstName,lastName,email,type));
            loginInfo = new LoginInformation(login , password , newUser.get());
            newUser.get().setLoginInfo(person , loginInfo);
            personRepo.save(newUser.get());
            return newUser;
        } else {
            log.error("You have no enough rights for this action");
        }
        return Optional.empty();
    }




}
