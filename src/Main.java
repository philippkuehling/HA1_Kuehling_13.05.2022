import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

// Philipp Kühling 994439

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //1. Neue Objekte der Verwaltungsklassen erstellen
        benutzerverwaltung benVerwaltung = new benutzerverwaltung();
        mitfahrangebotsverwaltung mitVerwaltung = new mitfahrangebotsverwaltung();
        buchungsverwaltung buchVerwaltung = new buchungsverwaltung();

        //2. Benutzer aus der persistenten Text-Datei lesen
        benVerwaltung.readBenutzerFromFile("benutzer.txt");
        mitVerwaltung.readMitfahrangeboteFromFile("Mitfahrangebote.txt");
        buchVerwaltung.readBuchungenFromFile("buchungen.txt");


        //2. Einlesen von Login-Daten
        String loginTry = readString(sc,"Wie ist ihr Login-Name?");
        String passwordTry = readString(sc, "Wie lautet ihr Passwort?");
        benVerwaltung.loginVersuch(loginTry, passwordTry);  // Login-Methode aus Benutzerverwaltung
       Benutzer aktuellerBenutzer = benVerwaltung.getUserID(loginTry);


        //3. Auswahl eines Menüpunktes

        while (true) {
            int eingabe = readInt(sc, "\nBitte treffen Sie eine Auswahl: \n\n 1.Mitfahrangebot anlegen \n 2.Mitfahrangebote anzeigen \n 3.Mitfahrangebot buchen \n 4.Buchungen anzeigen \n 5.Programm beenden");
            switch (eingabe) {

                //Mitfahrangebot anlegen
                case 1:
                    mitVerwaltung.createMitfahrangebot(loginTry, aktuellerBenutzer);
                    mitVerwaltung.saveMitfahrangebote();

                    break;
                    // Anzeigen Mitfahrangebote inkl. Preisausgabe
                case 2:
                    mitVerwaltung.showMitfahrangebote(aktuellerBenutzer.getId());
                    System.out.println("Folgende Preise haben Sie bereits erzielt: ");
                    System.out.println("__________________________________________");
                    buchVerwaltung.getSumOfPreis(mitVerwaltung.getMitfahrangebotsliste(), aktuellerBenutzer);

                    break;
                    // MFA buchen
                case 3:
                    String place = readString(sc, "Von wo aus wollen Sie losfahren?");


                    buchungen b1 =null;
                    b1 = mitVerwaltung.mitfahrAngeboteSuchen(place, aktuellerBenutzer.getId());
                    buchVerwaltung.addBuchung(b1);
                    buchVerwaltung.saveBuchungen("buchungen.txt");
                    mitVerwaltung.saveMitfahrangebote();
                    break;
                    // Buchungen anzeigen
                case 4:
                    System.out.println("Deine Buchungen: ");
                    System.out.println("_______________");

                    for (buchungen meineBuchungen: buchVerwaltung.buchungsListe) {

                        if(meineBuchungen.getBenutzer().getId() == aktuellerBenutzer.getId()) {
                            System.out.println(meineBuchungen.toString());

                        }
                    }
                    break;
                    // Programm beenden
                case 5:
                    System.err.println("Das Programm wird beendet.....");
                    System.exit(0);
                    break;

            }
        }
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


