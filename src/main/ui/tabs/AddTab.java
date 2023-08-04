package ui.tabs;

import model.AgentType;
import ui.ButtonNames;
import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Tab that allows user to add match details into text fields and adds match into match history
public class AddTab extends Tab {
    private JPanel panel;
    private JLabel labelStatus;
    private JLabel labelPoints;
    private JLabel labelEnemy;
    private JLabel labelAgent;
    private JButton b1;
    private static TextField textFieldStatus;
    private static TextField textFieldPoints;
    private static TextField textFieldEnemy;
    private static TextField textFieldAgent;
    private static String gameStatus;
    private static String points;
    private static String enemyPoints;
    private static AgentType gameType;

    // REQUIRES: MatchHistoryUI controller that holds this tab
    // EFFECTS: creates add tab that allows user to enter match details into text fields
    public AddTab(MatchHistoryUI controller) {
        super(controller);
        placeAddButton();
    }

    // MODIFIES: this
    // EFFECTS: initializes GUI frame, panel, labels, button
    public void init() {
        setLayout(new GridLayout(1, 20));
        panel = new JPanel();
        panel.setSize(600, 400);

        labelStatus = new JLabel("Enter win or lose");
        textFieldStatus = new TextField(30);
        textFieldStatus.setColumns(45);

        labelPoints = new JLabel("Enter team points");
        textFieldPoints = new TextField(30);
        textFieldPoints.setColumns(45);

        labelEnemy = new JLabel("Enter enemy points");
        textFieldEnemy = new TextField(30);
        textFieldEnemy.setColumns(45);

        labelAgent = new JLabel("Enter agent played");
        textFieldAgent = new TextField(30);
        textFieldAgent.setColumns(45);

        b1 = new JButton(ButtonNames.ADD.getValue());
        b1.setPreferredSize(new Dimension(90,30));

    }

    // MODIFIES: this
    // EFFECTS: adds labels and text fields to JPanel
    private void addToPanel() {
        panel.add(labelStatus);
        panel.add(textFieldStatus);
        panel.add(labelPoints);
        panel.add(textFieldPoints);
        panel.add(labelEnemy);
        panel.add(textFieldEnemy);
        panel.add(labelAgent);
        panel.add(textFieldAgent);
        panel.add(b1);
    }

    //MODIFIES: this
    //EFFECTS: adds a generate report button that prints app status when clicked
    private void placeAddButton() {
        init();
        addToPanel();

        this.add(panel);
        this.setVisible(true);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    gameStatus = textFieldStatus.getText().toLowerCase();
                    points = textFieldPoints.getText();
                    enemyPoints = textFieldEnemy.getText();
                    gameType = AgentType.valueOf(textFieldAgent.getText().toUpperCase());
                    getController().addCommand(gameStatus, points, enemyPoints, gameType);
                }
            }
        });
    }
}
