package test.graham.daotests;

import test.graham.entities.Book;
import test.graham.repositories.BookDAO;
import test.graham.repositories.BookDAOLocal;
import test.graham.repositories.BookDAOPostgres;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDAOTests {

    static BookDAO bookDAO = new BookDAOPostgres();

    // this is kabob case, tests are written in kabob case
    @Test
    @Order(1)
    void create_book_test(){
        Book newBook = new Book(0,"harry potter","jk bro",0);
        Book savedBook = bookDAO.createBook(newBook);
        Assertions.assertNotEquals(0,savedBook.getId());
    }
    @Test
    @Order(2)
    void get_book_by_id_test(){
        Book gottenBook = bookDAO.getBookById(0);
        Assertions.assertEquals("harry potter",gottenBook.getTitle());
    }
    @Test
    @Order(3)
    void update_book_test(){
        //When testing update, you should either get the book and use its values or create a completely new book and use those values
        Book book = bookDAO.getBookById(1);
        //You can think of update more of a full replacement/swap and less of specific values being update
        Book bookV2 = new Book(book.getId(),book.getTitle(),"jk bro",book.getReturnDate());
        bookDAO.updateBook(bookV2);
        Book updatedBook = bookDAO.getBookById(bookV2.getId());
        Assertions.assertEquals("jk bro",updatedBook.getAuthor());
    }
    @Test
    @Order(4)
    void delete_book_by_id_test(){
        boolean result = bookDAO.deleteBookById(0);
        Assertions.assertTrue(result);
    }

}
