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
    public static ArrayList<Kurs> find(ArrayList<Kurs> kursy, String key, String szukane){
        ArrayList<Kurs> output = new ArrayList<Kurs>();
        switch (key) {
            case "name": {
                for(Kurs k : kursy){
                    if(k.getNazwa().equals(szukane)) output.add(k);
                }
                break;
            }
            case "ECTS": {
                for(Kurs k : kursy){
                    if(k.getPunktyECTS() == Integer.parseInt(szukane)) output.add(k);
                }
                break;
            }
            case "teacher": {
                for(Kurs k : kursy){
                    if(k.getProwadzacy().getNazwisko().equals(szukane)) output.add(k);
                }
                break;
            }
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
