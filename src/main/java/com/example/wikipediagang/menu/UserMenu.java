package com.example.wikipediagang.menu;

public enum UserMenu implements MenuOption{
    LOUGOUT("Logout as user"),
    CHANGE_PASSWORD("Change your password."),
    CHANGE_EMAIL("Change your email"),
    ARTICLE("Go to Article Menu.");
    private final String displayText;
    UserMenu(String displayText){
        this.displayText=displayText;
    }
    @Override
    public String getDisplayText() {
        return displayText;
    }
}
