package com.example.wikipediagang.menu;

public enum StartMenu implements  MenuOption {
    EXIT ("Exit the program"),
    ADMIN ("Login as Admin"),
    USER("Login as user"),
    DEVELOPER ("Login as developer");

    private final String displayText;
    StartMenu(String displayText){
        this.displayText = displayText;
    }
    @Override
    public String getDisplayText(){
        return displayText;
    }
}
