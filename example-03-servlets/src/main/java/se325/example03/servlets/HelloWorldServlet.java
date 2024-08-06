package se325.example03.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorldServlet extends HttpServlet { //lets take a look at a
    // basic http servlet

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException { //overriding one method, this method is called whenever we recieve a get request from the client.
        //two method arguments, request object contains information from the
        // client, response is where we send the information that we want to
        // send back to the client.

        resp.setContentType("text/plain"); //this methods lets us tell the
        // client what type of information we ar sending back. (Plain test)
        //mime type. common mime types: text/plain, application/json,
        // application/octet stream(for binary data)


        //another use full method is set status, if we do not call it, it
        // automatically sends defaul 200 code.

        PrintWriter out = resp.getWriter(); //this is a print writer, same
        // semantics as sys.out, so we can use prinln methods.

        //instad of getWriter(), we could use  getOutputStream to send binary
        // data instead of text based data.
        out.println("Hello, world!");

        //as well as defining a servelts, we need to let the servelt
        // contatiner know that it exisits, and that it should be loaded as a
        // servlet. There are several ways to do this.

        //original way is to use web.xml config file
        //here we define servlet section and servlet mapping

    }
}