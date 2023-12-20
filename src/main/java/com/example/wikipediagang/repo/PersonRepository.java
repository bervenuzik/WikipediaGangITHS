package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.LoginInformation;
import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository <Person,Integer > {

    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String secondName);
    Person findByEmail(String email);
    List<Person> findByType(UserType type);
    @Query(
        value = "SELECT * from person where type_id =: type",
        nativeQuery = true
    )
    List<Person> findByType(int type);
    Optional<Person> findByLoginInfo(LoginInformation loginInfo);

    @Query(nativeQuery = true,
            value = "select distinct p.* from person p inner join article a on p.id = a.author_id where p.firstname=:firstName and p.lastname=:lastName")
    List<Person> allAuthorsWithSameName(String firstName, String lastName);



}
