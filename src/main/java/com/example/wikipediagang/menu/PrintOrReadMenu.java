package com.example.wikipediagang.menu;

public enum PrintOrReadMenu implements MenuOption{
    EXIT("Exit"),
    PRINT("Print a hard-copy of the article."),
    READ("Read the article online.");

    private final String displayText;
     PrintOrReadMenu(String displayText){
         this.displayText = displayText;
     }

    @Override
    public String getDisplayText() {
        return displayText;
    }
}
