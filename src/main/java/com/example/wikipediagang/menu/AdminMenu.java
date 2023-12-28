package com.example.wikipediagang.menu;

public enum AdminMenu implements MenuOption{
    LOGOUT("Logout as admin"),
    ADD_USER("Add a new user"),
    REMOVE_USER("Remove a user"),
    EDIT_USER("Update a user"),
    SEARCH("Search/read an article"),
    WRITE("Write an article"),
    DELETE("Delete an article"),
    EDIT_CATEGORY_LIST("Edit an available category"),
    REVIEW("Review an article");

    private final String displayText;
    AdminMenu(String displaytext){
        this.displayText = displaytext;
    }
    @Override
    public String getDisplayText(){
        return displayText;
    }
}

