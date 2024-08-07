package se325.example04.server;

import javax.xml.ws.Endpoint;

public class HelloWorldPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:10000/ws/hello",
                new HelloWorldImpl()); //this method is provided by jax ws
        // and lets us specify a url where the service description should be
        // hosted.
        System.out.println("WSDL published: http://localhost:10000/ws/hello"); //via link, the service will return an html page with human readable info about the service. A summary of the info conatined in the wsdl.


    }

}
