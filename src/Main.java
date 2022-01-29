import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        int port = 7777;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Listen on port " + port);

        for (;;) {
            // wait for a connection
            Socket remote = serverSocket.accept();
            // remote is now the connected socket
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
        }
    }
}
