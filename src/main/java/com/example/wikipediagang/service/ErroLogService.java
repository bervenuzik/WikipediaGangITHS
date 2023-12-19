package com.example.wikipediagang.service;

import java.util.Date;
import java.util.Optional;
import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.ScannerHelper;
import com.example.wikipediagang.model.ErroLog;
import com.example.wikipediagang.repo.ErroLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ErroLogService {
    @Autowired
    private ErroLogRepo erroLogRepo;

    public void saveErroLog(){
        ErroLog erroLog = new ErroLog();
        try{
            erroLog.setPerson(new Person());
            erroLog.setDate(new Date());
            erroLog.setText("Technical fail");
            erroLog.setStatus("unchecked");
            erroLogRepo.save(erroLog);
        }catch(Exception e){
            erroLog.setText("Error"+e.getStackTrace());
        }
    }
    public void uppdateErroLog(){
        System.out.println("input text");
        int text = ScannerHelper.getIntInput();
        ScannerHelper.getStringInput();
        Optional<ErroLog> erroLog = erroLogRepo.findById(text);
        if(erroLog.isPresent()){
            ErroLog erro = erroLog.get();
            System.out.println("input status to uppdate");
            String newStatus = ScannerHelper.getStringInput();
            erro.setStatus(newStatus);
            erroLogRepo.save(erro);
        }
    }
}