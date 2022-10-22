package test.graham.services;
import test.graham.entities.Ticket;
import test.graham.repositories.TicketDAO;
import java.util.List;

public class TicketServiceImpl implements TicketService{

    private TicketDAO ticketDAO;

    public TicketServiceImpl(TicketDAO ticketDAO) {this.ticketDAO = ticketDAO;}


    @Override
    public Ticket createTicket(Ticket ticket) {
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return this.ticketDAO.getAllTickets();
        //return null;
    }
}
