package socket.server.servers;

import java.io.IOException;
import java.net.Socket;

import socket.Constants;
import socket.Utils;
import socket.server.base.AbstractServer;

/**
 * Heartbeat Server
 *
 * Created by Alexander on 2016/6/23.
 */
public class HeartbeatServer extends AbstractServer {
    @Override
    public String getServerName() {
        return "HeartbeatServer";
    }

    @Override
    public int getPort() {
        return Constants.HEARTBEAT_SERVER_PORT;
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
        // 3 Get nothing from client
        Utils.println(server.getRemoteSocketAddress().toString() + " is still alive.");
        server.close();
    }
}
