package test.graham.controllers;

import com.google.gson.Gson;
import io.javalin.http.Handler;
import test.graham.driver.Driver;
import test.graham.entities.Ticket;

import java.util.List;

public class TicketController {
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
    public Handler createTicket = (ctx) ->{
        String json = ctx.body();
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(json, Ticket.class);
        Ticket registeredTicket = Driver.ticketService.createTicket(ticket);
        String ticketJson = gson.toJson(registeredTicket);
        ctx.status(201); //This is a status code that will tell us how things went
        ctx.result(ticketJson);
    };

    // get pending



    // get request by employee id use global
    //


//    public Handler getBookByIdHandler = (ctx) ->{
//        int id = Integer.parseInt(ctx.pathParam("id"));//This will take what value was in the {id} and turn it into an int for us to use
//        Book book = Driver.bookService.getBookById(id);
//        Gson gson = new Gson();
//        String json = gson.toJson(book);
//        ctx.result(json);
//    };
//
    public Handler getAllTickets = (ctx) ->{
        List<Ticket> ticket = Driver.ticketService.getAllTickets();
        Gson gson = new Gson();
        String json = gson.toJson(ticket);
        ctx.result(json);
        //ctx.result("this is the employee route");
    };
//
public Handler getEmployeeTickets = (ctx) ->{
    //String email = Driver.currentLoggedEmployee.getEmail();
    String email = "test@mail.com";
    List<Ticket> ticket = Driver.ticketService.getTicketByEmail(email);
    Gson gson = new Gson();
    String json = gson.toJson(ticket);
    ctx.result(json);
    //ctx.result("this is the employee route");
};

//    public Handler updateBookHandler = (ctx) ->{
//        String bookJSON = ctx.body();
//        Gson gson = new Gson();
//        Book book = gson.fromJson(bookJSON, Book.class);
//        Book updateBook = Driver.bookService.updateBook(book);
//        String json = gson.toJson(updateBook);
//        ctx.result(json);
//    };
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
