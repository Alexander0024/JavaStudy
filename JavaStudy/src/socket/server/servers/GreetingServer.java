package socket.server.servers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import socket.Constants;
import socket.Utils;
import socket.server.base.AbstractServer;

/**
 * Greeting server.
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
        return Constants.GREETING_SERVER_PORT;
    }

    @Override
    public int getTimeout() {
        return Constants.TIMEOUT;
    }

    @Override
    public void init() {

    }

    @Override
    public void running(Socket server) throws IOException {
        // 3 Get UTF message from client
        DataInputStream in = new DataInputStream(server.getInputStream());
        Utils.println(in.readUTF());

        // 4 Send message to client
        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");

        // 5 Close connection
        server.close();
        Utils.println("Bye-bye!");
    }
}
