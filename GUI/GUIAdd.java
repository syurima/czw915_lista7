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
import java.util.HashMap;

public class GUIAdd implements ActionListener, ItemListener{

    static JFrame frame = new JFrame();
    static JPanel cards = new JPanel(new CardLayout());
    static HashMap<JButton, String> buttons = new HashMap<JButton, String>();
    static HashMap<String, JTextField> polaKurs = new HashMap<String, JTextField>();
    SystemAdministracji system;
    public void main(SystemAdministracji system){
        this.system = system;
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

        //---
        frame.add(panelChoice, BorderLayout.PAGE_START);
        frame.add(cards, BorderLayout.CENTER);
        frame.setSize(500,300);
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
                Student.wypiszStudentow(system.getLudzie());
                break;
            }
            case "pracownik":{
                PracownikUczelni.wypiszPracowników(system.getLudzie());
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
