package se325.example04.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService // annotate the interface with this annotation and soap binding
// annotation to describe the kind of soap service, RPC style(most widely
// used)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWorld { //to define a service with jaxws we first
    // create a java interface. this interface describes the methods that the
    // service should posess.

    @WebMethod //tag each interface method that should form part of the
        // service, with the web method annotation.
    String getHelloWorldAsString(String name);

}
