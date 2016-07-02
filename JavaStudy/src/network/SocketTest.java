package network;

import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 网络链接情况测试
 *
 * Created by Alexander on 16/7/2.
 */
public class SocketTest {
    public static void main(String[] args) throws Exception {
        String host;
        if (args.length != 0) {
            host = args[0];
        } else {
            host = "www.baidu.com";
        }
        Socket socket = new Socket(host, 13);
        InputStream in = socket.getInputStream();
        Scanner scanner = new Scanner(in);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}
