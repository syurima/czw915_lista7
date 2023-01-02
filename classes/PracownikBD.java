package classes;

public class PracownikBD extends PracownikUczelni{
    private int liczbaPublikacji;

    public PracownikBD() {
        super();
        this.wyplata = new WyplataBD();
        this.liczbaPublikacji = liczbaPublikacji;
    }
    public PracownikBD(String imie, String nazwisko, String pesel, int wiek, char plec, String stanowisko, int staz, int pensja, int etat, int liczbaPublikacji) {
        super(imie, nazwisko, pesel, wiek, plec, stanowisko, staz, pensja, etat);
        this.wyplata = new WyplataBD();
        this.liczbaPublikacji = liczbaPublikacji;
    }

    public int getLiczbaPublikacji() {
        return liczbaPublikacji;
    }
    public void setLiczbaPublikacji(int liczbaPublikacji) {
        this.liczbaPublikacji = liczbaPublikacji;
    }
    public boolean equals(Osoba o) {
        return super.equals(o);
    }

    public String toString() {
        return ("pracownik badawczo-dydaktyczny | " + super.toString() + ", liczba publikacji: " + liczbaPublikacji);
    }
    public String serialize() {
        return ("D/" +  super.serialize() + String.format("/%d",liczbaPublikacji));
    }
}
