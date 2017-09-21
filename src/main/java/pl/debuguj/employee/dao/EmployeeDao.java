package pl.debuguj.employee.dao;

import pl.debuguj.employee.domain.Employee;

import java.util.List;

/**
 * Created by grzesiek on 15.09.17.
 */
public interface EmployeeDao {

    public Employee getEmployeeById(int id);

    public void saveEmployee(Employee e);

    public List<Employee> getAllEmployees();

    public boolean removeEmployeeById(int id);
}
