package com.example.wikipediagang.repo;

import com.example.wikipediagang.Model.ArticleHardCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleHardCopyRepo extends JpaRepository<ArticleHardCopy, Integer> {
    @Query(nativeQuery = true,
            value = "select count(article_id) from article_hard_copy a group by article_id having article_id=:articleId")
    int findNumberOfHardCopiesByArticleId(Integer articleId);
}
