package GUI;

import classes.SystemAdministracji;
import comparators.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.HashMap;

public class GUISort implements ActionListener, ItemListener {
    SystemAdministracji system;
    JPanel cards = new JPanel(new CardLayout());
    HashMap<JButton, String> buttons = new HashMap<JButton, String>();
    public void main(SystemAdministracji system){
        this.system = system;
        JFrame frame = new JFrame();
        //wybór co sortować
        JPanel panelChoice = new JPanel();
        JComboBox comboBox = new JComboBox(new String[]{"sortuj kursy", "sortuj ludzi"});
        comboBox.setEditable(false);
        comboBox.addItemListener(this);
        panelChoice.add(comboBox);

        //panel dla kursu
        JPanel panelKurs = new JPanel();
        HashMap<JButton, String> buttonsKurs = new HashMap<JButton, String>();
        buttonsKurs.put(new JButton("ECTS + prowadzący"),"ECTS + prowadzący");
        for(JButton B: buttonsKurs.keySet()){
            B.addActionListener(this);
            panelKurs.add(B);
        }
        buttons.putAll(buttonsKurs);
        cards.add(panelKurs, "sortuj kursy");

        //panel dla osoby
        JPanel panelOsoba = new JPanel();
        HashMap<JButton, String> buttonsOsoba = new HashMap<JButton, String>();
        buttonsOsoba.put(new JButton("nazwisko"),"nazwisko");
        buttonsOsoba.put(new JButton("nazwisko + imię"),"nazwisko + imię");
        buttonsOsoba.put(new JButton("nazwisko + wiek"),"nazwisko + wiek");
        cards.add(panelOsoba, "sortuj ludzi");
        for(JButton B: buttonsOsoba.keySet()){
            B.addActionListener(this);
            panelOsoba.add(B);
        }
        buttons.putAll(buttonsOsoba);

        //frame
        frame.add(panelChoice, BorderLayout.PAGE_START);
        frame.add(cards, BorderLayout.CENTER);
        frame.setSize(500,150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    public void itemStateChanged(ItemEvent event) {
        CardLayout card = (CardLayout)(cards.getLayout());
        card.show(cards, (String)event.getItem());
    }
    public void actionPerformed(ActionEvent event){
        System.out.println("posortowane");
        switch (buttons.get(event.getSource())){
            case "ECTS + prowadzący":{
                system.sortujKursy();
                break;
            }
            case "nazwisko":{
                system.sortujLudzi("nazwisko");
                break;
            }
            case "nazwisko + imię":{
                system.sortujLudzi("nazwisko + imię");
                break;
            }
            case "nazwisko + wiek":{
                system.sortujLudzi("nazwisko + wiek");
                break;
            }
        }
        system.getAdmin().update();
    }
}
