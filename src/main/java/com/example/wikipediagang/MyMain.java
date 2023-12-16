package com.example.wikipediagang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Component
public class MyMain implements CommandLineRunner {
    @Autowired
    PersonRepository personRepo;
    @Autowired
    UserTypeRepository userTypeRepo;

    @Override
    public void run(String... args) throws Exception {

        Optional<UserType> type = userTypeRepo.findById(2);
        if(type.isPresent()) {
            List<Person> persons = personRepo.findByType(type.get());
            System.out.println(persons);
        }else {
            System.out.println("not found");
        }

    }
}
