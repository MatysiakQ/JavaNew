import java.io.*;
import java.net.*;

public class Serwer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Serwer czeka na połączenie...");

            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                System.out.println("Klient połączony!");
                out.println("Cześć, jestem serwerem!");
            }

        } catch (IOException e) {
            System.err.println("Błąd serwera:");
            e.printStackTrace();
        }
    }
}
