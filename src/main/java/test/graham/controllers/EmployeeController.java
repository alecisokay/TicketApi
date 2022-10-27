package test.graham.controllers;

import com.google.gson.Gson;
import test.graham.driver.Driver;
import test.graham.entities.Employee;


import io.javalin.http.Handler;
import test.graham.exceptions.UserTakenException;

import java.sql.SQLException;
import java.util.List;


public class EmployeeController {


    public Handler createUser = (ctx) ->{
        try {
            String json = ctx.body();
            Gson gson = new Gson();
            Employee employee = gson.fromJson(json, Employee.class);
            Employee registeredEmployee = Driver.employeeService.createEmployee(employee);
            String employeeJson = gson.toJson(registeredEmployee);
            ctx.status(201); //This is a status code that will tell us how things went
            ctx.result(employeeJson);
        }
        catch (UserTakenException e) {
            // check correct error code
            ctx.status(400);
            ctx.result("this user was already taken");

        }
        catch (Exception e) {
            e.printStackTrace();
            ctx.result("there was an issue creating user");
        }
    };


    // used for debugging purposes to check users in DB
    public Handler getAllEmployees = (ctx) ->{
        List<Employee> employee = Driver.employeeService.getAllEmployees();
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        ctx.result(json);
    };



    // login
   public Handler loginHandler = (ctx) -> {
        //System.out.println("login handler fired");
        //System.out.println(Driver.currentLoggedEmployee);

        if (Driver.currentLoggedEmployee == null) {
            try {
                String employeeJSON = ctx.body();

                Gson gsonUandP = new Gson();
                Employee userpass = gsonUandP.fromJson(employeeJSON, Employee.class);

                Employee employee = Driver.employeeService.getEmployeeByEmailPassword(userpass);
                Gson gson = new Gson();
                String json = gson.toJson(employee);

                if(employee == null) {
                    ctx.result("your password was incorrect");
                }
                else{
                    Driver.currentLoggedEmployee = employee;
                    System.out.println(Driver.currentLoggedEmployee);
                    ctx.result(json);
                }
            }
         catch(Exception e){
            ctx.status(401);
            ctx.result("could not find user");
        }
    }
        else{
            ctx.status(401);
            ctx.result("you are already logged in");
        }
   };

    public Handler logout = (ctx) -> {
        if (Driver.currentLoggedEmployee != null){
            //System.out.println("you logged out");
            Driver.currentLoggedEmployee = null;
            //System.out.println(Driver.currentLoggedEmployee);
            ctx.result("you successfully logged out");
        }
        else{
            ctx.result("you arent logged in");
        }
    };
}
