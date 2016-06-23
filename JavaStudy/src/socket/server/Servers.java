package socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import socket.server.base.AbstractServer;
import socket.server.servers.GreetingServer;
import socket.server.servers.HeartbeatServer;
import socket.server.servers.LoginServer;

/**
 * Main entrance for the server control.
 *
 * Created by Alexander on 2016/6/15.
 */
public class Servers {
    public static void main(String[] args) {
        startServers();
        startServerController();
    }

    /**
     * Start all servers
     */
    private static void startServers() {
        new HeartbeatServer();
        new LoginServer();
        new GreetingServer();
    }

    /**
     * Start server controller
     */
    private static void startServerController() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input commander.");
        System.out.println("For list all running servers, input 'p'");
        System.out.println("For stop all servers and exit, input 'exit'");
        String str;
        do {
            try {
                str = bufferedReader.readLine();
                if ("p".equals(str)) {
                    AbstractServer.printAllServers();
                }
            } catch (IOException e) {
                str = "";
            }
        } while (!"exit".equals(str.toLowerCase()));
        System.out.println("Stopping all servers!");
        AbstractServer.stopAllServers();
        System.out.println("Servers are stopped!");
        System.exit(0);
    }
}