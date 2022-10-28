package test.graham.repositories;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.postgresql.util.PSQLException;
import test.graham.entities.Employee;

import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOPostgresTest {

    static EmployeeDAO employeeDAO = new EmployeeDAOPostgres();



//    @Test
//    void create_employee_test() {
//        Employee newEmployee = new Employee("Harry", "Potter", "test3Email@mail.com", "12345", 0);
//        Employee savedEmployee = employeeDAO.createEmployee(newEmployee);
//        //System.out.println(savedEmployee);
//        //Assertions.assertEquals(savedEmployee.getFname(), "Harry");
//        //Assertions.assertNull(savedEmployee);
//    }

    @Test
    void get_employee_by_email_password_test() {
        Employee newEmployee = new Employee("newEmail@mail.com", "12345");
        Employee savedEmployee = employeeDAO.getEmployeeByEmailPassword(newEmployee);
        Assertions.assertEquals(savedEmployee.getPasswd(), "12345");
    }

    @Test
    void get_employee_by_email_test() {
        Employee newEmployee = employeeDAO.getEmployeeByEmail("newEmail@mail.com");
        Assertions.assertEquals(newEmployee.getFname(), "Harry");
    }

    @Test
    void get_all_employees_test() {
        List<Employee> newList = employeeDAO.getAllEmployees();
        Assertions.assertTrue(newList.size()>0);

    }

}