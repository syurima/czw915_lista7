package classes;

public class Admin {
    SystemAdministracji system;
    boolean op;
    public Admin(boolean op) {
        this.system = null;
        this.op = op;
    }
    public Admin(SystemAdministracji system) {
        this.system = system;
    }
    public void observe(SystemAdministracji system){
        this.system = system;
    }
    public void update() {
        SystemAdministracji.wypiszLudziPlik(system.ludzie, system.pathLudzie);
        SystemAdministracji.wypiszKursyPlik(system.kursy, system.pathKursy);
        System.out.println("dane zapisane");

    }
}
