import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PortListener {
    public void startup() throws IOException {
        int port = 7777;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listen on port " + port);


        for (; ; ) {
            // wait for a connection
            Socket remote = serverSocket.accept();
            new ListenerRunner(remote).start();
        }
    }
}
