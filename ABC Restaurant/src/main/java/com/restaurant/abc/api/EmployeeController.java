package com.restaurant.abc.api;

import com.restaurant.abc.exception.DatabaseException;
import com.restaurant.abc.exception.EmployeeExistException;
import com.restaurant.abc.exception.EmployeeNotFoundException;
import com.restaurant.abc.model.Employee;
import com.restaurant.abc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abc")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //GET ALL @GetMapping("/employees")
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    // GET by ID
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getById(@PathVariable long employeeId){
        try {
            return new ResponseEntity<>(employeeService.getById(employeeId), HttpStatus.OK);
        }
        catch (EmployeeNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees/employee")
    public ResponseEntity<Employee> getByEmail(@RequestParam String email){
        try {
            return new ResponseEntity<>(employeeService.getByEmail(email), HttpStatus.OK);
        }
        catch (EmployeeNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //SAVE
    @PostMapping("/employees")
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee newEmployee){
        try {
            return new ResponseEntity<>(employeeService.createNewEmployee(newEmployee), HttpStatus.CREATED);
        }
        catch (EmployeeExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (DatabaseException e){
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee){
        try {
            return new ResponseEntity<>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
        }
        catch(EmployeeNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (DatabaseException e){
            return new ResponseEntity(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
