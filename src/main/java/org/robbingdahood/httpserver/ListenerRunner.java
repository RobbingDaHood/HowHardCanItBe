package org.robbingdahood.httpserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ListenerRunner extends Thread {
    private final Socket remote;

    public ListenerRunner(Socket remote) {
        this.remote = remote;
    }

    @Override
    public void run() {
        try {
            System.out.println("Connection, sending data.");
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(remote.getInputStream()));
            PrintWriter out = new PrintWriter(remote.getOutputStream());

            // Print body
            String line;
            while ((line = reader.readLine()) != null && !line.equals("")) {
                System.out.println(line);
            }

            // Send the response
            // Send the headers
            System.out.println("Sending response");
            out.println("HTTP/1.0 200 OK");
            out.println("Content-Type: text/html");
            out.println("Server: Bot");
            // this blank line signals the end of the headers
            out.println("");
            // Send the HTML page
            out.println("<H1>Welcome to the Ultra Mini-WebServer</H2>");
            out.flush();
            remote.close();
            System.out.println("remote.close()");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
