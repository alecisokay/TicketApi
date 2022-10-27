package test.graham.services;

import test.graham.entities.Ticket;
import java.util.List;

public interface TicketService {
    Ticket createTicket(Ticket ticket);

    Ticket updatePendingTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    List<Ticket> getAllTickets();

    List<Ticket> getTicketByEmail(String email);
    List<Ticket> getAllPendingTickets();
}
