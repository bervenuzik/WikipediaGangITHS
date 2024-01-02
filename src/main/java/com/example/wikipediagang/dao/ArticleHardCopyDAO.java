package com.example.wikipediagang.dao;

import com.example.wikipediagang.model.ArticleHardCopy;

import java.util.List;

public interface ArticleHardCopyDAO {
    List<ArticleHardCopy> findHardCopiesByArticleId(Integer articleId);
}
