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
    public Ticket updatePendingTicket(String approvedBy, int id, String decision) {
        Ticket ticket = this.ticketDAO.getTicketById(id);

        ticket.setApprovedBy(approvedBy);
        int result = (decision == "approved")? 1 : 2;
        ticket.setStatus(result);

        Ticket ticketUpdate = this.ticketDAO.updateTicket(ticket);
        return ticketUpdate;
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
