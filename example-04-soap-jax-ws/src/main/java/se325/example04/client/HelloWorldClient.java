package se325.example04.client;

import se325.example04.server.HelloWorld;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class HelloWorldClient {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:10000/ws/hello");

        QName qname = new QName("http://server.example04.se325/",
                "HelloWorldImplService");
        Service service = Service.create(url, qname); //this creates an
        // object that allows us to interact with the web service.
        HelloWorld helloService = service.getPort(HelloWorld.class);
        //reference to the soap port corressponding to the hellow world
        // service. HelloWorld.class is the interface type, which jax ws will
        // create proxy implementing this interface which will communicate
        // with the soap service.

        String name = Keyboard.prompt("Enter your name:");
        String response = helloService.getHelloWorldAsString(name);//Therefore  within the our java code, we can
        // invoke service methods just as if they were regular java methods.

        System.out.println("Server says: " + response);

    }
}
