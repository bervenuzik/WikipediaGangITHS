package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.ArticleBorrowerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleBorrowerInfoRepo extends JpaRepository<ArticleBorrowerInfo, Integer> {
}
