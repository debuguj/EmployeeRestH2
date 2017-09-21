package pl.debuguj.employee.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.debuguj.employee.dao.EmployeeDao;
import pl.debuguj.employee.domain.Employee;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by grzesiek on 15.09.17.
 */
@RestController
@Transactional
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeDao employeeDao;

    @GetMapping(path="/getEmployees")
    public List<Employee> getAllEmployees(){
        return employeeDao.getAllEmployees();
    }

    @GetMapping(path="/getEmployeeById/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId){

        return employeeDao.getEmployeeById(employeeId);
    }

    @GetMapping("/removeEmployee/{employeeId}")
    public HttpEntity<Employee> removeEmployee(@PathVariable int employeeId){
        logger.info(Integer.toString(employeeId));
        Employee e = employeeDao.getEmployeeById(employeeId);
        if(employeeDao.removeEmployeeById(employeeId)){
            return new HttpEntity(e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/addEmployee")
    public Employee addEmployee(

            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("salary") BigDecimal salary,
            @RequestParam("classification") int classification
    ){
        Employee e = new Employee(name, surname, salary, classification);
        logger.info(e.toString());
        employeeDao.saveEmployee(e);
        return e;
    }
}
