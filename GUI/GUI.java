package GUI;

import classes.SystemAdministracji;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class GUI implements ActionListener {
    static SystemAdministracji system;
    static JFrame frame = new JFrame("co chcesz zrobić?");
    static HashMap<JButton, String> buttons = new HashMap<JButton, String>();
    public void main(SystemAdministracji system) {
        this.system = system;

        buttons.put(new JButton("dodaj"),"add");
        buttons.put(new JButton("wypisz"),"print");
        buttons.put(new JButton("szukaj i usuń"),"search");
        buttons.put(new JButton("sort"),"sort");
        buttons.put(new JButton("wypisz bez duplikatów"),"nodupes");
        JPanel menu = new JPanel(new GridLayout());
        for(JButton B: buttons.keySet()){
            B.addActionListener(this);
            menu.add(B);
        }
        frame.setSize(900,80);
        frame.add(menu, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void update(){
        JFrame frame = new JFrame();
        frame.add(new JButton("dane zapisane"));
        frame.setSize(200,100);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event){
        switch (buttons.get(event.getSource())){
            case "add":{
                GUIAdd guiAdd = new GUIAdd();
                guiAdd.main(system);
                break;
            }
            case "search":{
                GUISearch guiSearch = new GUISearch();
                guiSearch.main(system);
                break;
            }
            case "print":{
                GUIPrint guiPrint = new GUIPrint();
                guiPrint.main(system);
                break;
            }
            case "sort":{
                GUISort guiSort = new GUISort();
                guiSort.main(system);
                break;
            }
            case "nodupes":{
                GUIPrintNoDupes guiPrintNoDupes = new GUIPrintNoDupes();
                guiPrintNoDupes.main(system);
                break;
            }
        }
    }
}
