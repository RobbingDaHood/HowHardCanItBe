package org.robbingdahood.httpserver;

import java.util.Map;
import java.util.function.Function;

public class ListenerRunner implements PathObserver {

    public static GeneralHttpResponse playersGet(HttpRequest request) {
        try {
            System.out.println("Connection, sending data.");
            System.out.println("Method: " + request.getHttpMethod());
            System.out.println("getHeaders: " + request.getHeaders());
            System.out.println("getPath: " + request.getPath());
            System.out.println("getBody: " + request.getBody());

            // Send the response
            // Send the headers
            return GeneralHttpResponse.builder()
                    .statusCode(200)
                    .statusMessage("OK")
                    .headers(Map.of(
                            "Content-Type", "text/html"
                    ))
                    .body("<H1>Welcome to the Ultra Mini-WebServer</H2>")
                    .build();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, Function<HttpRequest, HttpResponse>> getPaths() {
        return Map.of(
                "/players/", ListenerRunner::playersGet
        );
    }
}
