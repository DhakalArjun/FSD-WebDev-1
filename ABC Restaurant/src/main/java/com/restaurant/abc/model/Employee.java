package com.restaurant.abc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    @JsonProperty("employee_id")
    private int id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("email")
    private String email;
    private String title;
    private String sin;
    private String dob;
    private String address;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("termination_date")
    private LocalDate terminationDate;
    private double salary;
    @JsonProperty("user_name")
    private String userName;
    private String password;
    private int otp;
}
