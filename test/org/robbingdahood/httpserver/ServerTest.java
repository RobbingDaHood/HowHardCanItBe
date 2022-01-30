package org.robbingdahood.httpserver;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
        StringBuilder body = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            body.append(inputLine);
        in.close();

        assertEquals(200, connection.getResponseCode());
        assertEquals("OK", connection.getResponseMessage());
        assertEquals("text/html", connection.getHeaderField("Content-Type"));
        assertEquals("Bot", connection.getHeaderField("Server"));
        assertEquals("<H1>Welcome to the Ultra Mini-WebServer</H2>", body.toString());

        //server.shutdown(); //TODO Should be fixed: Can first be tested when there is a operation that takes a long time; I is okay to just shutdown, but likely you want all threads to shutdown nicely.
    }

    @Test
    void startup_not_found() throws IOException {
        Server server = new Server();
        server.startup();

        URL url = new URL("http://localhost:7777/players!/");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        assertEquals(404, connection.getResponseCode());
        assertEquals("NOT FOUND", connection.getResponseMessage());

        //server.shutdown(); //TODO Should be fixed: Can first be tested when there is a operation that takes a long time; I is okay to just shutdown, but likely you want all threads to shutdown nicely.
    }
}