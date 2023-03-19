package com.restaurant.abc.service;

import com.restaurant.abc.exception.EmployeeExistException;
import com.restaurant.abc.exception.EmployeeNotFoundException;
import com.restaurant.abc.model.Employee;
import com.restaurant.abc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.restaurant.abc.constants.ErrorMessage.*;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployee();
    }


    public Employee getById(long empId) {
        Employee employeeMatched = employeeRepository.getEmployeeById(empId);
        if(employeeMatched == null){
            throw new EmployeeNotFoundException(String.format(EMPLOYEE_NOT_FOUND_WITH_THIS_ID, empId));
        }
        return employeeMatched;

    }


    public Employee getByEmail(String email) {
        Employee employeeMatched = employeeRepository.getByEmail(email);
        if(employeeMatched == null){
            throw new EmployeeNotFoundException(String.format(EMPLOYEE_NOT_FOUND_WITH_THESE_EMAIL,email));
        }
        return employeeMatched;
    }


    public Employee createNewEmployee(Employee newEmployee) {
        Employee employeeAlreadyPresent = employeeRepository.getEmployeeById(newEmployee.getId());
        if (employeeAlreadyPresent !=null){
            throw new EmployeeExistException(String.format(EMPLOYEE_ALREADY_EXIST_FOR_THIS_ID, newEmployee.getId()));
        }
        return employeeRepository.createNewEmployee(newEmployee);
    }


    public Employee updateEmployee(long empId, Employee employee) {
        Employee employeePresent = employeeRepository.getEmployeeById(empId);
        if (employeePresent == null) {
            throw new EmployeeNotFoundException(String.format(EMPLOYEE_NOT_FOUND_WITH_THIS_ID, empId));
        }
        employeeRepository.update(empId, employee);
        return employeeRepository.getEmployeeById(empId);
    }

}
