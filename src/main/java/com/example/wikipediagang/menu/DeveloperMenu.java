package com.example.wikipediagang.menu;

public enum DeveloperMenu implements MenuOption{

    LOGOUT("Logout as developer"),
    ERROR_LOG("Show all Errors"),
    ERROR_HANDLE("Handle Errors");
    private final String displayText;

    DeveloperMenu(String displayText){
        this.displayText= displayText;
    }
    @Override
    public String getDisplayText() {
        return displayText;
    }
}
