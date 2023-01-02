package GUI;

import classes.SystemAdministracji;

import javax.swing.*;

public class GUIPrint {
    SystemAdministracji system;
    public void main(SystemAdministracji system){
        this.system = system;
        JFrame frame = new JFrame();


        frame.setSize(500,300);
        frame.setVisible(true);
    }
}
