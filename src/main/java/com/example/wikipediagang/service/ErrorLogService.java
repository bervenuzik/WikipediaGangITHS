package com.example.wikipediagang.service;

import com.example.wikipediagang.ScannerHelper;
import com.example.wikipediagang.model.ErrorLog;
import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.repo.ErrorLogRepo;
import com.example.wikipediagang.repo.PersonRepository;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ErrorLogService {
    private static ErrorLogRepo errorLogRepo ;

    //private static final Logger logger = LoggerFactory.getLogger();


    @Autowired
    public ErrorLogService(ErrorLogRepo errorLogRepo) {
        this.errorLogRepo = errorLogRepo;
    }
    @Autowired
    static PersonRepository personRepo;

    public static void handleException(Throwable e, String additionalInfo){
        String errorMessage = "Exception occurred:" + e.getMessage()+". Additional Info: "+additionalInfo;
        saveErroLog(errorMessage,null);
    }

    public static void saveErroLog(String errorMessage,Person person){

            ErrorLog errorLog = new ErrorLog();
            try{
                errorLog.setPerson(person);
                errorLog.setDate(new Date());
                errorLog.setText(errorMessage);
                errorLog.setStatus("unchecked");
                errorLogRepo.save(errorLog);
            }catch(Exception e){
                errorLog.setText("Error"+e.getStackTrace());
            }


    }
    public void uppdateErroLog(){
        System.out.println("input text");
        int text = ScannerHelper.getIntInput();
        ScannerHelper.getStringInput();
        Optional<ErrorLog> erroLog = errorLogRepo.findById(text);
        if(erroLog.isPresent()){
            ErrorLog erro = erroLog.get();
            System.out.println("input status to uppdate");
            String newStatus = ScannerHelper.getStringInput();
            erro.setStatus(newStatus);
            errorLogRepo.save(erro);
        }
    }
}