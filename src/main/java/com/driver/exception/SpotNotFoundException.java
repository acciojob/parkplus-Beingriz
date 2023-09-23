package com.driver.exception;

public class SpotNotFoundException extends RuntimeException{
    public SpotNotFoundException(String  message){
        super(message);
    }
}
