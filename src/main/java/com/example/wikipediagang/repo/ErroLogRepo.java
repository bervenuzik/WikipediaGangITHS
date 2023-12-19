package com.example.wikipediagang.repo;

import com.example.wikipediagang.model.ErroLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ErroLogRepo extends JpaRepository<ErroLog, Integer> {

    @Query("select e.text from ErroLog e")
    List<String> selectTextColumn();
    Optional<ErroLog> findByDate(Date date);
    @Query("select e from ErroLog e where e.person.id = :person_id ")
    List<ErroLog> findByPersonId(int person_id);
    List<ErroLog> findByText(String text);

    @Query("select e.text from ErroLog e")
    List<String> findByErroLog(String text);

    boolean existsByPerson_id(int id);


}
