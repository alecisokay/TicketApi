package test.graham.repositories;

import test.graham.entities.Ticket;
import java.util.List;

public interface TicketDAO {

    //Interfaces are similar to standard classes, but you cant have ANY concrete methods in them

    //create
    //Book createBook(Book book);
    Ticket createTicket(Ticket ticket);

    //
//    //read
//    Book getBookById(int id);
//    List<Book> getAllBooks();
    List<Ticket> getAllTickets();
//
//    //update
//    Book updateBook(Book book);
//
//    //delete
//    boolean deleteBookById(int id);


}
