package GUI;

import classes.Osoba;
import classes.PracownikUczelni;
import classes.Student;
import classes.SystemAdministracji;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import GUI.*;

public class GUIPrintNoDupes implements ActionListener{
    SystemAdministracji system;
    static HashMap<JButton, String> buttons;
    public void main(SystemAdministracji system){
        JFrame frame = new JFrame();
        this.system = system;
        JPanel panel = new JPanel();
        buttons = new HashMap<JButton, String>();
        buttons.put(new JButton("wypisz studentów"),"student");
        buttons.put(new JButton("wypisz pracowników"),"pracownik");
        for(JButton B: buttons.keySet()){
            B.addActionListener(this);
            panel.add(B);
        }
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(500,100);
        frame.setVisible(true);
    }
    public ArrayList<Osoba> removeDupes(){
        ArrayList<Osoba> output = new ArrayList<Osoba>();
        HashSet<Osoba> hashSet = new HashSet<Osoba>();

        for(Osoba o:system.getLudzie()){
            hashSet.add(o);
        }
        for(Osoba o:hashSet){
            output.add(o);
        }
        return output;
    }
    public void actionPerformed(ActionEvent event){
        switch (buttons.get(event.getSource())){
            case "student":{
                GUIPrint.wypiszStudentow(removeDupes());
                break;
            }
            case "pracownik":{
                GUIPrint.wypiszPracowników(removeDupes());
                break;
            }
        }
    }
}
