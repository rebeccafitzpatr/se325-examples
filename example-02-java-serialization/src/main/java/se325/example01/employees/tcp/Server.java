package se325.example01.employees.tcp;

import se325.example01.employees.Employee;
import se325.example01.employees.EmployeeRequest;
import se325.example01.employees.Manager;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Server {

    public static void main(String[] args) {

        Manager mgr = new Manager("David", "8653899");
        Employee e1 = new Employee("Tim", "2368571", mgr);
        Employee e2 = new Employee("Gareth", "0911558", mgr);//creating the object graph.
        List<Employee> employees = Arrays.asList(mgr, e1, e2); //this is the list of employess we are searching through

        try (ServerSocket socket = new ServerSocket(0)) {

            InetAddress serverHost = InetAddress.getLocalHost();
            System.out.println("Server destination: " + serverHost.getHostAddress() + ":" + socket.getLocalPort());

            /* Repeatedly handle requests for processing. */
            while (true) {

                try (Socket clientConnection = socket.accept()) { //the first thing we do when we accept request from client, we will get the input and output streams as object input streams and object output streams..
                    System.out.println("Client connected!");

                    ObjectInputStream in = new ObjectInputStream(clientConnection.getInputStream()); //make sure to set up input stream first and output stream second to complement the client setup
                    ObjectOutputStream out = new ObjectOutputStream(clientConnection.getOutputStream());

                    System.out.println("Streams obtained!");

                    // Read request
                    EmployeeRequest request = (EmployeeRequest) in.readObject();

                    System.out.println("Client requested for employee named '" + request.getName() + "'");

                    // Find matching employee
                    Optional<Employee> match = employees.stream().filter(e -> e.getName().equalsIgnoreCase(request.getName())).findFirst();
                    //when we stream the list employees,we can perform different filters on it to decide what will get kept.
                    // Send it back if present
                    if (match.isPresent()) {
                        System.out.println("Found!");
                        out.writeBoolean(true);
                        out.writeObject(match.get());
                    }

                    // Notify not-found otherwise
                    else {
                        System.out.println("Not found!");
                        out.writeBoolean(false);
                    }

                    out.flush(); //take any information that is lingering the input output stream and ensure that it is written to the appropriate socket before it is closed. Often when we do not include this line, some information is not written and client gets an error.

                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
