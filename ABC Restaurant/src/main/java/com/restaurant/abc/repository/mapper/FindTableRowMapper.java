package com.restaurant.abc.repository.mapper;

import com.restaurant.abc.repository.entity.FindTableEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FindTableRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FindTableEntity.builder()
                .timeSlot(rs.getTime("time_slot").toLocalTime())
                .availableTables(rs.getInt("available_tables"))
                .build();
    }
}
