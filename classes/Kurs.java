package classes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Kurs{
    private String nazwa;
    private PracownikBD prowadzacy;
    private int punktyECTS;

    public Kurs(String nazwa, PracownikBD prowadzacy, int punktyECTS) {
        this.nazwa = nazwa;
        this.prowadzacy = prowadzacy;
        this.punktyECTS = punktyECTS;
    }
    public Kurs() {
        this.nazwa = "domyslny kurs";
        this.prowadzacy = new PracownikBD();
        this.punktyECTS = 0;
    }

    public String getNazwa() {
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public PracownikBD getProwadzacy() {
        return prowadzacy;
    }
    public void setProwadzacy(PracownikBD prowadzacy) {
        this.prowadzacy = prowadzacy;
    }
    public int getPunktyECTS() {
        return punktyECTS;
    }
    public void setPunktyECTS(int punktyECTS) {
        this.punktyECTS = punktyECTS;
    }

    public boolean equals(Kurs k) {
        return this.nazwa.equals(k.getNazwa());
    }
    public String toString() {
        return (nazwa + " | prowadzący: " + prowadzacy.getImie() + " " + prowadzacy.getNazwisko() + " | ECTS: " + punktyECTS);
    }
    public String serialize() {
        return ( String.format("%s/%s/%d",nazwa,prowadzacy.serialize(),punktyECTS));
    }

    public static List<Kurs> szukajNazwa(List<Kurs> kursy, String nazwa){
        List<Kurs> output = new ArrayList<Kurs>();
        for(Kurs k : kursy){
            if(k.getNazwa().equals(nazwa)) output.add(k);
        }
        return output;
    }
    public static List<Kurs> szukajProwadzacy(List<Kurs> kursy, PracownikBD o){
        List<Kurs> output = new ArrayList<Kurs>();
        for(Kurs k : kursy){
            if(k.getProwadzacy().equals(o)) output.add(k);
        }
        return output;
    }
    public static List<Kurs> szukajECTS(List<Kurs> kursy, int ECTS){
        List<Kurs> output = new ArrayList<Kurs>();
        for(Kurs k : kursy){
            if(k.getPunktyECTS() == ECTS) output.add(k);
        }
        return output;
    }
    public static void wypiszKursy(List<Kurs> kursy){
        JFrame frame = new JFrame("znalezione kursy");
        JTextArea textArea = new JTextArea();
        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        for(Kurs k : kursy){
            textArea.append(k.toString() + "\n");;
        }
    }

}
