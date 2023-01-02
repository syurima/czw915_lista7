package GUI;

import classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

public class GUISearch implements ActionListener, ItemListener {
    SystemAdministracji system;
    static JFrame frame = new JFrame();
    static JPanel cards = new JPanel(new CardLayout());
    static HashMap<JButton, String> buttons = new HashMap<JButton, String>();
    static JTextField input = new JTextField("type here!");
    public void main(SystemAdministracji system){
        this.system = system;
        //wybór co sortować
        JPanel panelChoice = new JPanel();
        JComboBox comboBox = new JComboBox(new String[]{"szukaj kursu", "szukaj studenta", "szukaj pracownika"});
        comboBox.setEditable(false);
        comboBox.addItemListener(this);
        panelChoice.add(comboBox);

        //panel dla kursu
        JPanel panelKurs = new JPanel();
        HashMap<JButton, String> buttonsKurs = new HashMap<JButton, String>();
        buttonsKurs.put(new JButton("ECTS"),"ECTS");
        buttonsKurs.put(new JButton("prowadzący"),"prowadzący");
        buttonsKurs.put(new JButton("nazwa"),"nazwa");
        for(JButton B: buttonsKurs.keySet()){
            B.addActionListener(this);
            panelKurs.add(B);
        }
        buttons.putAll(buttonsKurs);
        cards.add(panelKurs, "szukaj kursu");

        //panel dla studenta
        JPanel panelStudent = new JPanel();
        HashMap<JButton, String> buttonsStudent = new HashMap<JButton, String>();
        buttonsStudent.put(new JButton("nazwisko"),"studentNazwisko");
        buttonsStudent.put(new JButton("imię"),"studentImię");
        buttonsStudent.put(new JButton("ID"),"ID");
        buttonsStudent.put(new JButton("rok studiów"),"rok");
        buttonsStudent.put(new JButton("kurs"),"kurs");
        cards.add(panelStudent, "szukaj studenta");
        for(JButton B: buttonsStudent.keySet()){
            B.addActionListener(this);
            panelStudent.add(B);
        }
        buttons.putAll(buttonsStudent);

        //panel dla pracownika
        JPanel panelPracownik = new JPanel();
        HashMap<JButton, String> buttonsPracownik = new HashMap<JButton, String>();
        buttonsPracownik.put(new JButton("nazwisko"),"pracNazwisko");
        buttonsPracownik.put(new JButton("imię"),"pracImię");
        buttonsPracownik.put(new JButton("stanowisko"),"stanowisko");
        buttonsPracownik.put(new JButton("staż"),"staz");
        buttonsPracownik.put(new JButton("pensja"),"pensja");
        cards.add(panelPracownik, "szukaj pracownika");
        for(JButton B: buttonsPracownik.keySet()){
            B.addActionListener(this);
            panelPracownik.add(B);
        }
        buttons.putAll(buttonsPracownik);

        //frame
        frame.add(panelChoice, BorderLayout.PAGE_START);
        frame.add(cards, BorderLayout.CENTER);
        frame.getContentPane().add(BorderLayout.SOUTH,input);
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    public void itemStateChanged(ItemEvent event) {
        CardLayout card = (CardLayout)(cards.getLayout());
        card.show(cards, (String)event.getItem());
    }
    public void actionPerformed(ActionEvent event){
        //JFrame frame = new JFrame();
        //JPanel panel = new JPanel();
        //JTextField input = new JTextField("type here!");
        //frame.getContentPane().add(BorderLayout.CENTER,input);

        //JButton enter = new JButton("enter");
        //enter.addActionListener(this);
        //frame.getContentPane().add(BorderLayout.SOUTH,enter);

        //frame.setSize(300,100);
        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.setVisible(true);

        switch (buttons.get(event.getSource())){
            case "ECTS":{
                Kurs.wypiszKursy(Kurs.szukajECTS(system.getKursy(),Integer.parseInt(input.getText())));
                break;
            }
            case "prowadzący":{
                Kurs.wypiszKursy(Kurs.szukajProwadzacy(system.getKursy(),(PracownikBD)PracownikUczelni.szukajNazwisko(system.getLudzie(), input.getText()).get(0)));
                break;
            }
            case "nazwa":{
                Kurs.wypiszKursy(Kurs.szukajNazwa(system.getKursy(),input.getText()));
                break;
            }
            case "studentNazwisko":{
                Student.wypiszStudentow(Student.szukajNazwisko(system.getLudzie(), input.getText()));
                break;
            }
            case "studentImię":{
                Student.wypiszStudentow(Student.szukajImie(system.getLudzie(), input.getText()));
                break;
            }
            case "ID":{
                Student.wypiszStudentow(Student.szukajID(system.getLudzie(), input.getText()));
                break;
            }
            case "rok":{
                Student.wypiszStudentow(Student.szukajRok(system.getLudzie(), Integer.parseInt(input.getText())));
                break;
            }
            case "kurs":{
                Student.wypiszStudentow(Student.szukajKurs(system.getLudzie(),system.getKursy(), input.getText()));
                break;
            }
            case "pracNazwisko":{
                PracownikUczelni.wypiszPracowników(PracownikUczelni.szukajNazwisko(system.getLudzie(), input.getText()));
                break;
            }
            case "pracImię":{
                PracownikUczelni.wypiszPracowników(PracownikUczelni.szukajImie(system.getLudzie(), input.getText()));
                break;
            }
            case "stanowisko":{
                PracownikUczelni.wypiszPracowników(PracownikUczelni.szukajStanowisko(system.getLudzie(), input.getText()));
                break;
            }
            case "staz":{
                PracownikUczelni.wypiszPracowników(PracownikUczelni.szukajStaz(system.getLudzie(), Integer.parseInt(input.getText())));
                break;
            }
            case "pensja":{
                PracownikUczelni.wypiszPracowników(PracownikUczelni.szukajPensja(system.getLudzie(), Integer.parseInt(input.getText())));
                break;
            }
        }
    }
}
