package se325.example05.helloworld.server;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/services") //annotation, so when a client sends HTTP request to the server containing /services, that request will be sent to this application and process by on of the resources.
public class HelloApplication extends Application {

    private final Set<Object> singletons = new HashSet<>();

    public HelloApplication() {
        singletons.add(new GreetingsResource());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    } //returns a set of all of our resrouces
}
