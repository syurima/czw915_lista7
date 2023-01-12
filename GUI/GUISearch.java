package GUI;

import classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

public class GUISearch implements ActionListener, ItemListener {
    SystemAdministracji system;
    static JPanel cards = new JPanel(new CardLayout());
    static JCheckBox del = new JCheckBox("usuń");
    static HashMap<JButton, String> buttons = new HashMap<JButton, String>();
    static JTextField input = new JTextField("type here!");
    public void main(SystemAdministracji system){
        this.system = system;
        JFrame frame = new JFrame();
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
        JPanel panelconstant = new JPanel();
        panelconstant.add(input, BorderLayout.EAST);
        panelconstant.add(del, BorderLayout.AFTER_LAST_LINE);
        //frame
        frame.add(panelChoice, BorderLayout.PAGE_START);
        frame.add(panelconstant, BorderLayout.CENTER);
        frame.add(cards, BorderLayout.SOUTH);
        frame.setSize(500,140);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    public void itemStateChanged(ItemEvent event) {
        CardLayout card = (CardLayout)(cards.getLayout());
        card.show(cards, (String)event.getItem());
    }
    public void actionPerformed(ActionEvent event){
        ArrayList<Kurs> foundCourses = new ArrayList<Kurs>();
        ArrayList<Osoba> foundPeople = new ArrayList<Osoba>();

        switch (buttons.get(event.getSource())){
            case "ECTS":{
                foundCourses = Kurs.find(system.getKursy(),"ECTS", input.getText());
                Kurs.wypiszKursy(foundCourses);
                break;
            }
            case "prowadzący":{
                foundCourses = Kurs.find(system.getKursy(),"teacher", input.getText());
                Kurs.wypiszKursy(foundCourses);
                break;
            }
            case "nazwa":{
                foundCourses = Kurs.find(system.getKursy(),"name", input.getText());
                Kurs.wypiszKursy(foundCourses);
                break;
            }
            case "studentNazwisko":{
                foundPeople = Student.find(system.getLudzie(), system.getKursy(), "surname", input.getText());
                Student.wypiszStudentow(foundPeople);
                break;
            }
            case "studentImię":{
                foundPeople = Student.find(system.getLudzie(), system.getKursy(),"name", input.getText());
                Student.wypiszStudentow(foundPeople);
                break;
            }
            case "ID":{
                foundPeople = Student.find(system.getLudzie(), system.getKursy(), "ID", input.getText());
                Student.wypiszStudentow(foundPeople);
                break;
            }
            case "rok":{
                foundPeople = Student.find(system.getLudzie(), system.getKursy(), "year", input.getText());
                Student.wypiszStudentow(foundPeople);
                break;
            }
            case "kurs":{
                foundPeople = Student.find(system.getLudzie(), system.getKursy(), "course", input.getText());
                Student.wypiszStudentow(foundPeople);
                break;
            }
            case "pracNazwisko":{
                foundPeople = PracownikUczelni.find(system.getLudzie(), "surname", input.getText());
                PracownikUczelni.wypiszPracowników(foundPeople);
                break;
            }
            case "pracImię":{
                foundPeople = PracownikUczelni.find(system.getLudzie(),"name", input.getText());
                PracownikUczelni.wypiszPracowników(foundPeople);
                break;
            }
            case "stanowisko":{
                foundPeople = PracownikUczelni.find(system.getLudzie(),"job",  input.getText());
                PracownikUczelni.wypiszPracowników(foundPeople);
                break;
            }
            case "staz":{
                foundPeople = PracownikUczelni.find(system.getLudzie(), "year", input.getText());
                PracownikUczelni.wypiszPracowników(foundPeople);
                break;
            }
            case "pensja":{
                foundPeople = PracownikUczelni.find(system.getLudzie(), "salary", input.getText());
                PracownikUczelni.wypiszPracowników(foundPeople);
                break;
            }
        }
        if (del.isSelected()){
            for (Kurs k : foundCourses){
                system.getKursy().remove(k);
            }
            for (Osoba o : foundPeople){
                system.getLudzie().remove(o);
            }
            system.getAdmin().update();
            system.notifyAdmins();
        }
    }
}
