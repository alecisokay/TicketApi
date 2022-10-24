package test.graham.repositories;

import test.graham.entities.Ticket;
//import test.graham.entities.Employee;
import java.util.List;

public interface TicketDAO{


    //create
    Ticket createTicket(Ticket ticket);

    //
//    //read
//    Book getBookById(int id);
//    List<Book> getAllBooks();
    List<Ticket> getAllTickets();

    List<Ticket> getTicketByEmail(String email);
//
//    //update
//    Book updateBook(Book book);
//
//    //delete
//    boolean deleteBookById(int id);


}
