package com.example.wikipediagang.service;

import java.util.Scanner;

public class MessageHandlerService {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private Scanner input = new Scanner(System.in);


    public void error(String text){
        System.err.println(text);
    }
    public void warning(String text){
        System.out.println(ANSI_YELLOW + text + ANSI_RESET);
    }

    public void menu(String text){
        System.out.println(ANSI_BLUE + text + ANSI_RESET);
    }

    public void success(String text){
        System.out.println(ANSI_GREEN + text + ANSI_RESET);
    }
    public void question(String text){
        System.out.println(ANSI_CYAN + text + ANSI_RESET);
    }
    public  void message(String text){
        System.out.println(text);
    }
    public boolean tryAgain (){
        String choise;
        while (true) {
            this.question("Do you want to try again? Y/N");
            choise = input.nextLine().trim().toUpperCase();
            if (choise.equals("N")) {
                return false;
            }
            if (choise.equals("Y")) {
                return true;
            }
            this.error("Wrong input , try again");
        }
    }


    @Override
    public String toString() {
        return "MassageHandlerService";
    }
}
