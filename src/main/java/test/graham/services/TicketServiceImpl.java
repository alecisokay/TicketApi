package test.graham.services;
import test.graham.entities.Ticket;
import test.graham.repositories.TicketDAO;
import test.graham.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TicketServiceImpl implements TicketService{

    private TicketDAO ticketDAO;

    public TicketServiceImpl(TicketDAO ticketDAO) {this.ticketDAO = ticketDAO;}


    @Override
    public Ticket createTicket(Ticket ticket) {
        return this.ticketDAO.createTicket(ticket);
    }

    @Override
    public Ticket updatePendingTicket(Ticket tickets) {
        System.out.println(tickets);
        try {
            Ticket ticket = this.ticketDAO.getTicketById(tickets.getId());
            if (!ticket.getChanged()) {

                ticket.setApprovedBy(tickets.getApprovedBy());
                ticket.setStatus(tickets.getStatus());
                ticket.setChanged(true);
                return this.ticketDAO.updateTicket(ticket);
            }
//
        }
        catch (Exception e) {
            System.out.println("you cannot reverse a decision");
            throw new RuntimeException("you cannot reverse a decision");
        }

        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return this.ticketDAO.updateTicket(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return this.ticketDAO.getAllTickets();
        //return null;
    }

    @Override
    public List<Ticket> getTicketByEmail(String email) {
        return this.ticketDAO.getTicketByEmail(email);
    }

    @Override
    public List<Ticket> getAllPendingTickets() {
        return this.ticketDAO.getAllPendingTickets();
    }

}
