package socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import socket.server.base.AbstractServer;
import socket.server.servers.GreetingServer;

/**
 * Created by Alexander on 2016/6/15.
 */
public class Servers {
    public static void main(String[] args) {
        new GreetingServer().start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input command, print servers with 'p', exit with 'exit'");
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