package com.example.wikipediagang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository < Person ,Integer > {

    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String secondName);
    Person findByEmail(String email);
    List<Person> findByType(UserType type);
    @Query(
        value = "SELECT * from person where type_id =: type",
        nativeQuery = true
    )
    List<Person> findByType(int type);

    @Query(nativeQuery = true,
            value = "select p.* from Person p inner join Article a on p.id = a.author_id where p.firstname=:firstName and p.lastname=:lastName")
    List<Person> allAuthorsWithName(String firstName, String lastName);

}
