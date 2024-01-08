package com.example.wikipediagang.service;

import com.example.wikipediagang.model.Person;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class ErrorLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(ErrorLoggingAspect.class);
    private List<String> errologs = new ArrayList<>();
   /* @Around("execution(* com.example.wikipediagang.*.*(..))")
    public Object interceptLoggin(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("in");
        Object[] methodArgs = joinPoint.getArgs();
        Object result = joinPoint.proceed();
        if(logger.isErrorEnabled()){
                String text = "your text";
                Person person = extractPersonArgument(methodArgs);
                logger.error("error occurred", text, person);
                ErrorLogService.saveErroLog(text,person);

        }
        return result;
    }
    private Person extractPersonArgument(Object[]methodArgs){
        if (methodArgs.length >= 2 && methodArgs[0] instanceof String && methodArgs[1] instanceof Person) {
            return (Person) methodArgs[1];
        }else{
            return null;
        }

    }
    private void checkForlogError(ProceedingJoinPoint joinPoint) {
        System.out.println("in2");
        String methodName = joinPoint.getSignature().getName();
        if (methodName.contains("log.error")) {
            logger.error("Intercepted log.error in method: {}", joinPoint.getSignature().toShortString());
        }*/


    @AfterReturning
            (pointcut = "execution(* com.example.wikipediagang.service.*.*(..))", returning = "result")

    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        /*if (logger.isErrorEnabled()) {
            try {
                if (result == null) {
                    logger.error("result is null in method" + joinPoint.getSignature().toShortString());
                }
            }catch(Exception ex){
                logger.error("Exception in logAfterReturning",ex);
            }
        }
        Class<?> targetClass=joinPoint.getTarget().getClass();
        Logger logger1;
        try{
            logger1 = LoggerFactory.getLogger(targetClass);
        }catch (Exception e){
            logger1=LoggerFactory.getLogger("com.example.wikipediagang.service.ArticleService");
        }
        if (logger1 !=null){
            logger.debug("After returning: "+result.toString());
        }else{
            System.out.println("Logger is null for class: "+targetClass);
        }*/


        //Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        /*if (logger instanceof ch.qos.logback.classic.Logger) {
            ch.qos.logback.classic.Logger classicLogger = (ch.qos.logback.classic.Logger) logger;

            if (classicLogger.getLevel().isGreaterOrEqual(ch.qos.logback.classic.Level.ERROR)) {

                Object[] args = joinPoint.getArgs();
                if (args.length >= 2 && args[0] instanceof String && args[1] instanceof Person) {
                    ErrorLogService.saveErroLog((String) args[0], (Person) args[1]);
                }
            }

        }*/
    }


    @AfterThrowing(pointcut = "execution(* com.example.wikipediagang.service.*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        String errorMessage = "Error in method" + joinPoint.getSignature().getName() + ": " + ex.getMessage();
        ErrorLogService.handleException(ex, errorMessage);
        /*if (ex instanceof ArticleNotFoundException) {
            String errorMessage = "Error in method" + joinPoint.getSignature().getName() + ": " + exception.getMessage();
            ErrorLogService.handleException(exception, errorMessage);
        }*/
    }

    @After("execution(* com.example.wikipediagang..*.*(..))")
    public void after(JoinPoint joinPoint) {

    }
}