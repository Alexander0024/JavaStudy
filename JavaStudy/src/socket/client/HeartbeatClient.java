package socket.client;

import java.net.Socket;

import socket.Constants;
import socket.Utils;

/**
 * Heartbeat client
 *
 * Created by Alexander on 2016/6/23.
 */
public class HeartbeatClient {
    private static int times = 0;

    public static void main(String[] args) {
        while (true) {
            try {
                // 1 Connect Server
                Socket client = new Socket(Constants.LOCAL_SERVER_LOCATION, Constants.HEARTBEAT_SERVER_PORT);
                Utils.println("Connected to " + client.getRemoteSocketAddress() + ", still alive!");
                times = 0;
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Utils.println("Sleep interrupted!");
            } catch (Exception e) {
                if (times < 3) {
                    times ++;
                    Utils.println("Connect to server error in time " + times);
                } else {
                    Utils.println("OFFLINE!!!");
                    break;
                }
            }
        }
    }
}
