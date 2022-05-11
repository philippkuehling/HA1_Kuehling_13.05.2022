import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
// Philipp Kühling 994439

public class mitfahrangebotsverwaltung {
    double gebotenerPreis;
    Scanner sc = new Scanner (System.in);

    public static ArrayList<mitfahrangebote> getMitfahrangebotsliste() {
        return mitfahrangebotsliste;
    }

    private static ArrayList<mitfahrangebote> mitfahrangebotsliste = new ArrayList<>();

    public int nextID() {

        return mitfahrangebotsliste.get(mitfahrangebotsliste.size()-1).getAngebotsNr()+10;
    }

    public static mitfahrangebote getMitfahrangebotFromMitfahrangebotsNr(int id) {

        for (mitfahrangebote m21: mitfahrangebotsliste ) {

            if (m21.getAngebotsNr() == id) {
                return m21;
            }
        }
        return null;
    }

    public void addMitfahrangebot(mitfahrangebote mit) {
        this.mitfahrangebotsliste.add(mit);
    }

    public void removeMitfahrangebot (mitfahrangebote mit) {
        this.mitfahrangebotsliste.remove(mit);
    }

    public void readMitfahrangeboteFromFile (String fileName){
        FileReader file = null;


        try {
            file = new FileReader (fileName);

        } catch (FileNotFoundException e) {
            System.err.println("Datei nicht gefunden");
        }
        Scanner sc = new Scanner(file);

        sc.useDelimiter(";|\n");
        this.mitfahrangebotsliste.clear();

        while(sc.hasNext()) {

            int angebotsNr = sc.nextInt();
            String start = sc.next();
            String ziel = sc.next();
            int anzahlPersonen =  sc.nextInt();
            String date = sc.next();
            int id = sc.nextInt();  // Benutzer benutzer = ??
            sc.nextLine();

            mitfahrangebote mit = null;

            mit = new mitfahrangebote(angebotsNr ,start, ziel, anzahlPersonen, date, benutzerverwaltung.getBenutzerFromUserId(id));

            this.mitfahrangebotsliste.add(mit);

        }
        try {
            file.close();
        } catch (IOException e) {
            System.err.println("Datei nicht geschlossen");
        }


    }



public buchungen mitfahrAngeboteSuchen(String place, int aktuellerBenutzer) {
        buchungen b1 = null;
    Iterator<mitfahrangebote> itr = mitfahrangebotsliste.iterator();
    System.out.println("Ihre Gelegenheiten zum Mitfahren: ");
    System.out.println("________________________________\n");

    while (itr.hasNext()) {
        mitfahrangebote m1 = itr.next();
        if ( m1.getStart().equals(place) && m1.getAnzahlPersonen() != 0 && m1.getBenutzer().getId() != aktuellerBenutzer) {
            System.out.println(m1);

        } else {

        }
    }

    int buchungsNr = readInt(sc, "Geben Sie die Buchungsnummer ein, die Sie buchen wollen?");
    gebotenerPreis = readDouble(sc, "Wie viel sind Sie bereit zu zahlen?");

    //Personenkapazität reduzieren
    for (mitfahrangebote m1: mitfahrangebotsliste) {

        if (buchungsNr == m1.getAngebotsNr() && m1.getAnzahlPersonen() !=0) {
            m1.setAnzahlPersonen(m1.getAnzahlPersonen()-1);

        }
        else {
            continue;
        }
    }



    System.out.println(mitfahrangebotsliste.toString());

    LocalDateTime dateAndTime = LocalDateTime.now();
    DateTimeFormatter zeitFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy/HH:mm");
    String datumUndZeit = dateAndTime.format(zeitFormat);

        for (mitfahrangebote m1: mitfahrangebotsliste) {
            if(m1.getAngebotsNr() == buchungsNr) {
                b1 = new buchungen(nextID(), datumUndZeit, gebotenerPreis, benutzerverwaltung.getBenutzerFromUserId(aktuellerBenutzer),  mitfahrangebotsverwaltung.getMitfahrangebotFromMitfahrangebotsNr(buchungsNr));

            }
        }


return b1;


}


public void saveMitfahrangebote () {
    PrintWriter mitfahrangebotsFile = null;

    try {
        mitfahrangebotsFile = new PrintWriter("mitfahrangebote.txt");
    } catch (FileNotFoundException e) {
        System.out.println("Fehler beim Anlegen der Datei!");
        e.printStackTrace();
    }

    for (mitfahrangebote m1: mitfahrangebotsliste) {
        mitfahrangebotsFile.write(m1.getAngebotsNr() + ";");
        mitfahrangebotsFile.write(m1.getStart() + ";");
        mitfahrangebotsFile.write(m1.getZiel() + ";");
        mitfahrangebotsFile.write(m1.getAnzahlPersonen() + ";");
        mitfahrangebotsFile.write(m1.getDate()+ ";");
        mitfahrangebotsFile.write(m1.getBenutzer().getId() + ";");
        mitfahrangebotsFile.write("\n");
    }

    mitfahrangebotsFile.close();
}



