package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.model.Article;
import com.example.wikipediagang.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentByPerson(Person person);
    List<Comment> findCommentsByArticle(Article article);

}
