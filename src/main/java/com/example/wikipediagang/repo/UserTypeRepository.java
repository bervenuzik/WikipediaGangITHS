package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserTypeRepository  extends JpaRepository<UserType, Integer > {

UserType findByName(String name);

}
