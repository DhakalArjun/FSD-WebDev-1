package com.restaurant.abc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Reservation {
    @JsonProperty("reservation_id")
    private long reservationId;
    @JsonProperty("customer_id")
    private long customerId;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("email")
    private String email;
    @JsonProperty("reservation_date")
    private LocalDate reservationDate;
    @JsonProperty("reservation_start")
    private LocalTime reservationStart;
    @JsonProperty("reservation_end")
    private LocalTime reservationEnd;
    @JsonProperty("guest_number")
    private int guestNumber;
    @JsonProperty("no_of_table")
    private int noOfTables;
    @JsonProperty("communication_mode")
    private String communicationMode;
    @JsonProperty("special_request")
    private String specialRequest;
    @JsonProperty("update_status")
    private String updateStatus;
    @JsonProperty("updated_by")
    private int updatedBy;
}


