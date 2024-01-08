package com.example.wikipediagang.menu;

public enum UserMenu implements MenuOption{
    LOGOUT("Logout as user"),
    CHANGE_PASSWORD("Change your password"),
    CHANGE_EMAIL("Change your email"),
    WRITE("Write an article"),
    EDIT("Edit an article you have written"),
    SEARCH("Search/Read/Reserve an article"),
    SHOW_RESERVED_HARD_COPIES("Show my reserved hard-copies"),
    RETURN_RESERVED("Return a reserved hard-copy"),
    SHOW_RESERVATIONS_IN_QUEUE("Show reservations in queue");

    private final String displayText;
    UserMenu(String displayText){
        this.displayText=displayText;
    }
    @Override
    public String getDisplayText() {
        return displayText;
    }
}
