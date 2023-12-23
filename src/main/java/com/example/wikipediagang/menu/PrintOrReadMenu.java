package com.example.wikipediagang.menu;

public enum PrintOrReadMenu implements MenuOption{
    EXIT("Exit"),
    READ_ONLINE("Read the article online"),
    RESERVE("Reserve a hard-copy of the article"),
    PRINT("Order a personal hard-copy of the article");

    private final String displayText;
     PrintOrReadMenu(String displayText){
         this.displayText = displayText;
     }

    @Override
    public String getDisplayText() {
        return displayText;
    }
}
