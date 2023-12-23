package com.example.wikipediagang.menu;

public enum EditArticleMenu implements MenuOption{
    EXIT("Exit the editing mode"),
    TITLE("Edit the title"),
    CONTENT("Edit the content"),
    CATEGORY("Edit the category");

    private final String displayText;
    EditArticleMenu(String displayText){
        this.displayText= displayText;
    }

    @Override
    public String getDisplayText() {
        return displayText;
    }
}
