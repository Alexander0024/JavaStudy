package socket.server.base;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import socket.Utils;
import socket.server.bean.ServerBean;

/**
 * Abstract Server for Server Model
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
     * Thread Run
     */
    @Override
    public void run() {
        startServer();
    }

    /**
     * Start server
     */
    protected void startServer() {
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
        System.out.println
                ("========================================================================");
        System.out.printf("%20s\t%6s\t%10s\t%20s\n", "Server Name", "Port", "Timeout", "StartTime");
        for (ServerBean serverBean : servers) {
            serverBean.toString();
        }
        System.out.println
                ("========================================================================");
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
                Socket socket = server.accept();
                new Thread(new Task(socket)).start();
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
    }

    /**
     * Remove from server list when stopping server.
     */
    private void removeServer() {
        for (int i = 0; i < servers.size(); i++) {
            if (getPort() == servers.get(i).getServerPort()) {
                servers.remove(i);
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

    class Task implements Runnable {
        Socket server;

        public Task(Socket server) {
            this.server = server;
        }

        public void run() {
            try {
                init();
                running(server);
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
