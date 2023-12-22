package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.ArticleReservationQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleReservationQueueRepo extends JpaRepository<ArticleReservationQueue, Integer> {
}
