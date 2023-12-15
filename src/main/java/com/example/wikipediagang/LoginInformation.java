package com.example.wikipediagang;

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
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public Person getPerson() {
    return person;
    }

    public void setPerson(Person person) {
    this.person = person;
    }

    @Override
    public String toString() {
        return "user: " + Person.getName()+" \n userName:" + userName + '\n' +
                "password:" + password ;
    }

}
