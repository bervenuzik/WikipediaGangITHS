package com.example.wikipediagang.repo;

import com.example.wikipediagang.Model.ArticleHardCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleHardCopyRepo extends JpaRepository<ArticleHardCopy, Integer> {
}
