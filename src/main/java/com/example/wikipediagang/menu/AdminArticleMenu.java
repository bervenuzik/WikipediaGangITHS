package com.example.wikipediagang.menu;

public enum AdminArticleMenu implements MenuOption{
    RETURN("Return to the admin menu."),
    WRITE("Write an article."),
    CHANGE("Update an article."),
    REVIEW("Review an article."),
    DELETE("Delete an article."),
    SEARCH("Search for an article.");

    private final String displayText;
    AdminArticleMenu(String displayText){
        this.displayText= displayText;
    }
    @Override
    public String getDisplayText() {
        return displayText;
    }
}
