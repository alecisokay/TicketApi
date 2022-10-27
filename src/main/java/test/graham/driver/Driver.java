package test.graham.driver;

import test.graham.controllers.EmployeeController;
import test.graham.controllers.TicketController;
import test.graham.entities.Employee;
import test.graham.repositories.BookDAOPostgres;
import test.graham.repositories.EmployeeDAOPostgres;
import test.graham.repositories.TicketDAOPostgres;
import test.graham.services.*;
import io.javalin.Javalin;

public class Driver {
    public static BookService bookService = new BookServiceImpl(new BookDAOPostgres());
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgres());
    public static TicketService ticketService = new TicketServiceImpl(new TicketDAOPostgres());
    public static Employee currentLoggedEmployee = null;
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        // controllers
        EmployeeController employeeController = new EmployeeController();
        TicketController ticketController = new TicketController();

        app.post("/createUser", employeeController.createUser); // submit Json post request to add user
        app.post("/login", employeeController.loginHandler);
        app.get("/logout", employeeController.logout);
        app.get("/employees", employeeController.getAllEmployees); // check to see all users




        /////////////////////////////////////
        //create ticket
        app.post("/tickets", ticketController.createTicket); // creates to see all tickets


        // view tickets
        app.get("/tickets", ticketController.getAllTickets); // check to see all tickets
        app.get("/mytickets", ticketController.getEmployeeTickets);



        // admin view pending tickets
        app.get("/pending-tickets", ticketController.getAllPendingTickets);
        // admin approve/deny tickets
        app.get("/update-ticket", ticketController.updateTicket);


        app.start();
    }
}
