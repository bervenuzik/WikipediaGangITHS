package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCategoryRepo extends JpaRepository<ArticleCategory, Integer> {
    List<ArticleCategory> findAll();
}
