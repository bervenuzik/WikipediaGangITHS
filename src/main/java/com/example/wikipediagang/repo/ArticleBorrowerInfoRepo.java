package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.Article;
import com.example.wikipediagang.model.ArticleBorrowerInfo;
import com.example.wikipediagang.model.ArticleHardCopy;
import com.example.wikipediagang.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleBorrowerInfoRepo extends JpaRepository<ArticleBorrowerInfo, Integer> {

    List<ArticleBorrowerInfo> findArticleBorrowerInfoByPerson(Person person);
    Optional<ArticleBorrowerInfo> findArticleBorrowerInfoByArticleHardCopy(ArticleHardCopy articleHardCopy);
    @Query(nativeQuery = true,
            value = "select count(*) from article_borrower_info ab where ab.article_hard_copy_id=:hardCopyId")
    int numberOfTimesHardCopyIsBorrowed(Integer hardCopyId);
}
