package classes;

import GUI.*;
import comparators.*;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class SystemAdministracji{
    protected Admin admin;
    protected String pathLudzie;
    protected String pathKursy;
    protected ArrayList<Kurs> kursy = new ArrayList<Kurs>();
    protected ArrayList<Osoba> ludzie = new ArrayList<Osoba>();
    public SystemAdministracji(Admin admin, String pathLudzie, String pathKursy) {
        this.admin = admin;
        this.pathLudzie = pathLudzie;
        this.pathKursy = pathKursy;
    }
    public void main() {
        admin.observe(this);
        wczytajKursyPlik();
        wczytajLudziPlik();

        GUI gui = new GUI();
        gui.main(this);
    }
    /*
    public void menuDel(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Usuń kurs");
        System.out.println("2.Usuń studenta");
        System.out.println("3.Usuń pracownika");
        switch (scanner.nextLine().charAt(0)){
            case '1': {
                System.out.println("1.Szukaj wg nazwy");
                System.out.println("2.Szukaj wg nazwiska prowadzącego");
                System.out.println("3.Szukaj wg ECTS");
                List<Kurs> del = new ArrayList<Kurs>();
                switch (scanner.nextLine().charAt(0)) {
                    case '1': {
                        System.out.println("podaj nazwę kursu");
                        String nazwa = scanner.nextLine();
                        del = Kurs.szukajNazwa(kursy, nazwa);
                        break;
                    }
                    case '2': {
                        System.out.println("podaj nazwisko");
                        String nazwisko = scanner.nextLine();
                        del = Kurs.szukajProwadzacy(kursy, (PracownikBD) PracownikUczelni.szukajNazwisko(ludzie, nazwisko).get(0));
                        break;
                    }
                    case '3': {
                        System.out.println("podaj ects");
                        int ects = scanner.nextInt();
                        del = Kurs.szukajECTS(kursy, ects);
                        break;
                    }
                }
                for (Kurs k : del){
                    kursy.remove(k);
                }

                wypiszKursyPlik();
                wczytajLudziPlik();
                wypiszLudziPlik();
                break;
            }
            case '2': {
                System.out.println("1.Szukaj wg imienia");
                System.out.println("2.Szukaj wg nazwiska");
                System.out.println("3.Szukaj wg ID");
                System.out.println("4.Szukaj wg roku studiów");
                System.out.println("5.Szukaj wg nazwyy kursu");
                List<Osoba> del = new ArrayList<Osoba>();
                switch (scanner.nextLine().charAt(0)) {
                    case '1': {
                        System.out.println("podaj imie");
                        String imie = scanner.nextLine();
                        del = Student.szukajImie(ludzie, imie);
                        break;
                    }
                    case '2': {
                        System.out.println("podaj nazwisko");
                        String nazwisko = scanner.nextLine();
                        del = Student.szukajNazwisko(ludzie, nazwisko);
                        break;
                    }
                    case '3': {
                        System.out.println("podaj ID");
                        String ID = scanner.nextLine();
                        del = Student.szukajID(ludzie, ID);
                        break;
                    }
                    case '4': {
                        System.out.println("podaj rok studiów");
                        int rok = scanner.nextInt();
                        del = Student.szukajRok(ludzie, rok);
                        break;
                    }
                    case '5': {
                        System.out.println("podaj kurs");
                        String kurs = scanner.nextLine();
                        del = Student.szukajKurs(ludzie,kursy, kurs);
                        break;
                    }
                }
                for (Osoba o : del){
                    ludzie.remove(o);
                }
                wypiszLudziPlik();
                break;
            }
            case '3': {
                System.out.println("1.Szukaj wg imienia");
                System.out.println("2.Szukaj wg nazwiska");
                System.out.println("3.Szukaj wg stanowiska");
                System.out.println("4.Szukaj wg stazu");
                System.out.println("5.Szukaj wg pensji");
                List<Osoba> del = new ArrayList<Osoba>();
                switch (scanner.nextLine().charAt(0)) {
                    case '1': {
                        System.out.println("podaj imie");
                        String imie = scanner.nextLine();
                        del = PracownikUczelni.szukajImie(ludzie, imie);
                        break;
                    }
                    case '2': {
                        System.out.println("podaj nazwisko");
                        String nazwisko = scanner.nextLine();
                        del = PracownikUczelni.szukajNazwisko(ludzie, nazwisko);
                        break;
                    }
                    case '3': {
                        System.out.println("podaj stanowisko");
                        String stanowisko = scanner.nextLine();
                        del = PracownikUczelni.szukajStanowisko(ludzie, stanowisko);
                        break;
                    }
                    case '4': {
                        System.out.println("podaj staz");
                        int rok = scanner.nextInt();
                        del = PracownikUczelni.szukajStaz(ludzie, rok);
                        break;
                    }
                    case '5': {
                        System.out.println("podaj pensje");
                        int pensja = scanner.nextInt();
                        del = PracownikUczelni.szukajPensja(ludzie,pensja);
                        break;
                    }
                }
                for (Osoba o : del){
                    ludzie.remove(o);
                }
                wypiszLudziPlik();
                break;
            }
        }
    }

     */
    public void sortujKursy() {
        Collections.sort(kursy,new CompareECTS().thenComparing(new CompareProwadzacy()));
    }
    public void sortujLudzi(String klucz) {
        switch (klucz){
            case "nazwisko":{
                Collections.sort(ludzie,new CompareSurname());
                break;
            }
            case "nazwisko + imię":{
                Collections.sort(ludzie,new CompareSurname().thenComparing(new CompareName()));
                break;
            }
            case "nazwisko + wiek":{
                Collections.sort(ludzie,new CompareSurname().thenComparing(new CompareWiek().reversed()));
                break;
            }
        }
    }
    public void addStudent(HashMap<String, JTextField> polaStudent, HashMap<String, JCheckBox> checksStudent, JTextField coursenames){
        String imie = polaStudent.get("imie").getText();
        String nazwisko = polaStudent.get("nazwisko").getText();
        String pesel = polaStudent.get("pesel").getText();
        int wiek = Integer.parseInt(polaStudent.get("wiek").getText());
        char plec = polaStudent.get("plec").getText().charAt(0);
        String ID = polaStudent.get("ID").getText();
        int rok = Integer.parseInt(polaStudent.get("rok").getText());
        boolean erasmus = (checksStudent.get("erasmus").isSelected());
        boolean stopien = (checksStudent.get("stopien").isSelected());
        boolean stacjonarne = (checksStudent.get("stacjonarne").isSelected());
        Student student = new Student(imie, nazwisko, pesel, wiek, plec, ID, rok, erasmus, stopien, stacjonarne);
        String[] courses = coursenames.getText().split(", ");
        for (String s : courses){
            for(Kurs k : Kurs.find(this.kursy, "name", s)){
                student.addKurs(k);
            }
        }
        ludzie.add(student);
    }
    public void addPracownikAdmin(HashMap<String, JTextField> polaPracownikA, String stanowisko){
        String imie = polaPracownikA.get("imie").getText();
        String nazwisko = polaPracownikA.get("nazwisko").getText();
        String pesel = polaPracownikA.get("pesel").getText();
        int wiek = Integer.parseInt(polaPracownikA.get("wiek").getText());
        char plec = polaPracownikA.get("plec").getText().charAt(0);
        int staz = Integer.parseInt(polaPracownikA.get("staz").getText());
        int pensja = Integer.parseInt(polaPracownikA.get("pensja").getText());
        int etat = Integer.parseInt(polaPracownikA.get("etat").getText());

        PracownikAdmin o = new PracownikAdmin(imie, nazwisko, pesel, wiek, plec, stanowisko, staz, pensja, etat, Integer.parseInt(polaPracownikA.get("nadgodziny").getText()));
        ludzie.add(o);
    }
    public void addPracownikBD(HashMap<String, JTextField> polaPracownikBD, String stanowisko){
        String imie = polaPracownikBD.get("imie").getText();
        String nazwisko = polaPracownikBD.get("nazwisko").getText();
        String pesel = polaPracownikBD.get("pesel").getText();
        int wiek = Integer.parseInt(polaPracownikBD.get("wiek").getText());
        char plec = polaPracownikBD.get("plec").getText().charAt(0);
        int staz = Integer.parseInt(polaPracownikBD.get("staz").getText());
        int pensja = Integer.parseInt(polaPracownikBD.get("pensja").getText());
        int etat = Integer.parseInt(polaPracownikBD.get("etat").getText());

        PracownikBD o = new PracownikBD(imie, nazwisko, pesel, wiek, plec, stanowisko, staz, pensja, etat,  Integer.parseInt(polaPracownikBD.get("publikacje").getText()));

        ludzie.add(o);
    }
    public void addKurs(HashMap<String, JTextField> polaKurs){
        Scanner scanner = new Scanner(System.in);
        String nazwisko = polaKurs.get("prowadzący").getText();
        String nazwa = polaKurs.get("nazwa").getText();
        PracownikBD prowadzacy = (PracownikBD) PracownikUczelni.find(ludzie,"surname", nazwisko).get(0);
        int ECTS = Integer.parseInt(polaKurs.get("ECTS").getText());
        kursy.add(new Kurs(nazwa, prowadzacy, ECTS));
    }
    public void wczytajLudziPlik(){
        File input = new File(pathLudzie);
        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] line = data.split("/");
                char typ = data.charAt(0);
                switch (typ){
                    case 'S':{
                        //twórz studenta
                        Student student = new Student(line[1], line[2], line[3], Integer.parseInt(line[4]), line[5].charAt(0), line[6], Integer.parseInt(line[7]), Boolean.parseBoolean(line[8]), Boolean.parseBoolean(line[9]), Boolean.parseBoolean(line[10]));
                        String[] listaKursow = line[11].split("_");
                        for (String nazwa : listaKursow) {
                            if (Kurs.find(kursy,"name",nazwa).size()!=0) student.addKurs(Kurs.find(kursy,"name",nazwa).get(0));
                        }
                        this.ludzie.add(student);
                        break;
                    }
                    case 'A':{
                        //twórz admin
                        PracownikAdmin admin = new PracownikAdmin(line[1], line[2], line[3], Integer.parseInt(line[4]), line[5].charAt(0), line[6], Integer.parseInt(line[7]), Integer.parseInt(line[8]), Integer.parseInt(line[9]),Integer.parseInt(line[10]));
                        this.ludzie.add(admin);
                        break;
                    }
                    case 'D':{
                        //twórz pracownik BD
                        PracownikBD bd = new PracownikBD(line[1], line[2], line[3], Integer.parseInt(line[4]), line[5].charAt(0), line[6], Integer.parseInt(line[7]), Integer.parseInt(line[8]), Integer.parseInt(line[9]),Integer.parseInt(line[10]));
                        this.ludzie.add(bd);
                        break;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e){ System.out.println("nie można wczytać pliku");}
    }
    public void wypiszLudziPlik(){
        File output = new File(pathLudzie);
        try {
            PrintWriter printWriter = new PrintWriter(output);
            for (Osoba o : ludzie) printWriter.println(o.serialize());
            printWriter.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("nie można utworzyć pliku");
        }
    }
    public void wczytajKursyPlik(){
        File input = new File(pathKursy);
        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] line = data.split("/");
                //twórz prowadzącego
                PracownikBD prowadzący = new PracownikBD(line[2], line[3], line[4], Integer.parseInt(line[5]), line[6].charAt(0), line[7], Integer.parseInt(line[8]), Integer.parseInt(line[9]), Integer.parseInt(line[10]),Integer.parseInt(line[11]));
                Kurs kurs = new Kurs(line[0], prowadzący, Integer.parseInt(line[12]));
                this.kursy.add(kurs);
            }
            scanner.close();
        }
        catch (FileNotFoundException e){ System.out.println("nie można wczytać pliku");}
    }
    public void wypiszKursyPlik(){
        File output = new File(pathKursy);
        try {
            PrintWriter printWriter = new PrintWriter(output);
            for (Kurs k : kursy) printWriter.println(k.serialize());
            printWriter.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("nie można utworzyć pliku");
        }
    }

    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public ArrayList<Kurs> getKursy() {
        return kursy;
    }
    public ArrayList<Osoba> getLudzie() {
        return ludzie;
    }

    public void notifyAdmins(){
        for (Osoba o : ludzie)
            if (o instanceof PracownikAdmin){
                ((PracownikAdmin)o).update();
            }
    }
}
