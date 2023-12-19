package com.example.wikipediagang.repo;

import com.example.wikipediagang.Model.ArticleBorrowerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleBorrowerInfoRepo extends JpaRepository<ArticleBorrowerInfo, Integer> {
    @Query(nativeQuery = true,
    value = "select * from article_borrower_info where article_id=:articleId and borrower_id=:personId")
    List<ArticleBorrowerInfo> findByArticleIdAndPersonId(Integer articleId, Integer personId);
}
