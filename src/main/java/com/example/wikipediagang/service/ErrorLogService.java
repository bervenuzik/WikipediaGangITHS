package com.example.wikipediagang.service;

import com.example.wikipediagang.model.ErrorLog;
import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.repo.ErrorLogRepo;
import com.example.wikipediagang.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ErrorLogService {
    private static ErrorLogRepo errorLogRepo ;

    @Autowired
    public ErrorLogService(ErrorLogRepo errorLogRepo) {
        this.errorLogRepo = errorLogRepo;
    }
    @Autowired
    static PersonRepository personRepo;

    public static void handleException(Throwable e, String additionalInfo,Person person){
        String errorMessage = "Exception occurred:" + e.getMessage()+". Additional Info: "+additionalInfo;
        saveErroLog(errorMessage,person);
    }

    public static void saveErroLog(String errorMessage,Person person){

            ErrorLog errorLog = new ErrorLog();
            try{
                errorLog.setPerson(person);
                errorLog.setDate(new Date());
                errorLog.setText(errorMessage);
                errorLogRepo.save(errorLog);
            }catch(Exception e){
                errorLog.setText("Error"+e.getStackTrace());
            }

    }
}