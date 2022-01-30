package org.robbingdahood.httpserver;

import lombok.Builder;
import lombok.Getter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
public class GeneralHttpResponse implements HttpResponse {
    private int statusCode;
    private String statusMessage;
    @Builder.Default
    private String body = "";
    @Builder.Default
    private Map<String, String> headers = new HashMap<>();

    @Override
    public void sendResponse(Socket socket) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        System.out.println("Sending response");
        out.println("HTTP/1.0 " + statusCode + " " + statusMessage);

        headers.forEach((key, value) -> out.println(key + ": " + value));

        out.println("Server: Bot");
        // this blank line signals the end of the headers
        out.println("");
        // Send the HTML page
        out.println(body);
        out.flush();
        socket.close();
    }
}
