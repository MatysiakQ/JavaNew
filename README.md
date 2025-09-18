# 📘 Java Exam Cheatsheet

Repozytorium z przykładami kodu pomocnymi na egzaminie z programowania obiektowego w Javie.  
Znajdziesz tu najważniejsze elementy: **OOP, wyjątki, testy, pliki, XML, serwer/klient oraz `toString()`**.

---

## 🔹 1. OOP – Klasy, abstrakcja, dziedziczenie
```
abstract class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract void makeSound();
    public String getName() { return name; }
}

class Dog extends Animal {
    public Dog(String name) { super(name); }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog("Burek");
        dog.makeSound();
    }
}
```
## 2. Własny wyjątek

```
class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}

public class Main {
    public static void riskyMethod(boolean fail) throws MyException {
        if (fail) throw new MyException("Coś poszło nie tak!");
    }

    public static void main(String[] args) {
        try {
            riskyMethod(true);
        } catch (MyException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        }
    }
}
```

# 3. Testy Jednostkowe JUNIT
```
public class Calculator {
    public int add(int a, int b) { return a + b; }
}

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    @Test
    void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
    }
}
```

## 4.1 Odczyt/zapis pliku
```
import java.io.*;

public class FileWriteRead {
    public static void main(String[] args) {
        String filename = "data.txt";

        // zapis
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Hello, world!");
        } catch (IOException e) { e.printStackTrace(); }

        // odczyt
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read: " + line);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
```

##4.2 Odczyt po linii
```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWriteExample {
    public static void main(String[] args) {
        String fileName = "dane.txt";

        // --- ZAPIS DO PLIKU ---
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("To jest pierwsza linia\n");
            writer.write("Tutaj mamy drugą linie\n");
            writer.write("A to jest trzecia linia\n");
            System.out.println("Zapisano dane do pliku: " + fileName);
        } catch (IOException e) {
            System.err.println("Błąd zapisu do pliku: " + e.getMessage());
        }

        // --- ODCZYT PO LINII ---
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;

            System.out.println("\nZawartość pliku:");
            while ((line = reader.readLine()) != null) {
                System.out.println("Linia " + lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (IOException e) {
            System.err.println("Błąd odczytu pliku: " + e.getMessage());
        }
    }
}

```

## 5.XML jako drzewo obiektów (DOM)
```
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
        } catch (Exception e) { e.printStackTrace(); }
    }
}
```

##6. XML jako obiekty (JAXB)
```
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

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
```

##7. Serwer / Klient z obsługą błędów

Serwer:
```
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
```

Klient:
```
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
```

##8. Serwer/Klient z komunikacją dwustronną

Serwer
```
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Serwer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Serwer czeka na połączenie...");

            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(
                         new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                 PrintWriter out = new PrintWriter(
                         new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
                 BufferedReader konsola = new BufferedReader(
                         new InputStreamReader(System.in))) {

                System.out.println("Klient połączony!");

                // pierwsza wiadomość od serwera
                out.println("Cześć, jestem serwerem!");

                String wiadomoscOdKlienta;
                while ((wiadomoscOdKlienta = in.readLine()) != null) {
                    System.out.println("Klient: " + wiadomoscOdKlienta);

                    System.out.print("Serwer> ");
                    String odpowiedz = konsola.readLine();
                    out.println(odpowiedz);
                }
            }

        } catch (IOException e) {
            System.err.println("Błąd serwera:");
            e.printStackTrace();
        }
    }
}
```

Klient
```
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Klient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
             BufferedReader konsola = new BufferedReader(
                     new InputStreamReader(System.in))) {

            String wiadomoscOdSerwera;
            while ((wiadomoscOdSerwera = in.readLine()) != null) {
                System.out.println("Serwer: " + wiadomoscOdSerwera);

                System.out.print("Klient> ");
                String wiadomosc = konsola.readLine();
                out.println(wiadomosc);
            }

        } catch (IOException e) {
            System.err.println("Błąd połączenia z serwerem:");
            e.printStackTrace();
        }
    }
}
```
