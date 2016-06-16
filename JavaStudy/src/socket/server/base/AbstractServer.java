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
    private static List<ServerBean> servers = new ArrayList<ServerBean>();

    private boolean isRunning = false;

    @Override
    public void run() {
        startServer();
    }

    protected void startServer() {
        isRunning = true;
        addServer();
        startSocket();
    }

    protected void stopServer() {
        isRunning = false;
        removeServer();
    }

    public static void stopAllServers() {
        for (int i = 0; i < servers.size(); i++) {
            servers.get(i).getServer().stopServer();
        }
    }

    public static void printAllServers() {
        System.out.println("========================================================================");
        System.out.printf("%20s\t%6s\t%10s\t%20s\n","Server Name","Port","Timeout","StartTime");
        for (ServerBean serverBean : servers) {
            serverBean.toString();
        }
        System.out.println("========================================================================");
    }

    private void startSocket() {
        int timeout = getTimeout();
        try {
            ServerSocket server = new ServerSocket(getPort());
            if (0 != timeout) {
                server.setSoTimeout(timeout);
            }
            // 1 Init Server
            Utils.println("Start server " + getServerName() + " on port " + server.getLocalPort() + " ...");
            while (isRunning) {
                Socket socket = server.accept();
                new Thread(new Task(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addServer() {
        ServerBean serverBean = new ServerBean();
        serverBean.setServerName(getServerName());
        serverBean.setServerPort(getPort());
        serverBean.setTimeout(getTimeout());
        serverBean.setStartTime(new Date().getTime());
        serverBean.setServer(this);
        servers.add(serverBean);
    }

    private void removeServer() {
        for (int i = 0; i < servers.size(); i++) {
            if (getPort() == servers.get(i).getServerPort()) {
                servers.remove(i);
                break;
            }
        }
    }

    public abstract String getServerName();

    public abstract int getPort();

    public abstract int getTimeout();

    public abstract void running(Socket server) throws IOException;

    class Task implements Runnable {
        Socket server;

        public Task(Socket server) {
            this.server = server;
        }

        public void run() {
            try {
                running(server);
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
