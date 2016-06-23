package socket.server.servers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import socket.Constants;
import socket.Utils;
import socket.server.base.AbstractServer;

/**
 * Login Server
 *
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
        return Constants.LOGIN_SERVER_PORT;
    }

    @Override
    public int getTimeout() {
        return Constants.TIMEOUT;
    }

    @Override
    public void init() {
        mUserMap.put("admin", "admin");
        mUserMap.put("alex", "alex");
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
        String username, password;
        boolean flag = true;
        do {
            username = in.readUTF();
            password = in.readUTF();
            Utils.println("Username: " + username);
            Utils.println("Password: " + password);

            // 6 Send message to client
            if (isUser(username, password)) {
                out.writeUTF("true");
                out.writeUTF("Is allow login!\nGoodbye!");
                flag = false;

            } else {
                out.writeUTF("false");
                out.writeUTF("Check the user info you input!\n");
            }
        } while (flag);

        // 8 Close connection
        server.close();
        Utils.println("Bye-bye!");
    }

    private boolean isUser(String name, String password) {
        return mUserMap.get(name).equals(password);
    }
}
