package classes;

import javax.swing.*;
import java.awt.*;
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
    public static ArrayList<Osoba> find(List<Osoba> ludzie, String key, String szukane){
        ArrayList<Osoba> output = new ArrayList<Osoba>();
        switch (key) {
            case "surname": {
                for(Osoba o : ludzie){
                    if(o instanceof PracownikUczelni && o.getNazwisko().equals(szukane)) output.add(o);
                }
                break;
            }
            case "name": {
                for(Osoba o : ludzie){
                    if(o instanceof PracownikUczelni && o.getImie().equals(szukane)) output.add(o);
                }
                break;
            }
            case "job": {
                for(Osoba o : ludzie){
                    if(o instanceof PracownikUczelni && ((PracownikUczelni) o).getStanowisko().equals(szukane)) output.add(o);
                }
                break;
            }
            case "year": {
                for(Osoba o : ludzie){
                    if(o instanceof PracownikUczelni && ((PracownikUczelni) o).getStaz()==Integer.parseInt(szukane)) output.add(o);
                }
                break;
            }
            case "salary": {
                for(Osoba o : ludzie){
                    if(o instanceof PracownikUczelni && ((PracownikUczelni) o).getPensja()==Integer.parseInt(szukane)) output.add(o);
                }
            }
        }
        return output;
    }
    public static void wypiszPracowników(List<Osoba> ludzie){
        JFrame frame = new JFrame("znalezieni pracownicy");
        JTextArea textArea = new JTextArea();
        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.setSize(1000,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        for(Osoba o : ludzie){
            if(o instanceof PracownikUczelni) textArea.append(o.toString() + "\n");
        }
    }
}
