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

    // needs to be added to the search article methods, so we can log all the searches.
    public void addSearchWord(String word){
        if(findSearchWord(word) == null){
            SearchWord searchWord = new SearchWord(word, 1);
            searchWordsRepo.save(searchWord);
        }
    }

    //needs to be added to the search article methods, so we can log all the searches.
    public void uppdateCount(String word){
        SearchWord wordFound = findSearchWord(word);
        if(wordFound!= null){
            wordFound.setCount(wordFound.getCount()+1);
            searchWordsRepo.save(wordFound);
        }
    }

    public SearchWord findSearchWord(String word){
        List<SearchWord> searchWordlist = searchWordsRepo.findByText(word);

        if(searchWordlist.isEmpty()){
            return null;
        }else if (searchWordlist.size()== 1){
            for(SearchWord searchWord: searchWordlist){
                return searchWord;
            }
        }
        return null;
    }

    public static void printAllSearchWords(SearchWordsRepo searchWordsRepo){
        List<SearchWord> searchWordList = searchWordsRepo.findAll();
        for(SearchWord word: searchWordList){
            System.out.println(word);
        }
    }



}
