import java.io.*;

public class FileWriteRead {
    public static void main(String[] args) {
        String filename = "data.txt";

        // zapis
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Hello, world!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // odczyt
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
