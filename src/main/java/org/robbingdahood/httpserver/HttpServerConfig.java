package org.robbingdahood.httpserver;

public class HttpServerConfig {
    private static HttpServerConfig httpServerConfig;

    private static final int port = 7777;
    private static final int threadPoolSize = 10;

    private HttpServerConfig() {

    }

    public int getPort() {
        return port;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public static HttpServerConfig getHttpServerConfig() {
        if (httpServerConfig == null) httpServerConfig = new HttpServerConfig();
        return httpServerConfig;
    }
}
