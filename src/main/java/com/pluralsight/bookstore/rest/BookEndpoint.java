package com.pluralsight.bookstore.rest;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.repository.BookRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/books")
public class BookEndpoint {
    @Inject
    private BookRepository bookRepository;

    public Book getBook(Long id) {
        return bookRepository.find(id);
    }

    public Book createBook(Book book) {
        return bookRepository.create(book);
    }

    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getBooks() {
        List<Book> books = bookRepository.findAll();

        if (books.size() == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(books).build();
    }

    @GET
    @Path("/count")
    public Response countBooks() {
        Long nbOfBooks = bookRepository.countAll();

        if (nbOfBooks == 0) {
            return Response.noContent().build();
        }

        return Response.ok(nbOfBooks).build();
    }
}
