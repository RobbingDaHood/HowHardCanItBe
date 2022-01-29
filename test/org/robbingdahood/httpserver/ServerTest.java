package org.robbingdahood.httpserver;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {

    @Test
    void startup() throws IOException {
        Server server = new Server();
        server.startup();

        URL url = new URL("http://localhost:7777/players/");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String inputLine;
        System.out.println("Write test: ");
        StringBuilder body = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            body.append(inputLine);
        in.close();

        assertEquals(200, connection.getResponseCode());
        assertEquals("<H1>Welcome to the Ultra Mini-WebServer</H2>", body.toString());

        //server.shutdown(); //TODO Should be fixed
    }
}