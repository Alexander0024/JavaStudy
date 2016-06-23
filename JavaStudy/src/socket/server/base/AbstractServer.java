package socket.server.base;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import socket.Utils;
import socket.server.bean.ServerBean;

/**
 * Abstract Server for Server Model
 *
 * Created by Alexander on 2016/6/15.
 */
public abstract class AbstractServer extends Thread {

    /**
     * Server List
     */
    private static List<ServerBean> servers = new ArrayList<ServerBean>();

    /**
     * The flag for control the running process.
     */
    private boolean isRunning = false;

    /**
     * start server when init
     */
    public AbstractServer() {
        start();
    }

    /**
     * Thread Run
     */
    @Override
    public void run() {
        isRunning = true;
        addServer();
        startSocket();
    }

    /**
     * Stop current server
     */
    protected void stopServer() {
        isRunning = false;
        removeServer();
    }

    /**
     * Stop all running servers
     */
    public static void stopAllServers() {
        for (int i = 0; i < servers.size(); i++) {
            servers.get(i).getServer().stopServer();
        }
    }

    /**
     * List all running servers.
     */
    public static void printAllServers() {
        try {
            Utils.println("Got " + servers.size() + " servers running!");
            System.out.println
                    ("========================================================================");
            System.out.printf("%20s\t%6s\t%10s\t%20s\n", "Server Name", "Port", "Timeout", "StartTime");
            for (int i = 0; i < servers.size(); i++) {
                servers.get(i).printInfo();
            }
            System.out.println
                    ("========================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Start server socket.
     */
    private void startSocket() {
        int timeout = getTimeout();
        try {
            ServerSocket server = new ServerSocket(getPort());
            if (0 != timeout) {
                server.setSoTimeout(timeout);
            }
            // 1 Init Server
            Utils.println("Start server " + getServerName() + " on port " + server.getLocalPort()
                    + " ...");
            while (isRunning) {
                init();
                Socket socket = server.accept();
                Utils.println("Get connected from " + socket.getRemoteSocketAddress());
                running(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Record running server information.
     */
    private void addServer() {
        ServerBean serverBean = new ServerBean();
        serverBean.setServerName(getServerName());
        serverBean.setServerPort(getPort());
        serverBean.setTimeout(getTimeout());
        serverBean.setStartTime(new Date().getTime());
        serverBean.setServer(this);
        servers.add(serverBean);
        Utils.println("Add server " + getServerName() + " .");
    }

    /**
     * Remove from server list when stopping server.
     */
    private void removeServer() {
        for (int i = 0; i < servers.size(); i++) {
            if (getPort() == servers.get(i).getServerPort()) {
                servers.remove(i);
                Utils.println("Stop " + getServerName() + " server.");
                break;
            }
        }
    }

    /**
     * Get server name
     *
     * @return Server Name
     */
    public abstract String getServerName();

    /**
     * Get server port
     *
     * @return Server Port
     */
    public abstract int getPort();

    /**
     * Get server timeout setting.
     *
     * @return Server Timeout.
     */
    public abstract int getTimeout();

    /**
     * Init server attributes.
     */
    public abstract void init();

    /**
     * Start running server in a new Thread.
     *
     * @param server Server
     * @throws IOException throws IOException when meet the running exception.
     */
    public abstract void running(Socket server) throws IOException;
}
