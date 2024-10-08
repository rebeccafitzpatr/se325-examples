package se325.example07.parolee.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * JAX-RS application subclass for the Parolee Web service. This class is
 * discovered by the JAX-RS run-time and is used to obtain a reference to the
 * ParoleeResource object that will process Web service requests.
 * <p>
 * The base URI for the Parolee Web service is:
 * <p>
 * http://<host-name>:<port>/services.
 */
@ApplicationPath("/services")
public class ParoleeApplication extends Application {

    private Set<Object> singletons = new HashSet<>();
    private Set<Class<?>> classes = new HashSet<>();

    public ParoleeApplication() {
        singletons.add(new ParoleeResource());
        classes.add(SerializationMessageBodyReaderAndWriter.class); //add our class to the list of classes that is obtained by get classes method. This lets JAXRS know that our reader write class actually exists.
    }

    @Override
    public Set<Object> getSingletons() {
        // Return a Set containing an instance of ParoleeResource that will be
        // used to process all incoming requests on Parolee resources.
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}