package com.example.wikipediagang.menu;

public enum AdminMenu implements MenuOption{
    LOGOUT("Logout as admin."),
    ADD_USER("Add a new user."),
    REMOVE_USER("Remove a user"),
    EDIT_USER("Update a user"),
    CHANGE_PRIVILEGES("Change privilages of a user"),
    WRITE("Write an article."),
    REVIEW("Review an article."),
    DELETE("Delete an article."),
    SEARCH("Search/read an article.");

    private final String displayText;
    AdminMenu(String displaytext){
        this.displayText = displaytext;
    }
    @Override
    public String getDisplayText(){
        return displayText;
    }
}

