
import java.net.URI;
import org.freecode.demo.SayHello;
import org.freecode.demo.SayingHello;
import org.freecode.demo.SayingHello_Service;

public class HelloServiceTester {
    private static final String endpoint = "http://localhost:8080/JAXWS/SayingHello";

    public static void main(String[] args) {
        try {
            SayingHello_Service service = new SayingHello_Service(new URI(endpoint).toURL());
            SayingHello servicePort = service.getSayingHelloPort();
            SayHello sayHello = new SayHello();
            sayHello.setAName("John Doe");
            String response = servicePort.sayHello(sayHello.getAName());

            System.out.println("Response from service: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
