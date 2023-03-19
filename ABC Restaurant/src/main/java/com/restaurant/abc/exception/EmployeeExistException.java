package com.restaurant.abc.exception;

public class EmployeeExistException extends RuntimeException{

    public EmployeeExistException(String message){
        super(message);
    }
}
