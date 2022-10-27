package test.graham.repositories;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.graham.entities.Employee;
import test.graham.entities.Ticket;
import test.graham.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static test.graham.entities.Status.DENIED;

class TicketDAOPostgresTest {
    static TicketDAO ticketDAO  = new TicketDAOPostgres();

//    @BeforeAll
//    static void setup() throws SQLException {
//        try(Connection connection = ConnectionFactory.getConnection()) {
//            String sql = "drop table ticket2 if exists";
//            Statement statement = connection.createStatement();
//            statement.execute(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try(Connection connection = ConnectionFactory.getConnection()){
//            String sql = "create table ticket2(\n" +
//                    "id serial primary key not null," +
//                    "description varchar (100) not null," +
//                    "createdBy varchar (40) not null," +
//
//        } catch (SQLException e) {
//        throw new RuntimeException(e);
//    }
//    }

    //    create table tickets2 (
//            id serial primary key not null,
//            description varchar (100) not null,
//    createdBy varchar (40) not null,
//    approvedBy varchar (40) default 'not approved',
//    amount int not null default 0,
//    status varchar (40) default 'PENDING',
//    isChanged boolean default false
//
//            );
    @Test
    void create_ticket_test() {
        Ticket newTicket = new Ticket(0, "this is a test", "java Tests", "noone", 0, DENIED, true);
        Ticket savedTicket = ticketDAO.createTicket(newTicket);
        Assertions.assertTrue(newTicket.getChanged());
    }

    @Test
    void get_ticket_by_id_test() {
        Ticket newTicket = new Ticket(0, "this is a test", "java Tests", "noone", 0, DENIED, true);
        Ticket savedTicket = ticketDAO.getTicketById(newTicket.getId());
        Assertions.assertEquals(newTicket.getId(), savedTicket.getId());
    }

    @Test
    void update_ticket_test() {
        List<Ticket> newList = ticketDAO.getAllTickets();
        Assertions.assertTrue(newList.size()>0);
    }

    @Test
    void get_all_tickets_test() {
        List<Ticket> newList = ticketDAO.getAllTickets();
        Assertions.assertTrue(newList.size()>0);
    }

    @Test
    void get_all_pending_tickets_test() {
        List<Ticket> newList = ticketDAO.getAllPendingTickets();
        Assertions.assertTrue(newList.size()>0);
    }

    @Test
    void get_ticket_by_email_test() {
        List<Ticket> newList  = ticketDAO.getTicketByEmail("test@mail.com");
        Assertions.assertTrue(newList.size()>0);
    }

//    @AfterAll
//    static void tearDown(){
//        try(Connection connection = ConnectionFactory.getConnection()) {
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}