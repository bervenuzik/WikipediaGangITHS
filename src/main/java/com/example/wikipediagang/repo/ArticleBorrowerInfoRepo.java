package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.Article;
import com.example.wikipediagang.model.ArticleBorrowerInfo;
import com.example.wikipediagang.model.ArticleHardCopy;
import com.example.wikipediagang.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleBorrowerInfoRepo extends JpaRepository<ArticleBorrowerInfo, Integer> {
    List<ArticleBorrowerInfo> findByArticleHardCopyAndAndPerson(ArticleHardCopy hardCopy, Person person);
    List<ArticleBorrowerInfo> findByPerson(Person person);

    @Query(nativeQuery = true,
            value = "select ab.* from article_borrower_info ab " +
                    "inner join article_hard_copy ah on ah.id = ab.article_hard_copy_id " +
                    "order by ab.return_date")
    List<ArticleBorrowerInfo> sortHardCopiesByReturnDate(Article article);
}
