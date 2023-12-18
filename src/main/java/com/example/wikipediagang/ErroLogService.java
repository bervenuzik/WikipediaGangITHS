package com.example.wikipediagang;

import javax.naming.AuthenticationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ErroLogService {
    private ErroLogRepo erroLogRepo;


    public ErroLogService(ErroLogRepo erroLogRepo) {
        this.erroLogRepo = erroLogRepo;
    }


    public void saveErroLog(){
        ErroLog erroLog = new ErroLog();
        try{
            erroLog.setPerson(new Person());
            erroLog.setDate(new Date());
            erroLog.setText("Teckniska fel");
            erroLog.setStatus("unchecked");
            erroLogRepo.save(erroLog);
        }catch(Exception e){
            erroLog.setText("Erro"+e.getStackTrace());
        }
    }
    public void uppdateErroLog(){
        System.out.println("input text");
        int text = MyMain.input.nextInt();
        MyMain.input.nextLine();
        Optional<ErroLog> erroLog = erroLogRepo.findById(text);
        if(erroLog.isPresent()){
            ErroLog erro = erroLog.get();
            System.out.println("input status to uppdate");
            String newStatus = MyMain.input.nextLine();
            erro.setStatus(newStatus);
            erroLogRepo.save(erro);
        }
    }
}