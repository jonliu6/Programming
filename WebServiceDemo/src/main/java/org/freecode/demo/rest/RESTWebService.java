package org.freecode.demo.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.freecode.demo.entity.Book;

@Path("/demo")
/**
 * assuming the URL context is http://localhost:8080/WebServiceDemo
 * @author 
 *
 */
public class RESTWebService {
    
    private List<Book> books;
    
    public RESTWebService() {
        books = new ArrayList<Book>();
        Book aBook = new Book("12345", "Hello Book", new Date(), 123);
        books.add(aBook);
        aBook = new Book("98765", "World Book", new Date(), 456);
        books.add(aBook);
    }

    @GET
    @Path("/hello/{msg}")
    /**
     * URL: http://localhost:8080/WebServiceDemo/rest/demo/hello/msg
     * Method: GET
     * @param msg
     * @return
     */
    public Response sayHello( @PathParam("msg") String msg ) {
        String result = "Hello, " + msg;
        
        return Response.status(Status.OK).entity(result).build();
    }
    
    @GET
    @Path("/allbooks")
    @Produces("application/json")
    /**
     * URL: http://localhost:8080/WebServiceDemo/rest/demo/allbooks
     * Method: GET
     * @return
     */
    public List<Book> findAllBooksInJSON() {
        return books;
    }
    
    @GET
    @Path("/book/{isbn}")
    @Produces("application/json")
    /**
     * URL: http://localhost:8080/WebServiceDemo/rest/demo/book/isbn
     * Method: GET
     * @param isbn
     * @return
     */
    public Book findABookInJSON(@PathParam("isbn") String isbn) {
        Book returnBook = null;
        if ( books != null && books.size() > 0 ) {
            for ( Book aBook: books ) {
                if ( aBook.getIsbn() != null && aBook.getIsbn().equalsIgnoreCase(isbn) ) {
                    returnBook = aBook;
                    break;
                }
            }
        }
        return returnBook;
    }
    
    @POST
    @Path("/book/add")
    @Consumes("application/json")
    @Produces("application/json")
    /**
     * URL: http://localhost:8080/WebServiceDemo/rest/demo/book/add
     * Method: POST
     * Data: JSON string of a book
     * Note: when posting, change the header to "Content-Type: application/json"
     * @param aBook
     * @return
     */
    public List<Book> addBook(Book aBook) {
        if ( aBook != null && books != null) {
            books.add(aBook);
        }
        
        return books;
    }
    
    @PUT
    @Path("/book/update/{isbn}")
    @Consumes("application/json")
    @Produces("application/json")
    /**
     * URL: http://localhost:8080/WebServiceDemo/rest/demo/book/update/isbn
     * Method: PUT
     * Data: JSON string of a book
     * Note: when posting, change the header to "Content-Type: application/json"
     * @param isbn
     * @param aBook
     * @return
     */
    public List<Book> updateBook(@PathParam("isbn") String isbn, Book aBook) {
        Book foundBook = null;
        if (books != null) {
            for ( Book b: books ) {
                if ( b.getIsbn() != null && b.getIsbn().equalsIgnoreCase(isbn) ) {
                    foundBook = b;
                    break;
                }
            }
            if ( foundBook != null ) {
                foundBook.setTitle(aBook.getTitle());
                foundBook.setPublishedOn(aBook.getPublishedOn());
                foundBook.setPageNo(aBook.getPageNo());
            }
        }
        return books;
    }
    
    @DELETE
    @Path("/book/delete/{isbn}")
    @Produces("application/json")
    /**
     * URL: http://localhost:8080/WebServiceDemo/rest/demo/book/delete/isbn
     * Method: DELETE
     * @param isbn
     * @return
     */
    public List<Book> deleteBook(@PathParam("isbn") String isbn) {
        Book aBook = null;
        if (books != null) {
            for ( Book b: books ) {
                if ( b.getIsbn() != null && b.getIsbn().equalsIgnoreCase(isbn) ) {
                    aBook = b;
                    break;
                }
            }
            books.remove(aBook);
        }
        
        return books;
    }
}
