import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
// Philipp Kühling 994439

public class buchungsverwaltung {

    ArrayList<buchungen> buchungsListe = new ArrayList<>();



    public int nextID() {

        return buchungsListe.get(buchungsListe.size()-1).getBuchungsNr()+10;
    }
    public void readBuchungenFromFile (String fileName){
        FileReader file = null;


        try {
            file = new FileReader ("buchungen.txt");

        } catch (FileNotFoundException e) {
            System.err.println("Datei nicht gefunden");
        }
        Scanner sc = new Scanner(file);

        sc.useDelimiter(";|\n");
        sc.useLocale(Locale.US);
        this.buchungsListe.clear();

        while(sc.hasNext()) {

            int buchungsNr = sc.nextInt();
            String buchungsdatum = sc.next();
            double preis = sc.nextDouble();
            int benutzerID =  sc.nextInt();
            int mitfahrangebotsNr = sc.nextInt();
            sc.nextLine();


            buchungen buch = null;

            buch = new buchungen(buchungsNr, buchungsdatum, preis, benutzerverwaltung.getBenutzerFromUserId(benutzerID), mitfahrangebotsverwaltung.getMitfahrangebotFromMitfahrangebotsNr(mitfahrangebotsNr));

            this.buchungsListe.add(buch);
        }
        try {
            file.close();
        } catch (IOException e) {
            System.err.println("Datei nicht geschlossen");
        }


    }

    public void saveBuchungen (String fileName) {
        PrintWriter buchungsFile = null;

        try {
            buchungsFile = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Fehler beim Anlegen der Datei!");
            e.printStackTrace();
        }

        for (buchungen b1: buchungsListe) {
            buchungsFile.write(b1.getBuchungsNr() + ";");
            buchungsFile.write(b1.getBuchungsdatum() + ";");
            buchungsFile.write(b1.getPreis() + ";");
            buchungsFile.write(b1.getBenutzer().getId()+ ";");
            buchungsFile.write(b1.getMitfahrangebot().getAngebotsNr()+ ";");
            buchungsFile.write("\n");
        }

        buchungsFile.close();

}

public boolean addBuchung (buchungen buchung) {
    if (buchung!=null) {
        return this.buchungsListe.add(buchung);
    }
    return false;
}
}
