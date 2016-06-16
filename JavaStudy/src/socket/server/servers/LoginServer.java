package socket.server.servers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import socket.Utils;
import socket.server.base.AbstractServer;

/**
 * Login Server
 * Created by Alexander on 2016/6/16.
 */
public class LoginServer extends AbstractServer {
    private Map<String, String> mUserMap = new HashMap<String, String>();

    @Override
    public String getServerName() {
        return "LoginServer";
    }

    @Override
    public int getPort() {
        return 6666;
    }

    @Override
    public int getTimeout() {
        return 0;
    }

    @Override
    public void running(Socket server) throws IOException {
        // 2 Waiting for client
        Utils.println("Get connected to " + server.getRemoteSocketAddress());

        // Send Information to client
        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        out.writeUTF("Please input username and password for login!\n");

        // 5 Get UTF message from client
        DataInputStream in = new DataInputStream(server.getInputStream());
        String username = in.readUTF();
        String password = in.readUTF();
        Utils.println("Username: " + username);
        Utils.println("Password: " + password);

        // 6 Send message to client
        out.writeUTF("Name = " + username + "; Password = " + password + "\nGoodbye!");

        // 8 Close connection
        server.close();
        Utils.println("Bye-bye!");
    }
}
