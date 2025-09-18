import java.io.*;
import java.net.*;

public class Klient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String wiadomosc = in.readLine();
            System.out.println("Odebrano z serwera: " + wiadomosc);

        } catch (IOException e) {
            System.err.println("Błąd połączenia z serwerem:");
            e.printStackTrace();
        }
    }
}
