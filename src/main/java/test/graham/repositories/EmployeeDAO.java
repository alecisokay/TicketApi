package test.graham.repositories;

import test.graham.entities.Employee;
import java.util.List;

// DAO stands for Data Access Object
// This will allow us to do our CRUD operations (Create Read Update Delete)
public interface EmployeeDAO {

    //Interfaces are similar to standard classes, but you cant have ANY concrete methods in them

    //create
    Employee createEmployee(Employee employee);

//
//    //read
    Employee getEmployeeByEmailPassword(Employee employee);

    Employee getEmployeeByEmail(String email);
    List<Employee> getAllEmployees();
//
//    //update
//    Book updateBook(Book book);
//
//    //delete
//    boolean deleteBookById(int id);


}