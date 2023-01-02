package classes;

public class PracownikAdmin extends PracownikUczelni{

    private int nadgodziny;

    public PracownikAdmin() {
        super();
        this.wyplata = new WyplataAdmin();
        this.nadgodziny = 0;
    }
    public PracownikAdmin(String imie, String nazwisko, String pesel, int wiek, char plec, String stanowisko, int staz, int pensja, int etat, int nadgodziny) {
        super(imie, nazwisko, pesel, wiek, plec, stanowisko, staz, pensja, etat);
        this.wyplata = new WyplataAdmin();
        this.nadgodziny = nadgodziny;
    }

    public int getNadgodziny() {
        return nadgodziny;
    }
    public void setNadgodziny(int nadgodziny) {
        this.nadgodziny = nadgodziny;
    }
    public boolean equals(Osoba o) {
        return super.equals(o);
    }
    public String toString() {
        return ("pracownik administracyjny | " + super.toString()) + ", nadgodziny: " + nadgodziny;
    }
    public String serialize() {
        return ("A/" +  super.serialize() + String.format("/%d",nadgodziny));
    }
    public void update(){
        System.out.println(this.getImie() + " został powiadomiony o zmianie");
    }
}
