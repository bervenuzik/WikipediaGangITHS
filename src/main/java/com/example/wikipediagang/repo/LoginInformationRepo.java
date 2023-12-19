package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.model.LoginInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginInformationRepo  extends JpaRepository<LoginInformation, Integer> {
    Optional<Person> findLoginInformationByUserNameAndPassword(String userName , String password);

}
