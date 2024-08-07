package se325.example04.server;

import javax.jws.WebService;

@WebService(endpointInterface = "se325.example04.server.HelloWorld")
public class HelloWorldImpl implements HelloWorld { //we create this class to
    // implement the interface, and also annotate with the web service
    // annothion. this time, we specify the interface which represents the
    // soap service in the endpoint interface property.

    //now that we have this service implementation we can host this
    // webservice witin a servelt container such as tomcat or create a
    // standalone server to host the service directly using jax ws.

    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello " + name + ", from your friendly JAX-WS SOAP Service!";
    }
}
