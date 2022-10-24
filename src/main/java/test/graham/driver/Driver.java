package test.graham.driver;

import test.graham.controllers.BookController;
import test.graham.controllers.EmployeeController;
import test.graham.controllers.TicketController;
import test.graham.entities.Employee;
import test.graham.handlers.*;
import test.graham.handlers.HelloHandler;
import test.graham.repositories.BookDAOPostgres;
import test.graham.repositories.EmployeeDAOPostgres;
import test.graham.repositories.TicketDAOPostgres;
import test.graham.services.*;
import io.javalin.Javalin;

public class Driver {

    // Because I am using dependency injection in my Service, I need to add my bookDAO as an argument when making a static object
    // If you make a static object of your Service, you can use this single instance throughout your application
    public static BookService bookService = new BookServiceImpl(new BookDAOPostgres());
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgres());
    public static TicketService ticketService = new TicketServiceImpl(new TicketDAOPostgres());
    public static Employee currentLoggedEmployee = null;
    public static void main(String[] args) {
        Javalin app = Javalin.create();

//        HelloHandler helloHandler = new HelloHandler();
////        GetBookByIdHandler getBookByIdHandler = new GetBookByIdHandler();
////        CreateBookHandler createBookHandler = new CreateBookHandler();
////        UpdateBookHandler updateBookHandler = new UpdateBookHandler();
////        DeleteBookHandler deleteBookHandler = new DeleteBookHandler();
//        BookController bookController = new BookController();
        EmployeeController employeeController = new EmployeeController();
        TicketController ticketController = new TicketController();

//
//        app.get("/hello", helloHandler);
//        app.get("/books/{id}", bookController.getBookByIdHandler);
//

        //////////////////////////////////

        //app.get("/login", employeeController.loginHandler);
//      TO DO:
//
//      CREATE LOGIN
//
        app.post("/createUser", employeeController.createUser); // submit Json post request to add user
        app.post("/login", employeeController.loginHandler);
        app.get("/employees", employeeController.getAllEmployees); // check to see all users




        /////////////////////////////////////
        //create ticket


        // view tickets
        app.get("/tickets", ticketController.getAllTickets); // check to see all tickets
        app.post("/tickets", ticketController.createTicket); // check to see all tickets
        app.get("/mytickets", ticketController.getEmployeeTickets);

        // admin view pending tickets


        // admin approve/deny tickets





//        app.get("/books", bookController.getAllBooks);
//
//        app.post("/books", bookController.createBook);
//
//        app.put("/books", bookController.updateBookHandler);
//
//        app.delete("/books/{id}", bookController.deleteBookHandler);



        app.start();
    }
}
