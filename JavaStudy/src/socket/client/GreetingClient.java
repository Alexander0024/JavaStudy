package socket.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import socket.Constants;
import socket.Utils;

/**
 * Created by Alexander on 2016/6/15.
 */
public class GreetingClient {
    public static void main(String[] args) {
        try {
            // 1 Connect Server
            Utils.println("Connecting to " + Constants.LOCAL_SERVER_LOCATION + " on port " +
                    Constants.GREETING_SERVER_PORT);
            Socket client = new Socket(Constants.LOCAL_SERVER_LOCATION, Constants
                    .GREETING_SERVER_PORT);
            Utils.println("Connected to " + client.getRemoteSocketAddress());

            // 2 Send message to Server
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            out.writeUTF("Hello from " + client.getLocalSocketAddress());

            // 3 Get message from Server
            DataInputStream in = new DataInputStream(client.getInputStream());
            Utils.println("Server says " + in.readUTF());

            // 4 Close connection
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
