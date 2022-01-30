package org.robbingdahood.httpserver;

import java.io.IOException;
import java.net.Socket;

public interface HttpResponse {
    void sendResponse(Socket socket) throws IOException;

    String getBody();

    String getStatusMessage();

    java.util.Map<String, String> getHeaders();

    int getStatusCode();
}
