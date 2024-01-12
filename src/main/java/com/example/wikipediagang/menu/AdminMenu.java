package com.example.wikipediagang.menu;

public enum AdminMenu implements MenuOption{
    LOGOUT("Logout as admin"),
    ADD_USER("Add a new user"),
    REMOVE_USER("Remove a user"),
    EDIT_USER("Update a user"),
    SEARCH("Search/Read/Reserve an article"),
    SHOW_RESERVED_HARD_COPIES("Show my reserved hard-copies"),
    SHOW_RESERVATIONS_IN_QUEUE("Show reservations in queue"),
    RETURN_RESERVED("Return a reserved hard-copy"),
    DELETE("Delete an article"),
    EDIT_CATEGORY_LIST("Edit name of an existing category"),
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

