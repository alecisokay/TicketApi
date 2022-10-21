package test.graham.repositories;

import test.graham.entities.Employee;
import java.util.List;

// DAO stands for Data Access Object
// This will allow us to do our CRUD operations (Create Read Update Delete)
public interface EmployeeDAO {

    //Interfaces are similar to standard classes, but you cant have ANY concrete methods in them

    //create
    //Book createBook(Book book);
    Employee createEmployee(Employee employee);

//
//    //read
//    Book getBookById(int id);
//    List<Book> getAllBooks();
//
//    //update
//    Book updateBook(Book book);
//
//    //delete
//    boolean deleteBookById(int id);


}