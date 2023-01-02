package classes;

import java.util.ArrayList;
import java.util.List;

abstract public class PracownikUczelni extends Osoba{
    protected Wyplata wyplata;
    private String stanowisko;
    private int staz;
    private int pensja;

    private int etat;

    public PracownikUczelni() {
        super();
        this.stanowisko = "stanowisko";
        this.staz = 0;
        this.pensja = 0;
        this.etat = 1;
    }
    public PracownikUczelni(String imie, String nazwisko, String pesel, int wiek, char plec, String stanowisko, int staz, int pensja, int etat) {
        super(imie, nazwisko, pesel, wiek, plec);
        this.stanowisko = stanowisko;
        this.staz = staz;
        this.pensja = pensja;
        this.etat = etat;
    }

    public String getStanowisko() {
        return stanowisko;
    }
    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }
    public int getStaz() {
        return staz;
    }
    public void setStaz(int staz) {
        this.staz = staz;
    }
    public int getPensja() {
        return pensja;
    }
    public void setPensja(int pensja) {
        this.pensja = pensja;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
    public boolean equals(Osoba o) {
        return super.equals(o);
    }
    @Override
    public String toString() {
        return super.toString() +  stanowisko  + ", staz: " + staz + ", pensja: " + pensja +  ", etat: " + etat + " , wypłata za miesiąc: " + this.wyplata.liczWyplate(this);
    }
    public String serialize() {
        return (super.serialize()+ String.format("/%s/%d/%d/%d",this.getStanowisko(),this.getStaz(),this.getPensja(),this.getEtat()));
    }
    public static List<Osoba> szukajNazwisko(List<Osoba> ludzie, String nazwisko){
        List<Osoba> output = new ArrayList<Osoba>();
        for(Osoba o : ludzie){
            if(o instanceof PracownikUczelni && o.getNazwisko().equals(nazwisko)) output.add(o);
        }
        return output;
    }
    public static List<Osoba> szukajImie(List<Osoba> ludzie, String imie){
        List<Osoba> output = new ArrayList<Osoba>();
        for(Osoba o : ludzie){
            if(o instanceof PracownikUczelni && o.getImie().equals(imie)) output.add(o);
        }
        return output;
    }
    public static List<Osoba> szukajStanowisko(List<Osoba> ludzie, String stanowisko){
        List<Osoba> output = new ArrayList<Osoba>();
        for(Osoba o : ludzie){
            if(o instanceof PracownikUczelni && ((PracownikUczelni) o).getStanowisko().equals(stanowisko)) output.add(o);
        }
        return output;
    }
    public static List<Osoba> szukajStaz(List<Osoba> ludzie, int staz){
        List<Osoba> output = new ArrayList<Osoba>();
        for(Osoba o : ludzie){
            if(o instanceof PracownikUczelni && ((PracownikUczelni) o).getStaz()==staz) output.add(o);
        }
        return output;
    }
    public static List<Osoba> szukajPensja(List<Osoba> ludzie, int pensja){
        List<Osoba> output = new ArrayList<Osoba>();
        for(Osoba o : ludzie){
            if(o instanceof PracownikUczelni && ((PracownikUczelni) o).getPensja()==pensja) output.add(o);
        }
        return output;
    }
    public static void wypiszPracowników(List<Osoba> ludzie){
        //System.out.println("pracownicy: ");
        for(Osoba o : ludzie){
            if(o instanceof PracownikUczelni) System.out.println(o.toString());
        }
    }
}
