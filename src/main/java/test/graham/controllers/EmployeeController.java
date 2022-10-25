package test.graham.controllers;

import com.google.gson.Gson;
import test.graham.driver.Driver;
import test.graham.entities.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Handler;

import java.util.List;


class UserPass {
    String email;
    String passwd;

    UserPass(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }
}

public class EmployeeController {


    public Handler createUser = (ctx) ->{
        String json = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json, Employee.class);
        Employee registeredEmployee = Driver.employeeService.createEmployee(employee);
        String employeeJson = gson.toJson(registeredEmployee);
        ctx.status(201); //This is a status code that will tell us how things went
        ctx.result(employeeJson);
    };


    // used for debugging purposes to check users in DB
    public Handler getAllEmployees = (ctx) ->{
        List<Employee> employee = Driver.employeeService.getAllEmployees();
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        ctx.result(json);
    };



    // login
   public Handler loginHandler = (ctx) ->{
       if(Driver.currentLoggedEmployee == null) {
           String employeeJSON = ctx.body();

           Gson gsonUandP = new Gson();
           UserPass userpass = gsonUandP.fromJson(employeeJSON, UserPass.class);

           System.out.println(userpass.email);
           System.out.println(userpass.passwd);
           Employee employee = Driver.employeeService.getEmployeeByEmailPassword(userpass.email, userpass.passwd);
           Gson gson = new Gson();
           String json = gson.toJson(employee);

           // check what this varibale is
           Driver.currentLoggedEmployee = employee;
           System.out.println(Driver.currentLoggedEmployee);
           ctx.result(json);

       }
       else{
           ctx.status(404);
           ctx.result("you are logged in already");
       }
    };
    public Handler logout = (ctx) -> {
        System.out.println("you logged out");
        Driver.currentLoggedEmployee = null;
        ctx.result("you successfully logged out");
    };
}
