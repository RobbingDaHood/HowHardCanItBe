package org.robbingdahood.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public static HttpRequest parse(Socket remote) throws IOException {
        HttpRequest.HttpRequestBuilder requestBuilder = HttpRequest.builder();

        System.out.println("Connection, sending data.");
        final BufferedReader reader = new BufferedReader(
                new InputStreamReader(remote.getInputStream()));
        PrintWriter out = new PrintWriter(remote.getOutputStream());

        // Print body
        String line = reader.readLine();
        String[] firstLineWords = line.split(" ");
        requestBuilder.httpMethod(HttpMethod.valueOf(firstLineWords[0]));
        requestBuilder.path(firstLineWords[1]);

        Map<String, String> headers = new HashMap<>();
        while ((line = reader.readLine()) != null && !line.equals("")) {
            String[] maybeHeader = line.split(": ");
            if (maybeHeader.length == 2) {
                headers.put(maybeHeader[0], maybeHeader[1]);
            } else {
                requestBuilder.body(line);
            }
        }

        return requestBuilder
                .headers(headers)
                .build();
    }
}
