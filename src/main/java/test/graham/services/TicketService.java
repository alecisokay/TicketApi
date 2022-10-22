package test.graham.services;

import test.graham.entities.Ticket;
import java.util.List;

public interface TicketService {
    Ticket createTicket(Ticket ticket);

    List<Ticket> getAllTickets();
}
