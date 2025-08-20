
import java.net.URI;
import java.util.List;
import org.freecode.demo.Book;

import org.freecode.demo.BookService;

import org.freecode.demo.BookService_Service;

public class BookServiceTester {
    private static String endpoint = "http://localhost:8080/JAXWS/BookService";
    public static void main(String[] args) {
        try {
            BookService_Service service = new BookService_Service(new URI(endpoint).toURL());
            BookService servicePort = service.getBookServicePort();

            Book newBook = new Book();
            newBook.setIsbn("978-3-16-148410-0");
            newBook.setTitle("Effective Java");
            newBook.setAuthor("Joshua Bloch");
            servicePort.addBook(newBook);

            Book newBook2 = new Book();
            newBook2.setIsbn("123-3-16-987654-1");
            newBook2.setTitle("Web Services in Action");
            newBook2.setAuthor("John Smith");
            servicePort.addBook(newBook2);

            List<Book> books = servicePort.findAllBooks();
            System.out.println(">>>Books retrieved from service:" + books.size());
            for (Book book : books) {
                System.out.println("Book ISBN: " + book.getIsbn());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("---------------------------\n");
            }

            newBook2.setTitle(newBook2.getTitle() + " - 2nd Edition");
            servicePort.updateBook(newBook2);
            System.out.println(">>>Updated book: " + servicePort.findBookByIsbn(newBook2.getIsbn()).getTitle());

            servicePort.removeBook(newBook2.getIsbn());
            System.out.println(">>>Book removed: " + newBook2.getIsbn());

            books = servicePort.findAllBooks();
            System.out.println(">>>Books retrieved from service:" + books.size());
            for (Book book : books) {
                System.out.println("Book ISBN: " + book.getIsbn());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("---------------------------\n");
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
