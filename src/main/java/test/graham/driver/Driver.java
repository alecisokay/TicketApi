package test.graham.driver;

import test.graham.controllers.BookController;
import test.graham.controllers.EmployeeController;
import test.graham.handlers.*;
import test.graham.handlers.HelloHandler;
import test.graham.repositories.BookDAOPostgres;
import test.graham.repositories.EmployeeDAOPostgres;
import test.graham.services.BookService;
import test.graham.services.BookServiceImpl;
import io.javalin.Javalin;
import test.graham.services.EmployeeService;
import test.graham.services.EmployeeServiceImpl;

public class Driver {

    // Because I am using dependency injection in my Service, I need to add my bookDAO as an argument when making a static object
    // If you make a static object of your Service, you can use this single instance throughout your application
    public static BookService bookService = new BookServiceImpl(new BookDAOPostgres());
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgres());
    //public static currentLoggedEmployee;
    public static void main(String[] args) {
        Javalin app = Javalin.create();

        HelloHandler helloHandler = new HelloHandler();
//        GetBookByIdHandler getBookByIdHandler = new GetBookByIdHandler();
//        CreateBookHandler createBookHandler = new CreateBookHandler();
//        UpdateBookHandler updateBookHandler = new UpdateBookHandler();
//        DeleteBookHandler deleteBookHandler = new DeleteBookHandler();
        BookController bookController = new BookController();

        EmployeeController employeeController = new EmployeeController();


        app.get("/hello", helloHandler);
        app.get("/books/{id}", bookController.getBookByIdHandler);


        //////////////////////////////////
        //app.post("/signup", signupHandler);

        //app.get("/login", employeeController.loginHandler);
        app.post("/createUser", employeeController.createUser);
        app.get("/employees", employeeController.getAllEmployees);




        /////////////////////////////////////




        app.get("/books", bookController.getAllBooks);

        app.post("/books", bookController.createBook);

        app.put("/books", bookController.updateBookHandler);

        app.delete("/books/{id}", bookController.deleteBookHandler);



        app.start();
    }
}
