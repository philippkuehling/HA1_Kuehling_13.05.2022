import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Philipp Kühling 994439


public class buchungen {


    int buchungsNr;
    String buchungsdatum;
    double preis;
    Benutzer benutzer;
    mitfahrangebote mitfahrangebot;

    public buchungen(int buchungsNr, String buchungsdatum, double preis, Benutzer benutzer, mitfahrangebote mitfahrangebot) {
        this.buchungsNr = buchungsNr;
        this.buchungsdatum = buchungsdatum;
        this.preis = preis;
        this.benutzer = benutzer;
        this.mitfahrangebot = mitfahrangebot;
    }


    public int getBuchungsNr() {
        return buchungsNr;
    }

    public void setBuchungsNr(int buchungsNr) {
        this.buchungsNr = buchungsNr;
    }

    public String getBuchungsdatum() {
        return buchungsdatum;
    }

    public void setBuchungsdatum(String buchungsdatum) {
        this.buchungsdatum = buchungsdatum;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public mitfahrangebote getMitfahrangebot() {
        return mitfahrangebot;
    }

    public void setMitfahrangebot(mitfahrangebote mitfahrangebot) {
        this.mitfahrangebot = mitfahrangebot;
    }

    @Override
    public String toString() {

        String result = "BuchungsNr: " +this.getBuchungsNr()+ " Buchungsdatum: "+this.getBuchungsdatum()+ " Preis: "+this.getPreis()+ " € Benutzer-ID: "+this.getBenutzer().getId()+ " Mitfahrangebots-Nr: "+this.getMitfahrangebot().getAngebotsNr();

        return result;
    }
}
