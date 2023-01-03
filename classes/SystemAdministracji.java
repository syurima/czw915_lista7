package classes;

import GUI.GUI;
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
    protected List<Kurs> kursy = new ArrayList<Kurs>();
    protected List<Osoba> ludzie = new ArrayList<Osoba>();
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

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Dodaj");
        System.out.println("2. Usuń");
        switch (scanner.nextLine().charAt(0)){
            case '1':{
                if (this.admin.op == true){
                    menuAdd();
                    admin.update();
                    notifyAdmins();
                }
                else{
                    System.out.println("brak dostępu");
                }
                break;
            }
            case '2':{
                if (this.admin.op == true){
                    menuDel();
                    admin.update();
                    notifyAdmins();
                }
                else{
                    System.out.println("brak dostępu");
                }
                break;
            }
        }
        System.out.println("___ \n continue?");
        if (scanner.nextLine().charAt(0) == 'y') {
            System.out.println("___");
            main();
        }
    }
    public void menuAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Dodaj kurs");
        System.out.println("2.Dodaj studenta");
        System.out.println("3.Dodaj pracownika administracji");
        System.out.println("4.Dodaj pracownika badawczo-dydaktycznego");
        char a = scanner.nextLine().charAt(0);
        if (a == '1'){
            //addKurs();
        }
        else if(Character.getNumericValue(a) < 5) {
            System.out.println("imię:");
            String imie = scanner.nextLine();
            System.out.println("nazwisko:");
            String nazwisko = scanner.nextLine();
            System.out.println("pesel:");
            String pesel = scanner.nextLine();
            System.out.println("wiek:");
            int wiek = scanner.nextInt();
            System.out.println("płeć:");
            char plec = scanner.next().charAt(0);
            switch (a) {
                case '2': {
                    addStudent(imie, nazwisko, pesel, wiek, plec);
                    break;
                }
                case '3': {
                    addPracownikAdmin(imie, nazwisko, pesel, wiek, plec);
                    break;
                }
                case '4': {
                    addPracownikBD(imie, nazwisko, pesel, wiek, plec);
                    break;
                }
            }
        }
    }
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
    public void sortujKursy() {
        Collections.sort(kursy,new CompareECTS().thenComparing(new CompareProwadzacy()));
        admin.update();
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
        admin.update();
    }
    public void addStudent(String imie, String nazwisko, String pesel, int wiek, char plec){
        Scanner scanner = new Scanner(System.in);
        System.out.println("nr. Indeksu: ");
        String ID = scanner.nextLine();
        System.out.println("rok studiów: ");
        int rok = scanner.nextInt();
        System.out.println("erasmus?");
        boolean erasmus = (scanner.next().charAt(0)=='y');
        System.out.println("I stopień?");
        boolean stopien1 = (scanner.next().charAt(0)=='y');
        System.out.println("stacjonarne?");
        boolean stacja = (scanner.next().charAt(0)=='y');
        Student s = new Student(imie, nazwisko, pesel, wiek, plec, ID, rok, erasmus, stopien1, stacja);
        System.out.println("czy chcesz dodać kurs?");
        while (scanner.next().charAt(0)=='y'){
            System.out.println("podaj nazwę kursu");
            String nazwaKursu = scanner.nextLine();
            while(Kurs.szukajNazwa(kursy,nazwaKursu).size()==0){
                System.out.println("nie znaleziono kursu, spróbuj ponownie");
                nazwaKursu = scanner.nextLine();
            }
            s.addKurs(Kurs.szukajNazwa(kursy,nazwaKursu).get(0));
            System.out.println("czy chcesz dodać kurs?");
        }
        ludzie.add(s);
    }
    public void addPracownikAdmin(String imie, String nazwisko, String pesel, int wiek, char plec){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Stanowisko:");
        String stanowisko = "";
        System.out.println("1.Referent");
        System.out.println("2.Specjalista");
        System.out.println("3.Starszy Specjalista");
        switch (scanner.nextLine().charAt(0)) {
            case '1': {
                stanowisko = "Referent";
                break;
            }
            case '2': {
                stanowisko = "Specjalista";
                break;
            }
            case '3': {
                stanowisko = "Starszy Specjalista";
                break;
            }
        }

        System.out.println("Staz:");
        int staz = scanner.nextInt();
        System.out.println("pensja:");
        int pensja = scanner.nextInt();
        System.out.println("etat:");
        int etat = scanner.nextInt();
        System.out.println("nadgodziny:");
        int nadgodziny = scanner.nextInt();
        PracownikAdmin o = new PracownikAdmin(imie, nazwisko, pesel, wiek, plec, stanowisko, staz, pensja, etat, nadgodziny);

        ludzie.add(o);
    }
    public void addPracownikBD(String imie, String nazwisko, String pesel, int wiek, char plec){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Stanowisko:");
        String stanowisko = "";
        System.out.println("1.Asystent");
        System.out.println("2.Adiunkt");
        System.out.println("3.Wykładowca");
        System.out.println("4.Profesor Zwyczajny");
        System.out.println("5.Profesor Nadzwyczajny");
        switch (scanner.nextLine().charAt(0)) {
            case '1': {
                stanowisko = "Asystent";
                break;
            }
            case '2': {
                stanowisko = "Adiunkt";
                break;
            }
            case '3': {
                stanowisko = "Wykładowca";
                break;
            }
            case '4': {
                stanowisko = "Profesor Zwyczajny";
                break;
            }
            case '5': {
                stanowisko = "Profesor Nadzwyczajny";
                break;
            }
        }

        System.out.println("Staz:");
        int staz = scanner.nextInt();
        System.out.println("pensja:");
        int pensja = scanner.nextInt();
        System.out.println("etat:");
        int etat = scanner.nextInt();
        System.out.println("publikacje:");
        int publikacje = scanner.nextInt();
        PracownikBD o = new PracownikBD(imie, nazwisko, pesel, wiek, plec, stanowisko, staz, pensja, etat,  publikacje);

        ludzie.add(o);
    }
    public void addKurs(HashMap<String, JTextField> polaKurs){
        Scanner scanner = new Scanner(System.in);
        String nazwisko = polaKurs.get("prowadzący").getText();
        if(PracownikUczelni.szukajNazwisko(ludzie,nazwisko).size()==0){
            System.out.println("nie znaleziono prowadzącego, spróbuj ponownie");
        }
        else {
            String nazwa = polaKurs.get("nazwa").getText();
            PracownikBD prowadzacy = (PracownikBD) PracownikUczelni.szukajNazwisko(ludzie, nazwisko).get(0);
            int ECTS = Integer.parseInt(polaKurs.get("ECTS").getText());
            kursy.add(new Kurs(nazwa, prowadzacy, ECTS));
        }
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
                            if (Kurs.szukajNazwa(kursy,nazwa).size()!=0) student.addKurs(Kurs.szukajNazwa(kursy,nazwa).get(0));
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
    public String getPathLudzie() {
        return pathLudzie;
    }
    public void setPathLudzie(String pathLudzie) {
        this.pathLudzie = pathLudzie;
    }
    public String getPathKursy() {
        return pathKursy;
    }
    public void setPathKursy(String pathKursy) {
        this.pathKursy = pathKursy;
    }
    public List<Kurs> getKursy() {
        return kursy;
    }
    public void setKursy(List<Kurs> kursy) {
        this.kursy = kursy;
    }
    public List<Osoba> getLudzie() {
        return ludzie;
    }
    public void setLudzie(List<Osoba> ludzie) {
        this.ludzie = ludzie;
    }

    public void notifyAdmins(){
        for (Osoba o : ludzie)
            if (o instanceof PracownikAdmin){
                ((PracownikAdmin)o).update();
            }
    }
}
