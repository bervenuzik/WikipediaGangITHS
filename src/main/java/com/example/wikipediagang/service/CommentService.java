package com.example.wikipediagang.service;

import com.example.wikipediagang.ScannerHelper;
import com.example.wikipediagang.model.Article;
import com.example.wikipediagang.model.Comment;
import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private MessageHandlerService log;

    public void writeCommentOnAnArticle(Article article, Person loggedInPerson) {
        List<Comment> allCommentsOnArticle = commentRepo.findCommentsByArticle(article);
        if (!allCommentsOnArticle.isEmpty()) {
            System.out.println("Available comments: " + allCommentsOnArticle);
        }
        System.out.print("Enter your comment/view (write THE-END after last line): ");
        String userText = ScannerHelper.getTextInput();
        if (!userText.isEmpty()) {
            Comment userComment = new Comment(userText, loggedInPerson, article);
            commentRepo.save(userComment);
        } else {
            log.error("Sorry! Empty text can NOT be saved as a comment");
        }
    }
}
