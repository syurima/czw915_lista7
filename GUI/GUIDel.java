package GUI;

import classes.SystemAdministracji;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class GUIDel  {
    SystemAdministracji system;
    public void main(SystemAdministracji system){
        this.system = system;
        JFrame frame = new JFrame();


        frame.setSize(500,300);
        frame.setVisible(true);
    }
}
