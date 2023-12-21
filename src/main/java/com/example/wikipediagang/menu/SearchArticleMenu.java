package com.example.wikipediagang.menu;

public enum SearchArticleMenu implements MenuOption{
    EXIT("Back to the article Menu."),
    TITLE("Search by title of an article"),
    AUTHOR("Search by author of an article");


    private final String displayText;
    SearchArticleMenu(String displayText){
        this.displayText= displayText;
    }
        @Override
    public String getDisplayText() {
        return displayText;
    }
}
