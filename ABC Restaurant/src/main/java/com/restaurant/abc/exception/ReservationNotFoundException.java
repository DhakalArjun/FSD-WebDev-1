package com.restaurant.abc.exception;

public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException (String message){
        super(message);
    }
}