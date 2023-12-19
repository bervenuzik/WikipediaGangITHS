package com.example.wikipediagang.Model;

import com.example.wikipediagang.Model.LoginInformation;
import com.example.wikipediagang.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface LoginInformationRepo  extends JpaRepository<LoginInformation, Integer> {


    List<LoginInformation> findByUserNameAndPassword(String userName , String password);

}
