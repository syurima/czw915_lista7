import GUI.GUI;
import classes.Admin;
import classes.SystemAdministracji;

public class Main {
    public static void main(String[] args) {
        Admin marek = new Admin(true);
        SystemAdministracji system = new SystemAdministracji(marek, "src/dane_ludzie.txt", "src/dane_kursy.txt");
        //classes.SystemAdministracji s2 = new classes.SystemAdministracji(marek, "src/dane_ludzie2.txt", "src/dane_kursy2.txt");
        system.main();
    }
}