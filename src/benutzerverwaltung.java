import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class benutzerverwaltung {


    public static ArrayList<Benutzer> getBenutzer() {
        return benutzer;
    }

    private static ArrayList<Benutzer> benutzer = new ArrayList<Benutzer>();

    public benutzerverwaltung() {

    }

    public static Benutzer getBenutzerFromUserId(int id) {

        for (Benutzer b21: benutzer ) {

            if (b21.getId() == id) {
                return b21;
            }
        }
        return null;
    }





    public void readBenutzerFromFile (String fileName){

       FileReader file = null;


        try {
            file = new FileReader ("benutzer.txt");

        } catch (FileNotFoundException e) {
            System.err.println("Datei nicht gefunden");
        }
        Scanner sc = new Scanner(file);

        sc.useDelimiter(";|\n");
       this.benutzer.clear();

        while(sc.hasNext()) {

                    int id = sc.nextInt();
                    String login = sc.next();
                    String password = sc.next();
                    String vorname =  sc.next();
                    String nachname = sc.next();
                    String email =   sc.next();

                    Benutzer benutzer = null;

            benutzer = new Benutzer(id, login, password, vorname, nachname, email);

            this.benutzer.add(benutzer);
        }
        try {
            file.close();
        } catch (IOException e) {
            System.err.println("Datei nicht geschlossen");
        }


   }

   public void writeBenutzerToFile() {  // evtl. doch ObjectOutput bzw.InputStream
       PrintWriter benutzerFile =null;

       try {
           benutzerFile = new PrintWriter("benutzer.txt");
       } catch (FileNotFoundException e) {
           System.out.println("Fehler beim Anlegen der Datei benutzer.txt");

       }

       for (Benutzer ben: benutzer) {

           benutzerFile.write(ben.getId()+ ";");
           benutzerFile.write(ben.getLogin()+ ";");
           benutzerFile.write(ben.getPassword()+ ";");
           benutzerFile.write(ben.getVorname()+ ";");
           benutzerFile.write(ben.getNachname()+ ";");
           benutzerFile.write(ben.getEmail()+ ";");
           benutzerFile.write("\n");
       }

       benutzerFile.close(); // Weniger Arbeitsspeicher notwendig

   }

   public Benutzer getUserID(String login) {
       Iterator <Benutzer> itr = benutzer.iterator();
       Benutzer benutzer =null;

       while(itr.hasNext()) {
           Benutzer ben = itr.next();

           if (ben.getLogin().equals(login)) {

               return ben;

           }
       }



        return null;
   }

   public void addBenutzer(Benutzer ben) {
        this.benutzer.add(ben);
   }

   public void saveBenutzer() {
       ObjectOutputStream oos = null;

       try {
           oos = new ObjectOutputStream(new FileOutputStream("benutzer.txt"));
       } catch (IOException e) {
           System.out.println("benutzer.txt konnte nicht geladen werden.");

       }

       for ( Benutzer ben: this.benutzer) {
           try {
               oos.writeObject(ben);
           } catch (IOException e) {
               System.out.println("Datei konnte nicht gescchrieben werden.");
           }
       }

       try {
           oos.close();
       } catch (IOException e) {
           System.out.println("Fehler beim Schließen der Datei.");
       }


   }

   public void loadBenutzer() {

   }

   boolean loginVersuch (String login, String password) {
        boolean loginSuccess = false;
        boolean passed = false;
        for (int i =0; i<benutzer.size(); i++) {  /// 3-mal Liste durchgehen

            if (benutzer.get(i).getLogin().equals(login)&& benutzer.get(i).getPassword().equals(password)) { // jedes mal nach diesen Elementen prüfen

                loginSuccess =true;

                if (loginSuccess) {
                    Benutzer ben = benutzer.get(i);  //Benutzer in Klassenvariable speichern??
                    System.out.println("Login erfolgreich!");
                    passed = false;
                    break;
                }


            } else
            {


            }

        }

        if (!loginSuccess) {
            System.err.println("Der Login war nicht erfolgreich. Das Programm wurde beendet.");
            loginSuccess = true;
            passed = true;
        }


        if (passed) {
            if (loginSuccess) {

                System.exit(0);
            }
        }


       return false;
   }





   // nur Mitfahrangebote über Konsolenausgabe, sonst Testklassen



}
