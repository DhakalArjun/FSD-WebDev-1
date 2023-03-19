package com.restaurant.abc.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class ReservationEntity {
    private long reservationId;
    private long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate reservationDate;
    private LocalTime reservationStart;
    private LocalTime reservationEnd;
    private int guestNumber;
    private int noOfTables;
    private String communicationMode;
    private String specialRequest;
    private String updateStatus;
    private int updatedBy;
}
