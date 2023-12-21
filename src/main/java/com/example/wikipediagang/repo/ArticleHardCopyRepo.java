package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.Article;
import com.example.wikipediagang.model.ArticleHardCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleHardCopyRepo extends JpaRepository<ArticleHardCopy, Integer> {
    @Query(nativeQuery = true,
            value = "select count(*) from article_hard_copy a where a.article_id=:articleId")
    int findNumberOfHardCopiesByArticleId(Integer articleId);

    List<ArticleHardCopy> findNumOfHardCopiesByArticleAndStatus(Article article, String status);

}
