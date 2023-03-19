package com.restaurant.abc.constants;

public class ErrorMessage extends RuntimeException{
    public final static String RESERVATION_NOT_FOUND_WITH_THIS_ID="Reservation not found with reservation_id = %d";
    public final static String RESERVATION_NOT_FOUND_WITH_THIS_NAME="Reservation not found with name= %s";
    public final static String RESERVATION_NOT_FOUND_WITH_THIS_EMAIL="Reservation not found with email= %s";
    public final static String DATABASE_EXCEPTION= "There is a problem in the Server, Please call the Administrator";

    public final static String EMPLOYEE_NOT_FOUND_WITH_THIS_ID="No Employee Found with id = %d";
    public final static String EMPLOYEE_NOT_FOUND_WITH_THESE_EMAIL="No Employee Found with email =%s";
    public final static String EMPLOYEE_ALREADY_EXIST_FOR_THIS_ID= "Employee already exit with employee id = %d";

}


