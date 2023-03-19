package com.restaurant.abc.repository;

import com.restaurant.abc.exception.DatabaseException;
import com.restaurant.abc.model.Employee;
import com.restaurant.abc.repository.entity.EmployeeEntity;
import com.restaurant.abc.repository.mapper.EmployeeRowMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.restaurant.abc.constants.ErrorMessage.DATABASE_EXCEPTION;

@Repository
public class EmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ModelMapper modelMapper;

    public List<Employee> getAllEmployee(){
        try {
            List<EmployeeEntity> employeeEntityList = jdbcTemplate.query("SELECT * FROM employee", new EmployeeRowMapper());
            List<Employee> employeeResult = new ArrayList<>();
            for (EmployeeEntity entity : employeeEntityList) {
                Employee employee = modelMapper.map(entity, Employee.class);
                employeeResult.add(employee);
            }
            return employeeResult;
        }
        catch (Exception ex){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }
    }

    public Employee getEmployeeById(long employeeId){
        try {
            String sqlStr = "SELECT * FROM employee WHERE employee_id=?";
            EmployeeEntity employeeEntity = jdbcTemplate.queryForObject(sqlStr, new EmployeeRowMapper(), employeeId);
            return modelMapper.map(employeeEntity, Employee.class);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception ex){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }
    }

    public Employee getByEmail(String email) {
        try {
            String sqlStr = "SELECT * FROM employee WHERE email=?";
            EmployeeEntity employeeEntity = jdbcTemplate.queryForObject(sqlStr, new EmployeeRowMapper(), email);
            return modelMapper.map(employeeEntity, Employee.class);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
        catch (Exception exc){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }
    }

    public Employee createNewEmployee (Employee newEmployee){
        try{
            String sqlStr = "INSERT INTO employee (first_name, last_name, phone, email, title, sin, dob, address, start_date, termination_date, salary, user_name, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sqlStr, newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getPhone(), newEmployee.getEmail(), newEmployee.getTitle(), newEmployee.getSin(), newEmployee.getDob(), newEmployee.getAddress(), newEmployee.getStartDate(), newEmployee.getTerminationDate(), newEmployee.getSalary(), newEmployee.getUserName(), newEmployee.getPassword());
            long employeeId = jdbcTemplate.queryForObject("SELECT MAX(id) FROM employee", Integer.class);
            return getEmployeeById(employeeId);
        }
        catch (Exception ex){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }

    }

    public void update(long id, Employee employee) {
        try{
            String sqlStr = "UPDATE employee SET first_name=?, last_name=?, phone=?, email=?, title=?, sin=?, dob=?, address=?, start_date=?, termination_date=?, salary=?, user_name=?, password=? WHERE employee_id=?";
            jdbcTemplate.update(sqlStr, employee.getFirstName(), employee.getLastName(), employee.getPhone(), employee.getEmail(), employee.getTitle(), employee.getSin(), employee.getDob(), employee.getAddress(), employee.getStartDate(), employee.getTerminationDate(), employee.getSalary(), employee.getUserName(), employee.getPassword(), id);
        }
        catch (Exception ex){
            throw new DatabaseException(DATABASE_EXCEPTION);
        }
    }

} //end of class
