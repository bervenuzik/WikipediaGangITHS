package com.example.wikipediagang.Model;

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

    public LoginInformation(int id, String userName, String password, Person person) {
        this.id = id;
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

    private boolean userNameValidator(String userName){
        if(userName.isEmpty()){
            System.out.println("username can't be empty.");
            return false;
        }
        return true;
    }

    private boolean passwordValidator(String password){
        if(password.isEmpty()){
            System.out.println("password can't be enpty");
            return false;
        }
        // check here that the password is sequere enough, exempelvis att den ska vara av en viss längd och att den ska innhålle minst 1 siffra och ett special tecken.
        return true;
    }

    @Override
    public String toString() {
        return "user: " + person.getFirstName()+ " "+ person.getLastName() +" \n userName:" + userName + '\n' +
                "password:" + password ;
    }

}
