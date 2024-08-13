package se325.example05.helloworld.server;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/greetings") //specifies the next part of the url that must be entered to order to access this resource.
public class GreetingsResource {

    @GET //this ethod will be accepting http get requests.
    @Path("hello") //this method will accept if the next part of the url after greeting is hello
    @Produces(MediaType.APPLICATION_JSON) //this method will return json data back to the client. It is used to match with the accept header
    public Response sayHello(@DefaultValue("Human") @QueryParam("name") String name) { //this method has a few annotions, accepts one parameter (string name). If the user entered a query parameter called name, then the String will be populated with the value that they entered at runtime. If they did not enter such a query parameter, then the string will be populated with the default value.
        //queryParam annotation ?name=Bob, if the query parameter does not exist, the default value will be Human.

        String json = "{ \"greeting\": \"Hello, " + name + "!\" }";
        return Response.ok(json).build(); //saying response.ok starts the response builder with status 200, allows us to build response and send it back

        //if we want to run application in tomcat, add a new tomcat server
    }

}