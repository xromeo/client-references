package com.rivera.clientreferences.exception;

import jakarta.persistence.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omar.Rivera on 05/3/2024.
 *
 * @author Omar.Rivera
 */
@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException){
        System.out.println("BusinessException is thrown");
        return new ResponseEntity<List<ErrorModel>>(businessException.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException methodArgumentNotValidException){
        logger.info("MethodArgumentNotValidException is thrown");
        List<ErrorModel> errorModelList = new ArrayList<>();
                List<FieldError> fieldErrorList = methodArgumentNotValidException.getFieldErrors();
        fieldErrorList.stream().forEach(fieldError -> errorModelList.add(new ErrorModel(fieldError.getField(), fieldError.getDefaultMessage())));
        return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(DataIntegrityViolationException dataIntegrityViolationException){
        logger.info("DataIntegrityViolationException is thrown Message = " +  dataIntegrityViolationException.getMessage());
        List<ErrorModel> errorModelList = new ArrayList<>();
        errorModelList.add(new ErrorModel("400", "Data Integrity Violation"));
        return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);

    }
}
