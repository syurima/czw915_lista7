package GUI;

import classes.Kurs;
import classes.PracownikUczelni;
import classes.Student;
import classes.SystemAdministracji;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

public class GUIPrint implements ActionListener{
    SystemAdministracji system;
    static HashMap<JButton, String> buttons;
    public void main(SystemAdministracji system){
        JFrame frame = new JFrame();
        this.system = system;
        JPanel panel = new JPanel();
        buttons = new HashMap<JButton, String>();
        buttons.put(new JButton("wypisz studentów"),"student");
        buttons.put(new JButton("wypisz pracowników"),"pracownik");
        buttons.put(new JButton("wypisz kursy"),"kurs");
        for(JButton B: buttons.keySet()){
            B.addActionListener(this);
            panel.add(B);
        }
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(500,100);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event){
        switch (buttons.get(event.getSource())){
            case "student":{
                Student.wypiszStudentow(system.getLudzie());
                break;
            }
            case "pracownik":{
                PracownikUczelni.wypiszPracowników(system.getLudzie());
                break;
            }
            case "kurs":{
                Kurs.wypiszKursy(system.getKursy());
                break;
            }
        }
    }
}
