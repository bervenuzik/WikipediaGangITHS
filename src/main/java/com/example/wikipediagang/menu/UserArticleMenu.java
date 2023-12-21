package com.example.wikipediagang.menu;

public enum UserArticleMenu implements MenuOption{
    RETURN("Return to the user menu"),
    WRITE("Write an article"),
    CHANGE("Edit an article you have written"),
    SEARCH("Search for an article"),
    SHOW_RESERVED("Show my reserved articles"),
    RETURN_RESERVED("Return a reserved article");

    private final String displayText;
    UserArticleMenu(String displayText){
        this.displayText= displayText;
    }
    public String getDisplayText(){
        return displayText;
    }
}
