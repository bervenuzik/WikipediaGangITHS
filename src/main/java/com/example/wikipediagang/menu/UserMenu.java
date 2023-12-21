package com.example.wikipediagang.menu;

public enum UserMenu implements MenuOption{
    LOUGOUT("Logout as user"),
    CHANGE_PASSWORD("Change your password."),
    CHANGE_EMAIL("Change your email"),
    WRITE("Write an article"),
    CHANGE("Edit an article you have written"),
    SEARCH("Search for an article"),
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
