package com.restaurant.abc.repository.mapper;

import com.restaurant.abc.repository.entity.FindCustomerIdEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FindCustomerIDRowMapper implements RowMapper <FindCustomerIdEntity>{
    @Override
    public FindCustomerIdEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FindCustomerIdEntity.builder()
                .customerID(rs.getInt("customer_id"))
                .build();
    }
}
