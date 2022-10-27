package test.graham.controllers;

import com.google.gson.Gson;
import io.javalin.http.Handler;
import test.graham.driver.Driver;
import test.graham.entities.Ticket;

import java.util.List;

class TicketId {
    int id;
    String approvedBy;
    String decision;

    public TicketId(int id, String approvedBy, String decision) {
        this.id = id;
        this.approvedBy = approvedBy;
        this.decision = decision;
    }
}

public class TicketController {


    // create a new ticket if you are logged in
    public Handler createTicket = (ctx) -> {
        System.out.println(Driver.currentLoggedEmployee);
            if(Driver.currentLoggedEmployee != null) {
                try {
                    String json = ctx.body();
                    Gson gson = new Gson();
                    Ticket ticket = gson.fromJson(json, Ticket.class);
                    Ticket registeredTicket = Driver.ticketService.createTicket(ticket);
                    String ticketJson = gson.toJson(registeredTicket);
                    ctx.status(201); //This is a status code that will tell us how things went
                    ctx.result(ticketJson);
                }
                catch (Exception e){
                    System.out.println(e);
                    ctx.result("there was an error");
                }
            }

        else {
            ctx.status(400);
            ctx.result("please log in");
            System.out.println("please log in");
        }

    };

    // get pending tickets if you are admin
    public Handler getAllPendingTickets = (ctx) -> {
        try {
            System.out.println(Driver.currentLoggedEmployee.getIsAdmin());
            if (Driver.currentLoggedEmployee.getIsAdmin() == 1) {
                List<Ticket> ticket = Driver.ticketService.getAllPendingTickets();
                Gson gson = new Gson();
                String json = gson.toJson(ticket);
                ctx.result(json);
            }
            else{
                ctx.result("you are not authorized to do this action");

            }
        } catch (Exception e) {
            ctx.status(400);
            ctx.result("you are not authorized to do this action");
            System.out.println("you are not authorized to do this action");
        }
    };

    // this will show all tickets in the database, not for demo, this is debugging stuff
    public Handler getAllTickets = (ctx) -> {
        List<Ticket> ticket = Driver.ticketService.getAllTickets();
        Gson gson = new Gson();
        String json = gson.toJson(ticket);
        ctx.result(json);
    };

    // check your tickets
    public Handler getEmployeeTickets = (ctx) -> {
        try {
            if (Driver.currentLoggedEmployee == null) {
                String email = Driver.currentLoggedEmployee.getEmail();
                //String email = "test@mail.com";
                List<Ticket> ticket = Driver.ticketService.getTicketByEmail(email);
                Gson gson = new Gson();
                String json = gson.toJson(ticket);
                ctx.result(json);
            }
        } catch (Exception e){
            ctx.status(400);
            ctx.result("You are not authorized to access this");
            System.out.println("you are not authorized to access this");
        }
    };

    // update Ticket as admin
    public Handler updateTicket = (ctx) -> {
        try {
            if (Driver.currentLoggedEmployee.getIsAdmin() == 1) {
                String ticketJSON = ctx.body();
                Gson gson = new Gson();
                TicketId ticketId = gson.fromJson(ticketJSON, TicketId.class);
                Ticket ticket = Driver.ticketService.updatePendingTicket(ticketId.approvedBy, ticketId.id, ticketId.decision);
                Driver.ticketService.updateTicket(ticket);
                System.out.println(ticket);
                String json = gson.toJson(ticket);
                ctx.result(json);
            }
        } catch (Exception e ) {
            ctx.status(400);
            ctx.result("You are not authorized to access this");
            System.out.println("you are not authorized to access this");
        }
    };
}
