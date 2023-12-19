package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.SearchWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SearchWordsRepo extends JpaRepository {
    List<SearchWord> findByText(String text);

}
