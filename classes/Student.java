package classes;

import java.util.ArrayList;
import java.util.List;

public class Student extends Osoba {
    private String nrIndeksu;
    private int rokStudiow;
    private boolean erasmus;
    private boolean stopien1; //stopien2 - false
    private boolean stacjonarny;
    private List<Kurs> kursy;

    public Student(String imie, String nazwisko, String pesel, int wiek, char plec, String ID, String rok, boolean erasmus, boolean stopien1, boolean stacja) {
        this.nrIndeksu = "0";
        this.rokStudiow = 0;
        this.kursy = new ArrayList<Kurs>();
        this.erasmus = false;
        this.stopien1 = true;
        this.stacjonarny = true;
    }
    public Student(String imie, String nazwisko, String pesel, int wiek, char plec, String nrIndeksu, int rokStudiow, boolean erasmus, boolean stopien1, boolean stacjonarny) {
        super(imie, nazwisko, pesel, wiek, plec);
        this.kursy = new ArrayList<Kurs>();
        this.nrIndeksu = nrIndeksu;
        this.rokStudiow = rokStudiow;
        this.kursy = new ArrayList<Kurs>();
        this.erasmus = erasmus;
        this.stopien1 = stopien1;
        this.stacjonarny = stacjonarny;
    }

    public String getNrIndeksu() {
        return nrIndeksu;
    }
    public void setNrIndeksu(String nrIndeksu) {
        this.nrIndeksu = nrIndeksu;
    }

    public int getRokStudiow() {
        return rokStudiow;
    }
    public void setRokStudiow(int rokStudiow) {
        this.rokStudiow = rokStudiow;
    }

    public List<Kurs> getKursy() {
        return kursy;
    }
    public void setKursy(List<Kurs> kursy) {
        this.kursy = kursy;
    }
    public void addKurs(Kurs k) {
        this.kursy.add(k);
    }
    public boolean isErasmus() {
        return erasmus;
    }
    public void setErasmus(boolean erasmus) {
        this.erasmus = erasmus;
    }

    public boolean isStopien1() {
        return stopien1;
    }
    public void setStopien1(boolean stopien1) {
        this.stopien1 = stopien1;
    }

    public boolean isStacjonarny() {
        return stacjonarny;
    }
    public void setStacjonarny(boolean stacjonarny) {
        this.stacjonarny = stacjonarny;
    }

    public boolean equals(Osoba o) {
        return super.equals(o);
    }
    public String toString() {
        String kursy = "";
        for (Kurs k : this.kursy){
            kursy = kursy + k.getNazwa() + ", ";
        }
        return ("student | " + super.toString() + "Indeks: " + nrIndeksu + ", " + rokStudiow + " rok studiów | erasmus: " + erasmus + ", " + "I stopień: " + stopien1 + ", " + "stacjonarne: " + stacjonarny + " | kursy: " + kursy);
    }
    public String serialize() {
        String a = "_";
        for (Kurs k : this.kursy){
            a = a + k.getNazwa() + "_";
        }
        return ("S/" +  super.serialize() + String.format("/%s/%d/%s/%s/%s/%s",nrIndeksu,rokStudiow,erasmus,stopien1,stacjonarny,a));
    }
    public static List<Osoba> szukajNazwisko(List<Osoba> ludzie, String nazwisko){
        List<Osoba> output = new ArrayList<Osoba>();
        for(Osoba o : ludzie){
            if(o instanceof Student && o.getNazwisko().equals(nazwisko)) output.add(o);
        }
        return output;
    }
    public static List<Osoba> szukajImie(List<Osoba> ludzie, String imie){
        List<Osoba> output = new ArrayList<Osoba>();
        for(Osoba o : ludzie){
            if(o instanceof Student && o.getImie().equals(imie)) output.add(o);
        }
        return output;
    }
    public static List<Osoba> szukajID(List<Osoba> ludzie, String indeks){
        List<Osoba> output = new ArrayList<Osoba>();
        int found = 0;
        for(Osoba o : ludzie){
            if(o instanceof Student && ((Student) o).getNrIndeksu().equals(indeks)) output.add(o);
        }
        return output;
    }
    public static List<Osoba> szukajRok(List<Osoba> ludzie, int rok){
        List<Osoba> output = new ArrayList<Osoba>();
        for(Osoba o : ludzie){
            if(o instanceof Student && ((Student) o).getRokStudiow()==rok) output.add(o);
        }
        return output;
    }
    public static List<Osoba> szukajKurs(List<Osoba> ludzie, List<Kurs>kursy, String nazwa){
        List<Osoba> output = new ArrayList<Osoba>();
        if (Kurs.szukajNazwa(kursy, nazwa).size()>0) {
            Kurs kurs = Kurs.szukajNazwa(kursy, nazwa).get(0);
            for(Osoba o : ludzie){
                if(o instanceof Student && ((Student) o).getKursy().contains(kurs)) output.add(o);
            }
            return output;
        }
        else return null;
    }
    public static void wypiszStudentow(List<Osoba> ludzie){
        //System.out.println("studenci: ");
        for(Osoba o : ludzie){
            if(o instanceof Student) System.out.println(o.toString());
        }
    }

}
