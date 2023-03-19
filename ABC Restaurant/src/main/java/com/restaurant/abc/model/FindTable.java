package com.restaurant.abc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FindTable {
    @JsonProperty("time_slot")
    private LocalTime timeSlot;
    @JsonProperty("available_tables")
    private int availableTables;
}
