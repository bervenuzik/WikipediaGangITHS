package com.example.wikipediagang.menu;

public enum StartMenu implements  MenuOption {
    EXIT ("Exit the program"),
    LOGIN ("Login");


    private final String displayText;
    StartMenu(String displayText){
        this.displayText = displayText;
    }
    @Override
    public String getDisplayText(){
        return displayText;
    }
}
