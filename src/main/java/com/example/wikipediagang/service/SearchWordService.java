package com.example.wikipediagang.service;

import com.example.wikipediagang.model.SearchWord;
import com.example.wikipediagang.repo.SearchWordsRepo;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class SearchWordService {

    @Autowired
    SearchWordsRepo searchWordsRepo;


    public void searchWordsInDataBase(String input) {
        String[] stringAsArray = input.split(" ");
        for (int i = 0; i < stringAsArray.length; i++) {
            if (!checkIfWordExists(stringAsArray[i])) {
                addSearchWord(stringAsArray[i]);
            }
            else {
                uppdateCount(stringAsArray[i]);
            }
        }
    }
    private boolean checkIfWordExists(String word){
        SearchWord searchWord = searchWordsRepo.findByText(word);
        if(searchWord == null){
            return false;
        } else{
        return true;
        }
    }


    private void addSearchWord(String word){
            SearchWord searchWord = new SearchWord(word, 1);
            searchWordsRepo.save(searchWord);
    }

    private void uppdateCount(String word){
        SearchWord searchWord = searchWordsRepo.findByText(word);
        searchWord.setCount(searchWord.getCount()+1);
        searchWordsRepo.save(searchWord);
    }

    public static void printAllSearchWords(SearchWordsRepo searchWordsRepo){
        List<SearchWord> searchWordList = searchWordsRepo.findAll();
        for(SearchWord word: searchWordList){
            System.out.println(word);
        }
    }



}
