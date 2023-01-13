package classes;

abstract public class Osoba implements FunkcjeOsoba {
    private String imie;
    private String nazwisko;
    private String pesel;
    private int wiek;
    private char plec;

    public Osoba() {
        this.imie = "imie";
        this.nazwisko = "nazwisko";
        this.pesel = "pesel";
        this.wiek = 0;
        this.plec = 'X';
    }
    public Osoba(String imie, String nazwisko, String pesel, int wiek, char plec) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.wiek = wiek;
        this.plec = plec;
    }

    public String getImie() {
        return imie;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }
    public String getNazwisko() {
        return nazwisko;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public String getPesel() {
        return pesel;
    }
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
    public int getWiek() {
        return wiek;
    }
    public void setWiek(int wiek) {
        this.wiek = wiek;
    }
    public char getPlec() {
        return plec;
    }
    public void setPlec(char plec) {
        this.plec = plec;
    }
    public boolean equals(Osoba o) {
        return this.pesel.equals(o.getPesel());
    }
    public String toString(){
        return String.format("%s %s wiek:%s plec:%s pesel:%s | ",imie,nazwisko,wiek,plec, pesel);
    }
    public String serialize() {
        return String.format("%s/%s/%s/%d/%s",imie,nazwisko,pesel,wiek,plec);
    }
    /*
    public int hashCode(){
        if(this instanceof Student) return ((Student) this).getNrIndeksu().hashCode();
        else return this.pesel.hashCode();
    }

     */
}
