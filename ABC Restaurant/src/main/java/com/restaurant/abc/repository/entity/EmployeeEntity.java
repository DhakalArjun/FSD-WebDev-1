package com.restaurant.abc.repository.entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeEntity{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String title;
    private String sin;
    private LocalDate dob;
    private String address;
    private LocalDate startDate;
    private LocalDate terminationDate;
    private double salary;
    private String userName;
    private String password;
    private int otp;
}
