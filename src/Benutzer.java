public class Benutzer {

    int id;
    private String login;
    private String password;
    private String vorname;
    private String nachname;
    private String email;

    public Benutzer(int id, String login, String password, String vorname, String nachname, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return this.getId() +" "+this.getLogin()+ " "+this.getPassword()+ " "+this.getVorname()+ " "+this.getNachname() +" "+ this.getEmail();
    }




}
