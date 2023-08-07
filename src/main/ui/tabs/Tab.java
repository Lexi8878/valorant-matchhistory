package ui.tabs;

import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;

// The code in the tab classes (addtab, hometab, saveload tab, etc) is based off of the code from the tab classes in
// the SmartHome project
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters/tree/main/SmartHome/src/main/ui/tabs
// Represents a basic tab for match history console
public class Tab extends JPanel {
    private MatchHistoryUI controller;

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

    //EFFECTS: returns the MatchHistoryUI controller for this tab
    public MatchHistoryUI getController() {
        return controller;
    }
}
