package socket.server.bean;

import java.util.Calendar;

import socket.server.base.AbstractServer;

/**
 * Entity for server information.
 *
 * Created by Alexander on 2016/6/15.
 */
public class ServerBean {
    /**
     * Server Name
     */
    private String serverName;
    /**
     * Server Port
     */
    private int serverPort;
    /**
     * Server timeout
     */
    private int timeout;
    /**
     * Server start timestamp
     */
    private long startTime;
    /**
     * Server reference
     */
    private AbstractServer server;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public AbstractServer getServer() {
        return server;
    }

    public void setServer(AbstractServer server) {
        this.server = server;
    }

    /**
     * Print server info.
     */
    public void printInfo() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getStartTime());
        System.out.printf("%20s\t%6d\t%10d\t%20s\n", getServerName(), getServerPort(), getTimeout
                (), c.getTime().toString());
    }
}
