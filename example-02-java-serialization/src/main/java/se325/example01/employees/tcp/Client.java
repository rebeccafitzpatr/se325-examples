package se325.example01.employees.tcp;

import se325.util.Keyboard;
import se325.example01.employees.Employee;
import se325.example01.employees.EmployeeRequest;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {

            InetAddress serverAddress = InetAddress.getByName(Keyboard.prompt("Server address:")); //get the server address and the port number of the server we are connecting to.
            int serverPort = Integer.parseInt(Keyboard.prompt("Server port:"));
            String empName = Keyboard.prompt(("Enter employee name to find:")); //what employee are we trying to find???

            try (Socket socket = new Socket(serverAddress, serverPort)) {

                ObjectOutputStream out =
                        new ObjectOutputStream(socket.getOutputStream());
                //create the output stream to send objects to the server.
                // rather than the data output stream.
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); //create input stream to receive objects from the server
                //object output and input streams also have methods to
                // read/write primitive types as well as objects.
                out.writeObject(new EmployeeRequest(empName)); //write an object to our server: employee request is the name of the employee we are trying to find.

                boolean found = in.readBoolean();  //read back from the server a result which is a boolean value.

                if (found) {
                    Employee emp = (Employee) in.readObject();
                    System.out.println("Employee found!");
                    System.out.println(emp);
                }
                else {
                    System.out.println("No employee found with that name.");
                }

            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
