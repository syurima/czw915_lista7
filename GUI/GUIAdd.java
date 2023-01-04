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
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUIAdd implements ActionListener, ItemListener{
    static JPanel cards = new JPanel(new CardLayout());
    static HashMap<JButton, String> buttons = new HashMap<JButton, String>();
    static HashMap<String, JTextField> polaKurs = new HashMap<String, JTextField>();
    static HashMap<String, JTextField> polaStudent = new HashMap<String, JTextField>();
    JTextField coursenames;
    static HashMap<String, JCheckBox> checksStudent = new HashMap<String, JCheckBox>();
    static HashMap<String, JTextField> polaPracownikBD = new HashMap<String, JTextField>();
    static HashMap<String, JTextField> polaPracownikA = new HashMap<String, JTextField>();
    SystemAdministracji system;
    public void main(SystemAdministracji system){
        this.system = system;
        JFrame frame = new JFrame();
        //wybór co dodać
        JPanel panelChoice = new JPanel();
        JComboBox comboBox = new JComboBox(new String[]{"dodaj kurs", "dodaj studenta", "dodaj pracownika administracyjnego", "dodaj pracownika badawczo - dydaktycznego" });
        comboBox.setEditable(false);
        comboBox.addItemListener(this);
        panelChoice.add(comboBox);

        JPanel checks = new JPanel();

        //panel dla kursu
        JPanel panelKurs = new JPanel();
        polaKurs.put("ECTS",new JTextField("ECTS"));
        polaKurs.put("prowadzący", new JTextField("prowadzący"));
        polaKurs.put("nazwa",new JTextField("nazwa"));
        for(JTextField T: polaKurs.values()){
            panelKurs.add(T);
        }
        JButton enterK = new JButton("dodaj");
        buttons.put(enterK,"kurs");
        enterK.addActionListener(this);
        panelKurs.add(enterK, BorderLayout.AFTER_LAST_LINE);
        cards.add(panelKurs, "dodaj kurs");
        //panel dla osoby
        /*
            JPanel panelOsoba = new JPanel();
            polaOsoba.put("imię", new JTextField("imię"));
            polaOsoba.put("nazwisko", new JTextField("nazwisko"));
            polaOsoba.put("pesel", new JTextField("pesel"));
            polaOsoba.put("wiek", new JTextField("wiek"));
            polaOsoba.put("plec", new JTextField("płeć"));
            for (JTextField T : polaOsoba.values()) {
                panelOsoba.add(T);
            }
        */
        //panel dla studenta
        {
            JPanel panelStudent = new JPanel();
            JPanel panelS1 = new JPanel(new GridLayout(2, 4));
            polaStudent.put("imie", new JTextField("imię", 10));
            polaStudent.put("nazwisko", new JTextField("nazwisko"));
            polaStudent.put("pesel", new JTextField("pesel"));
            polaStudent.put("wiek", new JTextField("wiek"));
            polaStudent.put("plec", new JTextField("płeć"));
            polaStudent.put("ID", new JTextField("ID"));
            polaStudent.put("rok", new JTextField("rok"));
            for (JTextField T : polaStudent.values()) {
                panelS1.add(T);
            }
            JPanel panelS2 = new JPanel();
            JTextField coursenames = new JTextField("wpisz nazwy kursów po przecinku");
            checksStudent.put("erasmus", new JCheckBox("erasmus"));
            checksStudent.put("stopien", new JCheckBox("I stopień"));
            checksStudent.put("stacjonarne", new JCheckBox("stacjonarne"));
            for (JCheckBox C : checksStudent.values()) {
                panelS2.add(C);
            }
            JButton enterS = new JButton("dodaj");
            buttons.put(enterS, "student");
            enterS.addActionListener(this);
            panelStudent.add(panelS1, BorderLayout.NORTH);
            panelStudent.add(panelS2, BorderLayout.CENTER);
            panelStudent.add(coursenames, BorderLayout.SOUTH);
            panelStudent.add(enterS, BorderLayout.AFTER_LAST_LINE);
            cards.add(panelStudent, "dodaj studenta");
        }
        //panel dla pracownika bd
        {
            JPanel panelPracownikBD = new JPanel();
            JPanel panelPBD = new JPanel(new GridLayout(2, 4));
            polaPracownikBD.put("imie", new JTextField("imię"));
            polaPracownikBD.put("nazwisko", new JTextField("nazwisko"));
            polaPracownikBD.put("pesel", new JTextField("pesel"));
            polaPracownikBD.put("wiek", new JTextField("wiek"));
            polaPracownikBD.put("plec", new JTextField("płeć"));
            polaPracownikBD.put("stanowisko", new JTextField("stanowisko"));
            polaPracownikBD.put("staz", new JTextField("staz"));
            polaPracownikBD.put("etat", new JTextField("etat"));
            polaPracownikBD.put("pensja", new JTextField("pensja"));
            polaPracownikBD.put("publikacje", new JTextField("publikacje"));
            for (JTextField T : polaPracownikBD.values()) {
                panelPBD.add(T);
            }
            JButton enterPBD = new JButton("dodaj");
            buttons.put(enterPBD, "pracownikBD");
            enterPBD.addActionListener(this);
            panelPracownikBD.add(panelPBD, BorderLayout.NORTH);
            panelPracownikBD.add(enterPBD, BorderLayout.AFTER_LAST_LINE);
            cards.add(panelPracownikBD, "dodaj pracownika badawczo - dydaktycznego");
        }
        //panel dla pracownika admin
        {
            JPanel panelPracownikA = new JPanel();
            JPanel panelPA = new JPanel(new GridLayout(2, 4));
            polaPracownikA.put("imie", new JTextField("imię"));
            polaPracownikA.put("nazwisko", new JTextField("nazwisko"));
            polaPracownikA.put("pesel", new JTextField("pesel"));
            polaPracownikA.put("wiek", new JTextField("wiek"));
            polaPracownikA.put("plec", new JTextField("płeć"));
            polaPracownikA.put("stanowisko", new JTextField("stanowisko"));
            polaPracownikA.put("staz", new JTextField("staz"));
            polaPracownikA.put("etat", new JTextField("etat"));
            polaPracownikA.put("pensja", new JTextField("pensja"));
            polaPracownikA.put("nadgodziny", new JTextField("nadgodziny"));
            for (JTextField T : polaPracownikA.values()) {
                panelPA.add(T);
            }
            JButton enterA = new JButton("dodaj");
            buttons.put(enterA, "pracownikA");

            enterA.addActionListener(this);
            panelPracownikA.add(panelPA, BorderLayout.NORTH);
            panelPracownikA.add(enterA, BorderLayout.AFTER_LAST_LINE);
            cards.add(panelPracownikA, "dodaj pracownika administracyjnego");
        }
        //---
        frame.add(panelChoice, BorderLayout.PAGE_START);
        frame.add(cards, BorderLayout.CENTER);
        frame.setSize(500,200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void itemStateChanged(ItemEvent event) {
        CardLayout card = (CardLayout)(cards.getLayout());
        card.show(cards, (String)event.getItem());
    }
    public void actionPerformed(ActionEvent event){
        switch (buttons.get(event.getSource())){
            case "student":{
                System.out.println("s");
                system.addStudent(polaStudent, checksStudent, coursenames);
                break;
            }
            case "pracownikBD":{
                System.out.println("bd");
                system.addPracownikBD(polaPracownikBD);
                break;
            }
            case "pracownikA":{
                System.out.println("a");
                system.addPracownikAdmin(polaPracownikA);
                break;
            }
            case "kurs":{
                system.addKurs(polaKurs);
                break;
            }
        }
        system.getAdmin().update();
        system.notifyAdmins();
    }
}
