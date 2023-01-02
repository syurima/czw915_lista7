package GUI;

import classes.SystemAdministracji;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class GUIAdd {
    SystemAdministracji system;
    public void main(SystemAdministracji system){
        this.system = system;
        JFrame frame = new JFrame();
        JPanel textbox = new JPanel();
        textbox.setLayout(new GridLayout());
        JTextField name = new JTextField("imie");
        JTextField surname = new JTextField("nazwisko");
        textbox.add(name, surname);

        JPanel checks = new JPanel();

        frame.setSize(500,300);
        frame.getContentPane().add(BorderLayout.WEST, textbox);
        frame.setVisible(true);
    }
}
