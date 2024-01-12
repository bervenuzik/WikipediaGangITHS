package com.example.wikipediagang.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    public LoginInformation() {
    }

    public LoginInformation(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {

        if(passwordValidator(password)){
            this.password = password;
        }
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
            System.out.println("password can't be empty");
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
        return userName  +"  "+ password;
    }

}





