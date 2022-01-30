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
        registerPathObservers();

        int port = httpServerConfig.getPort();
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listen on port " + port);
        ExecutorService executorService = Executors.newFixedThreadPool(httpServerConfig.getThreadPoolSize());

        Executors.newSingleThreadExecutor().submit(() -> {
            while (!shutdown) {
                // wait for a connection
                try {
                    final Socket remote = serverSocket.accept();
                    executorService.submit(() -> {
                        try {
                            PathListener.triggerObserver(new HttpRequest(remote))
                                    .sendResponse(remote);
                        } catch (IOException e) { //TODO hanlde better error handling
                            e.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void shutdown() {
        shutdown = true;
    }

    private void registerPathObservers() {
        PathListener.addObserver(new ListenerRunner());
    }
}
