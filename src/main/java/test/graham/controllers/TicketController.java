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

    public Handler createTicket = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(json, Ticket.class);
        Ticket registeredTicket = Driver.ticketService.createTicket(ticket);
        String ticketJson = gson.toJson(registeredTicket);
        ctx.status(201); //This is a status code that will tell us how things went
        ctx.result(ticketJson);
    };

    // get pending
    public Handler getAllPendingTickets = (ctx) -> {
        if (Driver.currentLoggedEmployee.getIsAdmin() == 1) {
            List<Ticket> ticket = Driver.ticketService.getAllPendingTickets();
            Gson gson = new Gson();
            String json = gson.toJson(ticket);
            ctx.result(json);
        } else {
            ctx.status(400);
            ctx.result("you are not admin bro");
            System.out.println("you are not admin bro");
        }
    };


    public Handler getAllTickets = (ctx) -> {
        List<Ticket> ticket = Driver.ticketService.getAllTickets();
        Gson gson = new Gson();
        String json = gson.toJson(ticket);
        ctx.result(json);
        //ctx.result("this is the employee route");
    };
    //
    public Handler getEmployeeTickets = (ctx) -> {
        String email = Driver.currentLoggedEmployee.getEmail();
        //String email = "test@mail.com";
        List<Ticket> ticket = Driver.ticketService.getTicketByEmail(email);
        Gson gson = new Gson();
        String json = gson.toJson(ticket);
        ctx.result(json);
        //ctx.result("this is the employee route");
    };


    public Handler updateTicket = (ctx) -> {
        String ticketJSON = ctx.body();
        Gson gson = new Gson();
        TicketId ticketId = gson.fromJson(ticketJSON, TicketId.class);
        Ticket ticket = Driver.ticketService.updatePendingTicket(ticketId.approvedBy, ticketId.id, ticketId.decision);
        Driver.ticketService.updateTicket(ticket);
        System.out.println(ticket);

        String json = gson.toJson(ticket);
        ctx.result(json);
    };
}
