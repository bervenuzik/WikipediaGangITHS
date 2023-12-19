package com.example.wikipediagang.Model;

import com.example.wikipediagang.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.example.wikipediagang.model.Article;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    List<Comment> findByPerson(Person person);
    List<Comment> findByArticle(Article article);

}
