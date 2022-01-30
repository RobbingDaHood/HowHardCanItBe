package org.robbingdahood.httpserver;

import lombok.Builder;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Getter
public class HttpRequest {
    private final HttpMethod httpMethod;
    private final String path;
    private final String body;
    private final Map<String, String> headers = new HashMap<>();

    public HttpRequest(Socket remote) throws IOException {
        System.out.println("Connection, sending data.");
        final BufferedReader reader = new BufferedReader(
                new InputStreamReader(remote.getInputStream()));
        PrintWriter out = new PrintWriter(remote.getOutputStream());

        // Print body
        String line = reader.readLine();
        String[] firstLineWords = line.split(" ");
        this.httpMethod = HttpMethod.valueOf(firstLineWords[0]);
        this.path = firstLineWords[1];

        String body = "";
        while ((line = reader.readLine()) != null && !line.equals("")) {
            String[] maybeHeader = line.split(": ");
            if (maybeHeader.length == 2) {
                this.headers.put(maybeHeader[0], maybeHeader[1]);
            } else {
                body = line;
            }
        }
        this.body = body;
    }
}