    public void createMitfahrangebot(String loginTry, Benutzer benutzer) {

        System.out.println("Sie wollen ein neues Mitfahrangebot anlegen? Dazu werden noch weitere Daten benötigt! \n ");
        String start = readString(sc, "Von wo aus wollen Sie starten?");
        String ziel = readString(sc, "Wohin wollen Sie fahren?");
        int angebotsNr = nextID();
        String datumUndZeit = readString(sc, "Geben Sie Datum und Zeit ein ( Folegendes Muster muss die Eingabe haben: dd.MM.yyyy/HH:mm"); // in Klasse einbinden
        //int benutzer_ID = benVerwaltung.getUserID(loginTry);
        int anzahlPersonen = readInt(sc, "Wie viele Personen können mitfahren?");

        addMitfahrangebot(new mitfahrangebote(angebotsNr, start, ziel, anzahlPersonen, datumUndZeit, benutzer));
    }

    public void showMitfahrangebote(int benutzer_ID) {
        Iterator<mitfahrangebote> itr = mitfahrangebotsliste.iterator();



        System.out.println("Ihre Mitfahrangebote:");
        System.out.println("____________________\n");


        while (itr.hasNext()) {
            mitfahrangebote mit = itr.next();

            if (mit.getBenutzer().getId() == benutzer_ID) {
                System.out.println(mit.toString());


            }
        }
        System.out.println("\n");
    }




    public static String readString(Scanner eingabe, String hinweis) {
        boolean fehlerhaft = true;
        String result = null;
        do {
            System.out.println(hinweis);

            try {
                result = eingabe.next();
                fehlerhaft = false;
                String restOfLine = eingabe.nextLine();
            } catch (InputMismatchException e) {
                String trash = eingabe.next();
                System.err.println("Diese Eingabe ist nicht erlaubt!");
            }
        } while (fehlerhaft);

        return result;
    }

    public static int readInt(Scanner eingabe, String hinweis) {
        boolean fehlerhaft = true;
        int result = 0;
        do {
            System.out.println(hinweis);

            try {
                result = eingabe.nextInt();
                fehlerhaft = false;
                String restOfLine = eingabe.nextLine();
            } catch (InputMismatchException e) {
                String trash = eingabe.nextLine();
                System.err.println("Diese Eingabe ist nicht erlaubt!");
            }
        } while (fehlerhaft);

        return result;
    }

    public static double readDouble(Scanner eingabe, String hinweis) {
        boolean fehlerhaft = true;
        double result = 0;
        do {
            System.out.println(hinweis);

            try {
                result = eingabe.nextDouble();
                fehlerhaft = false;
                String restOfLine = eingabe.nextLine();
            } catch (InputMismatchException e) {
                String trash = eingabe.nextLine();
                System.err.println("Diese Eingabe ist nicht erlaubt!");
            }
        } while (fehlerhaft);

        return result;
    }
}



