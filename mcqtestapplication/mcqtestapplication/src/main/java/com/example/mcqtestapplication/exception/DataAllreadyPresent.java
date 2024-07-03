package com.example.mcqtestapplication.exception;

public class DataAllreadyPresent extends RuntimeException {
    public DataAllreadyPresent(String str)
    {
        super(str);
    }
}
