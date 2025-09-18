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
