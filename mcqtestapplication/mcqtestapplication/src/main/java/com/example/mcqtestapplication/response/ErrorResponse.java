package com.example.mcqtestapplication.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    public ErrorResponse(HttpStatus notFound, String message) {
        //TODO Auto-generated constructor stub
    }
    private int statuscode;
    private String msg;

}
