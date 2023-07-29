package ui.tabs;

import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;

public class Tab extends JPanel {
    private final MatchHistoryUI controller;

    //REQUIRES: MatchHistoryUI controller that holds this tab
    public Tab(MatchHistoryUI controller) {
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    //EFFECTS: returns the SmartHomeUI controller for this tab
    public MatchHistoryUI getController() {
        return controller;
    }
}
