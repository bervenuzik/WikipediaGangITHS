package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.Article;
import com.example.wikipediagang.model.ArticleReservationQueue;
import com.example.wikipediagang.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleReservationQueueRepo extends JpaRepository<ArticleReservationQueue, Integer> {

    List<ArticleReservationQueue> findArticleReservationQueueByArticleOrderByTimestamp(Article article);
    List<ArticleReservationQueue> findArticleReservationQueueByPerson(Person person);

}
