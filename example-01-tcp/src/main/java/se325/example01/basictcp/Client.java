package se325.example01.basictcp;

import se325.util.Keyboard;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {

            InetAddress serverAddress = InetAddress.getByName(Keyboard.prompt("Server address:"));  // prompting the user to enter the address and port
            int serverPort = Integer.parseInt(Keyboard.prompt("Server port:"));
            int num1 = Integer.parseInt(Keyboard.prompt("Enter first number:"));        //we send these numbers to the server and do the calculation
            int num2 = Integer.parseInt(Keyboard.prompt("Enter second number:"));

            try (Socket socket = new Socket(serverAddress, serverPort)) {  //try with resources allows us to create things that must be closed when it is finished, here wecreate the socket object to themake the connection to the server

                DataInputStream in = new DataInputStream(socket.getInputStream()); // establishing the streams that we want to use to communicate to the server, getInputStream get OutputStream allow us to communicate with the server. These allow us to send and reciveve data other than raw bytes.
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                out.writeInt(num1);
                out.writeInt(num2); //send the integers to the server

                int product = in.readInt(); // read integer from the server
                System.out.println("Received: " + product);

            } //automatically closes connection at the end of the try brace.

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
