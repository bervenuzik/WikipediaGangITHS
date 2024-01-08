package com.example.wikipediagang.dao;

import com.example.wikipediagang.model.ArticleHardCopy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleHardCopyDAOImpl implements ArticleHardCopyDAO {
    private Connection connection;
    private PreparedStatement allHardCopiesOfAnArticlePS;

    /* to avoid using hard-coded values of database's name, username and password, one can use @Value annotation
    directly in the constructor. In this way, it fetches respective values from the properties file and uses them instead.
     */

    public ArticleHardCopyDAOImpl(@Value("${spring.datasource.url}") String databaseUrl,
                                  @Value("${spring.datasource.username}") String databaseUsername,
                                  @Value("${spring.datasource.password}") String databasePassword) {

        try {
            connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
            allHardCopiesOfAnArticlePS = connection.prepareStatement("select * from article_hard_copy a where a.article_id=?");
        } catch (SQLException e) {
            throw new RuntimeException("ArticleHardCopyDAO constructor error: " + e);
        }
    }

    @Override
    public List<ArticleHardCopy> findHardCopiesByArticleId(Integer articleId) {

        List<ArticleHardCopy> articleHardCopies = new ArrayList<>();
        try {
            allHardCopiesOfAnArticlePS.setInt(1,articleId);
            ResultSet rs = allHardCopiesOfAnArticlePS.executeQuery();
            while(rs.next()) {
                ArticleHardCopy articleHardCopy = new ArticleHardCopy();
                articleHardCopy.setStatus(rs.getString("status"));
                articleHardCopy.setId(rs.getInt("id"));
                articleHardCopies.add(articleHardCopy);
            }
            return articleHardCopies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
