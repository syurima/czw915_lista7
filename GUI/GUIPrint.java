package GUI;

import classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;

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
    public static void wypiszStudentow(List<Osoba> ludzie){
        JFrame frame = new JFrame("znalezieni studenci");
        JTextArea textArea = new JTextArea();
        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.setSize(800,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        for(Osoba o : ludzie){
            if(o instanceof Student) textArea.append(o.toString() + "\n");
        }
    }
    public static void wypiszPracowników(List<Osoba> ludzie){
        JFrame frame = new JFrame("znalezieni pracownicy");
        JTextArea textArea = new JTextArea();
        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.setSize(1000,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        for(Osoba o : ludzie){
            if(o instanceof PracownikUczelni) textArea.append(o.toString() + "\n");
        }
    }
    public static void wypiszKursy(List<Kurs> kursy){
        JFrame frame = new JFrame("znalezione kursy");
        JTextArea textArea = new JTextArea();
        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        for(Kurs k : kursy){
            textArea.append(k.toString() + "\n");;
        }
    }
    public void actionPerformed(ActionEvent event){
        switch (buttons.get(event.getSource())){
            case "student":{
                wypiszStudentow(system.getLudzie());
                break;
            }
            case "pracownik":{
                wypiszPracowników(system.getLudzie());
                break;
            }
            case "kurs":{
                wypiszKursy(system.getKursy());
                break;
            }
        }
    }
}
