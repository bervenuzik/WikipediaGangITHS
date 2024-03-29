package com.example.wikipediagang.service;

import com.example.wikipediagang.model.Person;
import com.example.wikipediagang.repo.PersonRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class ErrorLoggingAspect {
    private  PersonRepository personRepository;

    @AfterThrowing(pointcut = "execution(* com.example.wikipediagang.service.*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        String errorMessage = "Error in method" + joinPoint.getSignature().getName() + ": " + ex.getMessage();
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (Object arg : args) {
                if (arg instanceof Person) {
                    ErrorLogService.handleException(ex, errorMessage, (Person) arg);
                }
            }
        } else {
            Optional<Person> defaultPerson = personRepository.findById(9);
            ErrorLogService.handleException(ex, errorMessage, defaultPerson.get());
        }
    }
  }