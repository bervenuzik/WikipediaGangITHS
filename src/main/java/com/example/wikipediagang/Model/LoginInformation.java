package com.example.wikipediagang.model;

import com.example.wikipediagang.Person;
import jakarta.persistence.*;

@Entity
@Table(name = "login_info")
public class LoginInformation {

    // autogener id från databasen.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username" )
     private String userName;
     private String password;
     @OneToOne(cascade= CascadeType.ALL)
     @JoinColumn(name = "person_id")
     private Person person;

   /* @Column(name = "person_id")
    private int personId;*/
    public LoginInformation() {
    }

    public LoginInformation(String userName, String password, Person person) {
        this.userName = userName;
        this.password = password;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userNameValidator(userName)){
            this.userName = userName;}
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        if(passwordValidator(password)){
            this.password = password;
        }
    }

   public Person getPerson() {
    return person;
    }

    public void setPerson(Person person) {
    this.person = person;
    }

    public static boolean userNameValidator(String userName){
        if(userName.isEmpty()){
            System.out.println("username can't be empty.");
            return false;
        }
        return true;
    }

    public static boolean passwordValidator(String password){
        if(password.isEmpty()){
            System.out.println("password can't be enpty");
            return false;
        }
        char tempChar;
        boolean upperCaseFlag = false;
        boolean lowererCaseFlag = false;
        boolean numberFlag = false;

        for(int i= 0; i < password.length(); i++){
            tempChar = password.charAt(i);
            if(Character.isDigit(tempChar)){
                numberFlag = true;
            }
            if(Character.isUpperCase(tempChar)){
                upperCaseFlag = true;
            }
            if(Character.isLowerCase(tempChar)){
                lowererCaseFlag = true;
            }
            if(numberFlag && upperCaseFlag && lowererCaseFlag){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "user: " + person.getFirstName()+ " "+ person.getLastName() +" \n userName:" + userName + '\n' +
                "password:" + password ;
    }

}

