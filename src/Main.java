import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    private static Benutzer benutzer; // Klassenvariable Benutzer Benutzer
    benutzerverwaltung benVerwaltung = new benutzerverwaltung();
    mitfahrangebotsverwaltung mitVerwaltung = new mitfahrangebotsverwaltung();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //1. Neue Objekte der Verwaltungsklassen erstellen
        benutzerverwaltung benVerwaltung = new benutzerverwaltung();
        mitfahrangebotsverwaltung mitVerwaltung = new mitfahrangebotsverwaltung();
        buchungsverwaltung buchVerwaltung = new buchungsverwaltung();

        //2. Benutzer aus der persistenten Text-Datei lesen
        benVerwaltung.readBenutzerFromFile("benutzer.txt");  // funktioniert!
        mitVerwaltung.readMitfahrangeboteFromFile("Mitfahrangebote.txt");
        buchVerwaltung.readBuchungenFromFile("buchungen.txt");

        //3. Testweise Ausgabe von den Daten
        System.out.println(benutzerverwaltung.getBenutzer().toString());
        System.out.println(mitVerwaltung.getMitfahrangebotsliste().toString());// Test
        System.out.println(buchVerwaltung.buchungsListe.toString());
        System.out.println(benutzerverwaltung.getBenutzer().get(2));

        //2. Einlesen von Login-Daten
        String loginTry = readString(sc,"Wie ist ihr Login-Name?");
        String passwordTry = readString(sc, "Wie lautet ihr Passwort?");
        benVerwaltung.loginVersuch(loginTry, passwordTry);  // Login-Methode aus Benutzerverwaltung
       Benutzer aktuellerBenutzer = benVerwaltung.getUserID(loginTry);

       System.out.println(aktuellerBenutzer);

        //3. Auswahl eines Menüpunktes


        while (true) {
            int eingabe = readInt(sc, " Bitte treffen Sie eine Auswahl: \n\n 1. Mitfahrangebot anlegen \n 2.Mitfahrangebote anzeigen \n 3.Mitfahrangebot buchen \n 4. Buchungen anzeigen \n 5. Programm beenden");
            switch (eingabe) {

                case 1:
                    mitVerwaltung.createMitfahrangebot(loginTry, aktuellerBenutzer);
                    System.out.println(mitVerwaltung.getMitfahrangebotsliste());
                    mitVerwaltung.saveMitfahrangebote();

                    break;
                case 2:
                    mitVerwaltung.showMitfahrangebote(aktuellerBenutzer.getId());

                    break;
                case 3:
                    String place = readString(sc, "Von wo aus wollen Sie losfahren?");

                    buchungen b1 =null;
                    b1 = mitVerwaltung.mitfahrAngeboteSuchen(place, aktuellerBenutzer.getId());
                    buchVerwaltung.addBuchung(b1);
                    System.out.println(buchVerwaltung.buchungsListe.toString());
                    buchVerwaltung.saveBuchungen("buchungen.txt");
                    break;
                case 4:
                    System.out.println("Deine Buchungen: \n");
                    System.out.println("_______________");

                    for (buchungen meineBuchungen: buchVerwaltung.buchungsListe) {

                        if(meineBuchungen.getBenutzer().getId() == aktuellerBenutzer.getId()) {
                            System.out.println(meineBuchungen.toString());

                        }
                    }
                    break;
                case 5:
                    System.err.println("Das Programm wird beendet.....");
                    System.exit(0);
                    break;

            }
        }
     }

    // public mitfahrangebote createMitfahrangebot () {

        String start = readString(sc, "Von wo aus fahren Sie los?");
        String ziel = readString(sc, "Wo fahren Sie hin?");
        int anzahlPersonen = readInt(sc, "Wie viele Plätze haben Sie noch im Auto?");


        //mitfahrangebote mitfahrangebot = new mitfahrangebote(angebotsNr, date, start, ziel, anzahlPersonen, id);

       // mitVerwaltung.mitfahrangebotsliste.add(mitfahrangebot);
       //  System.out.println(mit);

       // return mitfahrangebot;
    // }


    public void mitfahrAngeboteSuchen(String place, int aktuellerBenutzer) {
        Iterator<mitfahrangebote> itr = mitVerwaltung.getMitfahrangebotsliste().iterator();
        System.out.println("Ihre Gelegenheiten zum Mitfahren: ");
        System.out.println("________________________________\n");

        while (itr.hasNext()) {
            mitfahrangebote m1 = itr.next();
            if ( m1.getStart().equals(place) && m1.getAnzahlPersonen() != 0 && m1.getBenutzer().getId() != aktuellerBenutzer) {
                System.out.println(m1);

            }
        }


    }

    //public void mitfahrangeboteByBuchungsNr() {









    //}

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
                String trash = eingabe.nextLine();
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


