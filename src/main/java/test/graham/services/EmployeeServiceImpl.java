package test.graham.services;

import test.graham.entities.Employee;
import test.graham.repositories.EmployeeDAO;
import java.util.List;
import java.util.Objects;

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
        Employee employee = this.employeeDAO.getEmployeeByEmail(email);
        // compare the stdout values
        System.out.println(employee.getPasswd());
        System.out.println(passwd);

        /////
        if(Objects.equals(employee.getPasswd(), passwd)){
            System.out.println("equals!");
            return employee;
        }
        else {
            System.out.println("passwords did not match");
        }
        return null;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return this.employeeDAO.getEmployeeByEmail(email);
    }


    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeDAO.getAllEmployees();
        //return null;
    }
}
