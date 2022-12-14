package test.graham.services;

import test.graham.entities.Employee;
import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    //

    Employee getEmployeeByEmailPassword(Employee employee);




    Employee getEmployeeByEmail(String email);
    // list all employees
    List<Employee> getAllEmployees();

    // delete employee
}
