package com.example.wikipediagang.menu;

public enum UserMenu implements MenuOption{
    LOGOUT("Logout as user"),
    CHANGE_PASSWORD("Change your password"),
    CHANGE_EMAIL("Change your email"),
    WRITE("Write an article"),
    EDIT("Edit an article you have written"),
    SEARCH("Search/Read an article"),
    SHOW_RESERVED("Show my reserved articles"),
    RETURN_RESERVED("Return a reserved article");
    private final String displayText;
    UserMenu(String displayText){
        this.displayText=displayText;
    }
    @Override
    public String getDisplayText() {
        return displayText;
    }
}
