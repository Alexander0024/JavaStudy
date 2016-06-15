package socket.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import socket.Utils;

/**
 * Created by Alexander on 2016/6/15.
 */
public class GreetingClient {
    public static void main(String[] args) {
        String serverName;
        int port;
        if (args.length == 0) {
            serverName = "127.0.0.1";
            port = 6768;
        } else {
            serverName = args[0];
            port = Integer.parseInt(args[1]);
        }
        try {
            // 3 Connect Server
            Utils.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);
            Utils.println("Connected to " + client.getRemoteSocketAddress());

            // 4 Send message to Server
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            out.writeUTF("Hello from " + client.getLocalSocketAddress());

            // 7 Get message from Server
            DataInputStream in = new DataInputStream(client.getInputStream());
            Utils.println("Server says " + in.readUTF());

            // 8 Close connection
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
