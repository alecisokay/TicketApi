package test.graham.repositories;

import test.graham.entities.Ticket;
//import test.graham.entities.Employee;
import java.util.List;

public interface TicketDAO{


    //create
    Ticket createTicket(Ticket ticket);

    Ticket getTicketById(int id);

    Ticket updateTicket(Ticket ticket);


    //
//    //read
//    Book getBookById(int id);
//    List<Book> getAllBooks();
    List<Ticket> getAllTickets();
    List<Ticket> getAllPendingTickets();

    List<Ticket> getTicketByEmail(String email);
//
//    //update
//    Book updateBook(Book book);
//
//    //delete
//    boolean deleteBookById(int id);


}
