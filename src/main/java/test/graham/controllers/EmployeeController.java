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
       if(Driver.currentLoggedEmployee == null) {
           String employeeJSON = ctx.body();

           Gson gsonUandP = new Gson();
           UserPass userpass = gsonUandP.fromJson(employeeJSON, UserPass.class);

           System.out.println(userpass.email);
           System.out.println(userpass.passwd);
           Employee employee = Driver.employeeService.getEmployeeByEmailPassword(userpass.email, userpass.passwd);
           // creates Gson Object\
           Gson gson = new Gson();
           String json = gson.toJson(employee);

           // check what this varibale is
           Driver.currentLoggedEmployee = employee;
           System.out.println(Driver.currentLoggedEmployee);

           ctx.result(json);

       }
       else{ ctx.status(404);}
    };
    public Handler logout = (ctx) -> {
        System.out.println("you logged out");
        Driver.currentLoggedEmployee = null;
        ctx.result("you successfully logged out");
    };







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
