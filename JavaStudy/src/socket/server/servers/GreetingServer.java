package socket.server.servers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import socket.Utils;
import socket.server.base.AbstractServer;

/**
 * Demo greeting server.
 *
 * Created by Alexander on 2016/6/15.
 */
public class GreetingServer extends AbstractServer {
    @Override
    public String getServerName() {
        return "GreetingServer";
    }

    @Override
    public int getPort() {
        return 6769;
    }

    @Override
    public int getTimeout() {
        return 0;
    }

    @Override
    public void init() {

    }

    @Override
    public void running(Socket server) throws IOException {
        // 2 Waiting for client
        Utils.println("Get connected to " + server.getRemoteSocketAddress());

        // 5 Get UTF message from client
        DataInputStream in = new DataInputStream(server.getInputStream());
        Utils.println(in.readUTF());

        // 6 Send message to client
        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");

        // 8 Close connection
        server.close();
        Utils.println("Bye-bye!");
    }
}
