package com.restaurant.abc.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
public class FindTableEntity {
    private LocalTime timeSlot;
    private int availableTables;
}
