
// Philipp Kühling 994439

public class mitfahrangebote {



    private int angebotsNr =100;
    private String date;
    private String start;
    private String ziel;
    private int anzahlPersonen;
    private Benutzer benutzer;

    public mitfahrangebote(int angebotsNr, String start, String ziel, int anzahlPersonen, String date, Benutzer benutzer) {
        this.angebotsNr = angebotsNr;
        this.date = date;
        this.start = start;
        this.ziel = ziel;
        this.anzahlPersonen = anzahlPersonen;
        this.benutzer = benutzer;
    }

    public int getAngebotsNr() {
        return angebotsNr;
    }

    public void setAngebotsNr(int angebotsNr) {
        this.angebotsNr = angebotsNr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getZiel() {
        return ziel;
    }

    public void setZiel(String ziel) {
        this.ziel = ziel;
    }

    public int getAnzahlPersonen() {
        return anzahlPersonen;
    }

    public void setAnzahlPersonen(int anzahlPersonen) {
        this.anzahlPersonen = anzahlPersonen;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    @Override
    public String toString() {
        String result = "[Angebots-Nr]: " +this.getAngebotsNr()+ " [Datum&Zeit]: " +this.getDate()+ " [Start]: " +this.getStart()+ " [Ziel]: " +this.getZiel()+ " [freie Plaetze]: "+this.getAnzahlPersonen()+ " [Benutzer-ID]: "+this.getBenutzer().getId()+ "\n";
        return result;
    }

}