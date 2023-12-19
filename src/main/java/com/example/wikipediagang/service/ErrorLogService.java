package com.example.wikipediagang.service;

import com.example.wikipediagang.ScannerHelper;
import com.example.wikipediagang.model.ErrorLog;
import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.repo.ErrorLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ErrorLogService {
    @Autowired
    private ErrorLogRepo errorLogRepo;

    public void saveErroLog(){
        ErrorLog errorLog = new ErrorLog();
        try{
            errorLog.setPerson(new Person());
            errorLog.setDate(new Date());
            errorLog.setText("Technical fail");
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