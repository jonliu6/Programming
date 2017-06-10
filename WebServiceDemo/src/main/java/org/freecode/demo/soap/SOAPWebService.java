package org.freecode.demo.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.freecode.demo.entity.Book;
import org.freecode.demo.entity.BookFactory;

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
