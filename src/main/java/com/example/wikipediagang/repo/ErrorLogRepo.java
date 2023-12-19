package com.example.wikipediagang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ErrorLogRepo extends JpaRepository<ErrorLog, Integer> {

    @Query("select e.text from ErrorLog e")
    List<String> selectTextColumn();
    Optional<ErrorLog> findByDate(Date date);
    @Query("select e from ErrorLog e where e.person.id = :person_id ")
    List<ErrorLog> findByPersonId(int person_id);
    List<ErrorLog> findByText(String text);

    @Query("select e.text from ErrorLog e")
    List<String> findByErrorLog(String text);

    boolean existsByPerson_id(int id);


}
