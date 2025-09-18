import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server running on port 1234...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String input;
            while ((input = in.readLine()) != null) {
                System.out.println("Received: " + input);
                out.println("Echo: " + input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
