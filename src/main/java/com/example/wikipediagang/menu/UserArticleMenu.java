package com.example.wikipediagang.menu;

public enum UserArticleMenu implements MenuOption{
    RETURN("Return to the user menu."),
    WRITE("Write an article."),
    CHANGE("Edit an article you hae written."),
    SEARCH("Search for an article"),
    RESERVE("Reserve a hard-copy of an article to borrow."),
    ORDER("Order an hard-copy of an article.");

    private final String displayText;
    UserArticleMenu(String displayText){
        this.displayText= displayText;
    }
    public String getDisplayText(){
        return displayText;
    }
}
