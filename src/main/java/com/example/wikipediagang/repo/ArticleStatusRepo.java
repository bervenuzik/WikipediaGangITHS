package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleStatusRepo extends JpaRepository<ArticleStatus, Integer> {
}
