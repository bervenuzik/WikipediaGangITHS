package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.LoginInformation;
import com.example.wikipediagang.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginInformationRepo  extends JpaRepository<LoginInformation, Integer> {

    List<LoginInformation> findByUserNameAndPassword(String userName , String password);
    Optional<Person> findLoginInformationByUserNameAndPassword(String userName , String password);

}
