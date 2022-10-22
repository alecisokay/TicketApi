package test.graham.services;

import test.graham.entities.Employee;
import test.graham.repositories.EmployeeDAO;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {this.employeeDAO = employeeDAO;}


    @Override
    public Employee createEmployee(Employee employee) {
        if(employee.getEmail().length() ==0 || employee.getPasswd().length() ==0){
            throw new RuntimeException("something went wrong with email or pass");
        }
        Employee savedEmployee = this.employeeDAO.createEmployee(employee);
        return savedEmployee;
    }

    @Override
    public Employee getEmployeeByEmailPassword(String email, String passwd) {
//        if(book.getTitle().length() == 0){
//            throw new RuntimeException("Book's title cannot be empty");
//        }
//        if(book.getAuthor().length() == 0){
//            throw new RuntimeException("Book's authors cannot be empty");
//        }
        return this.employeeDAO.getEmployeeByEmailPassword(email, passwd);
    }

//    @Override
//    public Employee getEmployeeByEmailPassword(String email, String password) {
//        return this.employeeDAO.getEmployeeByEmailPassword(email, password);
//    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeDAO.getAllEmployees();
        //return null;
    }
}
