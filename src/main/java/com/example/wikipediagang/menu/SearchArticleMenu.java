package com.example.wikipediagang.menu;

public enum SearchArticleMenu implements MenuOption{
    EXIT("Back to the Article Menu."),
    TITLE("Search by Title"),
    AUTHOR("Search by Author");


    private final String displayText;
    SearchArticleMenu(String displayText){
        this.displayText= displayText;
    }
        @Override
    public String getDisplayText() {
        return displayText;
    }
}
