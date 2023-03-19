package com.restaurant.abc.repository.mapper;

import com.restaurant.abc.repository.entity.EmployeeEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<EmployeeEntity> {
    @Override
    public EmployeeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return EmployeeEntity.builder()
                .id(rs.getInt("employee_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .phone(rs.getString("phone"))
                .email(rs.getString("email"))
                .title(rs.getString("title"))
                .sin(rs.getString("sin"))
                .dob(rs.getDate("dob").toLocalDate())
                .address(rs.getString("address"))
                .startDate(rs.getDate("start_date").toLocalDate())
                .terminationDate(rs.getDate("termination_date").toLocalDate())
                .salary(rs.getDouble("salary"))
                .userName(rs.getString("user_name"))
                .password(rs.getString("password"))
                .otp(rs.getInt("otp"))
                .build();
    }
}
