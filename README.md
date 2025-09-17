XML jako drzewo obiektów
|
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

import java.io.File;

public class OdczytXML_DOM {
    public static void main(String[] args) {
        try {
            File plik = new File("dane.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(plik);

            doc.getDocumentElement().normalize();

            NodeList lista = doc.getElementsByTagName("osoba");

            for (int i = 0; i < lista.getLength(); i++) {
                Node node = lista.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String imie = element.getElementsByTagName("imie").item(0).getTextContent();
                    String nazwisko = element.getElementsByTagName("nazwisko").item(0).getTextContent();
                    String wiek = element.getElementsByTagName("wiek").item(0).getTextContent();

                    System.out.println(imie + " " + nazwisko + ", wiek: " + wiek);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

XML jako obiekty
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

// Klasa odwzorowana na XML
@XmlRootElement
public class Osoba {
    private String imie;
    private String nazwisko;
    private int wiek;

    @XmlElement
    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }

    @XmlElement
    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }

    @XmlElement
    public int getWiek() { return wiek; }
    public void setWiek(int wiek) { this.wiek = wiek; }
}

SERWER/KLIENT z Obsługą błędów

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

ToSTRING
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
