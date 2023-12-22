package com.example.wikipediagang;

import com.example.wikipediagang.service.MessageHandlerService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class ScannerHelper {

    private static final Scanner sc = new Scanner(System.in).useLocale(Locale.US);
    private static final MessageHandlerService log = new MessageHandlerService();

    public static int getIntInput(int maxValue) {         //to handle invalid user-input instead of an integer
        int number = -1;
        do {
            try {
                number = sc.nextInt();
                if (number < 1 || number > maxValue) {
                    log.error("Invalid Input! 0 again: ");
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                log.error("Not an integer! Try Again: ");
            }
        } while (number < 1|| number > maxValue);
        return number;
    }
    public static int getIntInput() {
        int number = -1;
        do {
            try {
                number = sc.nextInt();
                if (number < 0) {
                    log.error("Invalid Input! Try again: ");
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                log.error("Not an integer! Try Again: ");
            }
        } while (number < 0);
        return number;
    }
    public static String getStringInput() {               //to handle invalid user-input instead of a String
        String s;
        do {
            s = sc.nextLine().trim();
        } while (s.trim().isEmpty());
        return s;
    }

    public static String getTextInput(){
        String input = "";
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            if(!line.equals("THE-END")){
                input = input + line + "\n";
            } else {
                break;
            }

        }
        return input;
    }
    public static void close(){
        sc.close();
    }}
