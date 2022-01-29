import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PortListener {
    private HttpServerConfig httpServerConfig = HttpServerConfig.getHttpServerConfig();

    public void startup() throws IOException {
        int port = httpServerConfig.getPort();
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listen on port " + port);

        ExecutorService executorService = Executors.newFixedThreadPool(httpServerConfig.getThreadPoolSize());

        for (; ; ) {
            // wait for a connection
            Socket remote = serverSocket.accept();
            executorService.submit(new ListenerRunner(remote));
        }
    }
}
