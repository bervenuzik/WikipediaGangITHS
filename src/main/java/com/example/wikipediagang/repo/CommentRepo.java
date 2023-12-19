package com.example.wikipediagang.repo;

import com.example.wikipediagang.Model.Person;
import com.example.wikipediagang.Model.Article;
import com.example.wikipediagang.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentByPerson(Person person);
    List<Comment> findCommentsByArticle(Article article);

}
