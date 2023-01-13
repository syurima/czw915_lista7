package classes;

import java.util.ArrayList;
import java.util.List;

public class Student extends Osoba {
    private String ID;
    private int rokStudiow;
    private boolean erasmus;
    private boolean stopien1; //stopien2 - false
    private boolean stacjonarny;
    private List<Kurs> kursy;

    public Student(String imie, String nazwisko, String pesel, int wiek, char plec, String ID, String rok, boolean erasmus, boolean stopien1, boolean stacja) {
        this.ID = "0";
        this.rokStudiow = 0;
        this.kursy = new ArrayList<Kurs>();
        this.erasmus = false;
        this.stopien1 = true;
        this.stacjonarny = true;
    }
    public Student(String imie, String nazwisko, String pesel, int wiek, char plec, String ID, int rokStudiow, boolean erasmus, boolean stopien1, boolean stacjonarny) {
        super(imie, nazwisko, pesel, wiek, plec);
        this.kursy = new ArrayList<Kurs>();
        this.ID = ID;
        this.rokStudiow = rokStudiow;
        this.kursy = new ArrayList<Kurs>();
        this.erasmus = erasmus;
        this.stopien1 = stopien1;
        this.stacjonarny = stacjonarny;
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
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
    public boolean equals(Osoba o) {
        return super.equals(o);
    }
    public String toString() {
        String kursy = "";
        for (Kurs k : this.kursy){
            kursy = kursy + k.getNazwa() + ", ";
        }
        return ("student | " + super.toString() + "Indeks: " + ID + ", " + rokStudiow + " rok studiów | erasmus: " + erasmus + ", " + "I stopień: " + stopien1 + ", " + "stacjonarne: " + stacjonarny + " | kursy: " + kursy);
    }
    public String serialize() {
        String a = "_";
        for (Kurs k : this.kursy){
            a = a + k.getNazwa() + "_";
        }
        return ("S/" +  super.serialize() + String.format("/%s/%d/%s/%s/%s/%s", ID,rokStudiow,erasmus,stopien1,stacjonarny,a));
    }

    public static ArrayList<Osoba> find(ArrayList<Osoba> ludzie, ArrayList<Kurs>kursy, String key, String szukane){
        ArrayList<Osoba> output = new ArrayList<Osoba>();
        switch (key) {
            case "surname": {
                for (Osoba o : ludzie) {
                    if (o instanceof Student && o.getNazwisko().equals(szukane)) output.add(o);
                }
                break;
            }
            case "name": {
                for(Osoba o : ludzie){
                    if(o instanceof Student && o.getImie().equals(szukane)) output.add(o);
                }
                break;
            }
            case "ID": {
                for(Osoba o : ludzie){
                    if(o instanceof Student && ((Student) o).getID().equals(szukane)) output.add(o);
                }
                break;
            }
            case "year": {
                for(Osoba o : ludzie){
                    if(o instanceof Student && ((Student) o).getRokStudiow()==Integer.parseInt(szukane)) output.add(o);
                }
                break;
            }
            case "course": {
                if (Kurs.find(kursy,"name", szukane).size()>0) {
                    Kurs kurs = Kurs.find(kursy,"name", szukane).get(0);
                    for(Osoba o : ludzie){
                        if(o instanceof Student && ((Student) o).getKursy().contains(kurs)) output.add(o);
                    }
                    return output;
                }
                else return null;
            }
        }
        return output;
    }
    public boolean equals(Object obj){
        if (this.getClass() == obj.getClass() && this.ID.equals(((Student) obj).getID()))
            return true;
        return false;
    }
    public int hashCode(){
        return this.getID().hashCode();
    }

}
