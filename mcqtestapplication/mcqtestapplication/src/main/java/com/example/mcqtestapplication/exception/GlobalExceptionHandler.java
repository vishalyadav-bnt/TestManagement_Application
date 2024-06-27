package com.example.mcqtestapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.mcqtestapplication.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(QuestionModelIsEmpty.class)
    public ResponseEntity<ErrorResponse> HandleObjectEmptyException(QuestionModelIsEmpty e)
    {
        ErrorResponse response=new ErrorResponse(HttpStatus.NOT_FOUND,e.getMessage());
        return new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

}
