package com.restaurant.abc.repository.mapper;


import com.restaurant.abc.repository.entity.ReservationEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ReservationEntity.builder()
                .reservationId(rs.getInt("reservation_id"))
                .customerId(rs.getInt("customer_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .phone(rs.getString("phone"))
                .email(rs.getString("email"))
                .reservationDate(rs.getDate("reservation_date").toLocalDate())
                .reservationStart(rs.getTime("reservation_start").toLocalTime())
                .reservationEnd(rs.getTime("reservation_end").toLocalTime())
                .guestNumber(rs.getInt("guest_number"))
                .noOfTables(rs.getInt("no_of_table"))
                .communicationMode(rs.getString("communication_mode"))
                .specialRequest(rs.getString("special_request"))
                .updateStatus(rs.getString("update_status"))
                .updatedBy(rs.getInt("updated_by"))
                .build();
    }
}
