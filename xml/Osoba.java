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
