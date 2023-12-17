package com.example.wikipediagang.Model;

import com.example.wikipediagang.Model.LoginInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginInformationRepo  extends JpaRepository<LoginInformation, Integer> {


}
