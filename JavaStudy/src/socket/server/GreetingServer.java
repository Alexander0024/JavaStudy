package socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import socket.Utils;

/**
 * Created by Alexander on 2016/6/15.
 */
public class GreetingServer extends Thread {
    private ServerSocket serverSocket;

    public GreetingServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        while (true) {
            try {
                // 1 Init Server
                Utils.println("Waiting for client on port " + serverSocket.getLocalPort() + " ...");

                // 2 Waiting for client
                Socket server = serverSocket.accept();
                Utils.println("Get connected to " + server.getRemoteSocketAddress());

                // 5 Get UTF message from client
                DataInputStream in = new DataInputStream(server.getInputStream());
                Utils.println(in.readUTF());

                // 6 Send message to client
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");

                // 8 Close connection
                server.close();
            } catch (SocketTimeoutException e) {
                Utils.println("Socket time out!");
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        int port;
        if (args.length == 0) {
            port = 6768;
        } else {
            port = Integer.parseInt(args[0]);
        }
        try {
            Thread thread = new GreetingServer(port);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
