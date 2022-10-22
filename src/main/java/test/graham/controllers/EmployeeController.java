package test.graham.controllers;

import com.google.gson.Gson;
import test.graham.driver.Driver;
import test.graham.entities.Employee;
import io.javalin.http.Handler;

import java.util.List;

public class EmployeeController {


    // These controllers and using what is called Lambdas
//    public Handler createBook = (ctx) ->{
//        String json = ctx.body();
//        Gson gson = new Gson();
//        Book book = gson.fromJson(json, Book.class);
//        Book registeredBook = Driver.bookService.createBook(book);
//        String bookJson = gson.toJson(registeredBook);
//        ctx.status(201); //This is a status code that will tell us how things went
//        ctx.result(bookJson);
//    };
    public Handler createUser = (ctx) ->{
        String json = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json, Employee.class);
        Employee registeredEmployee = Driver.employeeService.createEmployee(employee);
        String employeeJson = gson.toJson(registeredEmployee);
        ctx.status(201); //This is a status code that will tell us how things went
        ctx.result(employeeJson);
    };
//
//    public Handler getBookByIdHandler = (ctx) ->{
//        int id = Integer.parseInt(ctx.pathParam("id"));//This will take what value was in the {id} and turn it into an int for us to use
//        Book book = Driver.bookService.getBookById(id);
//        Gson gson = new Gson();
//        String json = gson.toJson(book);
//        ctx.result(json);
//    };
//
    public Handler getAllEmployees = (ctx) ->{
        List<Employee> employee = Driver.employeeService.getAllEmployees();
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        ctx.result(json);
        //ctx.result("this is the employee route");
    };
//
//    public Handler updateBookHandler = (ctx) ->{
//        String bookJSON = ctx.body();
//        Gson gson = new Gson();
//        Book book = gson.fromJson(bookJSON, Book.class);
//        Book updateBook = Driver.bookService.updateBook(book);
//        String json = gson.toJson(updateBook);
//        ctx.result(json);
//    };

   public Handler loginHandler = (ctx) ->{
        String employeeJSON = ctx.body();
        String email = "testUser@mail.com";
        String passwd = "12345";

//       // creates an employee object from the Json we pass in from the ctx.body params
//        Employee employee = gson.fromJson(employeeJSON, Employee.class);
//
//       // executes our login function (Book Updatabook = Employee loginEmployee function) -> employeeService.loginEmployee(employee)
////        Book updateBook = Driver.bookService.updateBook(book);
//       Employee checkEmployee = Driver.employeeService.getEmployeeByEmailPassword(employee);
//       // makes a json string that is comprised of the result of the return value of out employeeService.loginEmployee(employee)
////        String json = gson.toJson(updateBook);
//       String json = gson.toJson(checkEmployee);
//       ctx.result(json);
       //System.out.println(email);
       Employee employee = Driver.employeeService.getEmployeeByEmailPassword(email, passwd);
       // creates Gson Object\
       Gson gson = new Gson();
       String json = gson.toJson(employee);
       ctx.result(json);


    };


//
//
//    public Handler deleteBookHandler = (ctx) ->{
//        int id = Integer.parseInt(ctx.pathParam("id"));
//        boolean result = Driver.bookService.deleteBookById(id);
//        if(result){
//            ctx.status(204);
//        }
//        else{
//            ctx.status(400);
//            ctx.result("Could not process your delete request");
//        }
//    };

}
