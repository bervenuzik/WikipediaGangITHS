package com.example.wikipediagang.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    private String email;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id" , referencedColumnName = "id")
    UserType type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id" , referencedColumnName = "id")
    LoginInformation loginInfo;


    public Person() {
    }

    @Override
    public String toString() {
        return String.format("\n" +
                "id= %-12d" +
                ", firstName= %-20s'" + '\'' +
                ", lastName= %-20s'" +   '\'' +
                ", email= %-50s'" +  '\'' +
                ", type= %-15s"
                 ,id ,  firstName ,lastName,email,type.getName()) ;
    }

    public Person(String firstName, String lastName, String email, UserType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean setFirstName(String firstName) {
        if(firstNameValidator(firstName)) {
            this.firstName = firstName;
            return true;
        }
        return false;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean setLastName(String lastName) {
        if(lastNameValidator(lastName)) {
            this.lastName = firstName;
            return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        if(emailValidator(email)) {
            this.email = email;
            return true;
        }
        return false;
    }

    public int getTypeId() {
        return type.getId();
    }
    public String getTypeName() {
        return type.getName();
    }
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
    public void setLoginInfo(Person person , LoginInformation loginInfo){
        if(person.getType().getName().equals("admin")){
            this.loginInfo = loginInfo;
        }
    }

    public static boolean firstNameValidator(String firstName){

        if (firstName.isEmpty()) {
            System.err.println("First name can't be empty");
            return false;
        };

        String expression = "^[a-zA-ZöÖåÅäÄ ]+";
        if(firstName.matches(expression)){
            return true;
        } else{
            System.err.println("First name can contain only letters");
            return false;
        }

    }

    public static boolean lastNameValidator(String secondName){

        if (secondName.isEmpty()) {
            System.err.println("Last name can't be empty");
            return false;
        };

        String expression =  "^[a-zA-ZöÖåÅäÄ][a-zA-ZöÖåÅäÄ\\-.']*[a-zA-ZöÖåÅäÄ]$";
        if(secondName.matches(expression)){
            return true;
        } else{
            System.err.println("Last name can't contain numbers");
            return false;
        }

    }

    public static boolean emailValidator(String email){

        if (email.isEmpty()) {
            System.err.println("Email can't be empty");
            return false;
        };

        String expression =  "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if(email.matches(expression)){
            return true;
        } else{
            System.err.println("Wrong format of email");
            return false;
        }



    }
}
