package org.robbingdahood.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final HttpServerConfig httpServerConfig = HttpServerConfig.getHttpServerConfig();
    private boolean shutdown = false;

    public void startup() throws IOException {
        int port = httpServerConfig.getPort();
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listen on port " + port);
        ExecutorService executorService = Executors.newFixedThreadPool(httpServerConfig.getThreadPoolSize());

        Executors.newSingleThreadExecutor().submit(() -> {
            while (!shutdown) {
                // wait for a connection
                Socket remote = null;
                try {
                    remote = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                executorService.submit(new ListenerRunner(remote));
            }
        });
    }

    public void shutdown() {
        shutdown = true;
    }
}
