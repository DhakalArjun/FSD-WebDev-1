package com.restaurant.abc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FindCustomerId {
    @JsonProperty("customer_id")
    private long customerID;
}
