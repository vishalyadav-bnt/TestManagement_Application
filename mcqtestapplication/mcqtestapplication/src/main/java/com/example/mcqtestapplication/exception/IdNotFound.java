package com.example.mcqtestapplication.exception;

public class IdNotFound extends RuntimeException{
    public IdNotFound(String str)
    {
        super(str);
    }
}
