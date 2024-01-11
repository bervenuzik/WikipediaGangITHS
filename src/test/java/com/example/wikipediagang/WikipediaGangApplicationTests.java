package com.example.wikipediagang;

import com.example.wikipediagang.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.*;

//write tests for all methods in Person class
@SpringBootTest
class WikipediaGangApplicationTests {


    @Test
    void contextLoads() {
    }
    @Test
    void emailTest(){
         assertTrue("correct email",Person.emailValidator("vlad@gmail.com"));
         assertFalse("no @",Person.emailValidator("vladgmail.com"));
         assertFalse("nothing before @",Person.emailValidator("@gmail.com"));
         assertFalse("no .",Person.emailValidator("vlad@gmailcom"));
         assertFalse("nothing after  .",Person.emailValidator("vlad@gmail."));
         assertFalse("space in email.",Person.emailValidator("vlad@gm ail."));

    }

}
