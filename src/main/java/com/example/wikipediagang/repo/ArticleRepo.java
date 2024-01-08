package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Integer> {

    List<Article> findArticleByTitle(String title);
    List<Article> findArticleByTitleAndStatus(String title, String status);
    List<Article> findArticleByPerson(Person author);
    List<Article> findArticlesByPersonAndStatus(Person author, String status);
    List<Article> findAllByStatusIs(String status);

}
