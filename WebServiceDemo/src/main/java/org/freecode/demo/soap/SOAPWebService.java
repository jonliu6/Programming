package org.freecode.demo.soap;

import java.util.List;

import org.freecode.demo.entity.Book;
import org.freecode.demo.entity.BookFactory;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
// WSDL URL: http://localhost:8080/WebServiceDemo/SOAPWebService?WSDL
public class SOAPWebService {
    private BookFactory getBookFactory() {
        return new BookFactory();
    }

    @WebMethod
    public String sayHelloTo(String aName) {
        return "Hello " + aName;
    }
    
    @WebMethod
    public List<Book> findAllBooks() {
        return getBookFactory().initializeBooks();
    }
}
