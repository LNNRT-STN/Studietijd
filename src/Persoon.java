import java.time.LocalDate;

//gegeven

public class Persoon {

    private String naam;
    private String voornaam;
    private String email;
    private String woonplaats;

    public Persoon(String naam, String voornaam,
                   String email, String woonplaats) {
        this.naam = naam;
        this.voornaam = voornaam;
        this.email = email;
        this.woonplaats = woonplaats;

    }
    public String getNaam() {
        return naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getEmail() {
        return email;
    }

    public String getWoonplaats() {
        return woonplaats;
    }


    @Override
    public String toString() {
        return naam + ";" + voornaam + ";" + email + ";" + woonplaats;
    }
}