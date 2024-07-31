package se325.example01.basictcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(0)) { //creating the server socket, which is setting up the server to run. We can specify a port to bind to, port 0 which means that when we start the server it automatically picks an unused port.

            InetAddress serverHost = InetAddress.getLocalHost();
            System.out.println("Server destination: " + serverHost.getHostAddress() + ":" + socket.getLocalPort());

            /* Repeatedly handle requests for processing. */
            while (true) { //forever continually accept client connections

                try (Socket clientConnection = socket.accept()) { //listen and block until client trys to connect to the server, then listen and give back a socket to be used to communicate to the client.
                    DataInputStream in = new DataInputStream(clientConnection.getInputStream());
                    DataOutputStream out = new DataOutputStream(clientConnection.getOutputStream());

                    /* Read numbers to multiply. */
                    int x = in.readInt();
                    int y = in.readInt();

                    /* Compute the product and send it back to the client. */
                    int result = x * y;
                    out.writeInt(result);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
