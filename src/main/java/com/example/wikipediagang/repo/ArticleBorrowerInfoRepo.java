package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.ArticleBorrowerInfo;
import com.example.wikipediagang.model.ArticleHardCopy;
import com.example.wikipediagang.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleBorrowerInfoRepo extends JpaRepository<ArticleBorrowerInfo, Integer> {

    List<ArticleBorrowerInfo> findByPerson(Person person);
    List<ArticleBorrowerInfo> findArticleBorrowerInfoByPersonAndActualReturnDate(Person person, LocalDate actualReturnDate);
    Optional<ArticleBorrowerInfo> findArticleBorrowerInfoByArticleHardCopy(ArticleHardCopy articleHardCopy);
    @Query(nativeQuery = true,
            value = "select count(*) from article_borrower_info ab where ab.article_hard_copy_id=:hardCopyId")
    int numberOfTimesHardCopyIsBorrowed(Integer hardCopyId);
    @Query(nativeQuery = true,
            value = "select * from article_borrower_info ab where ab.borrower_id=:personId and ab.expected_return_date is null")
    List<ArticleBorrowerInfo> findPersonalHardCopiesByPerson(int personId);
}
