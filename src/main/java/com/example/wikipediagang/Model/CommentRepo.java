package com.example.wikipediagang.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    List<Comment> finByPerson(Person person);
    List<Comment> finByArticle(Article article);

}
