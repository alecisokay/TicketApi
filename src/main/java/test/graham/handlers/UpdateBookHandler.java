package test.graham.handlers;

import com.google.gson.Gson;
import test.graham.driver.Driver;
import test.graham.entities.Book;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateBookHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String bookJSON = ctx.body();
        Gson gson = new Gson();
        Book book = gson.fromJson(bookJSON, Book.class);
        Book updateBook = Driver.bookService.updateBook(book);
        String json = gson.toJson(updateBook);
        ctx.result(json);
    }
}
