package se325.asyncchat.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se325.asyncchat.domain.Message;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Path("/chat")
public class ChatResource {
	//our resource class has 2 web methods, one for subscribing to messages and another to post new messages. Message is a JSON object.

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatResource.class);

    private final List<AsyncResponse> subs = new Vector<>();

    /**
     * Subscribes to be notified when the next message is received.
     */
    @GET
    @Path("/sub")
    @Produces(MediaType.APPLICATION_JSON)
    public void subscribeToMessage(@Suspended AsyncResponse sub) {
        synchronized (subs) {
            subs.add(sub); 
        }
    }
    
    //to subscirbe to a message, the user sends a get request to the path /sub. They don't send any information, however there is the suspended async response which means that it is going to be an asyncronous methods, whereby we can eventually respond back to the clinet. 
    //THe only thing that we do immedidately is add the client to our list of subs.



	//later when the user wants to post a message, they send a poist request to the path.
    /**
     * POSTs a message, which will be pushed back to all subscribers.
     *
     * @param message the message to POST.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void postMessage(Message message) {

        LOGGER.warn(message.toString()); //print our message to server.

        List<AsyncResponse> currentSubs;
        synchronized (subs) {
            currentSubs = new ArrayList<>(subs);
        }

        currentSubs.parallelStream().forEach(sub -> { //grab all the current subs, and for each one we call their resume methods. Send the one message that was sent, send it back to all clients.
            sub.resume(message);
            synchronized (subs) {
                subs.remove(sub); //each sub can only be responded to once. so we remove it after sending the response.
            }
        });

    }

}
