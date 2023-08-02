package ui.tabs;

import model.AgentType;
import ui.ButtonNames;
import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTab extends Tab {
    private static final String ADD_GEN_MESSAGE = "Added a match!";

    private JScrollPane addPane;
    private JTextArea addText;
    private JLabel addMessage;
    private static JTextField textField1;
    private static JTextField textField2;
    private static JTextField textField3;
    private static JTextField textField4;
    private static String gameStatus;
    private static String points;
    private static String enemyPoints;
    private static AgentType gameType;

    //REQUIRES: MatchHistoryUI controller that holds this tab
    //EFFECTS: creates report tab with buttons and application status functionality
    public AddTab(MatchHistoryUI controller) {
        super(controller);
        placeAddButton();
    }

    //MODIFIES: this
    //EFFECTS: adds a generate report button that prints app status when clicked
    private void placeAddButton() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(400, 400);
        frame.setTitle("Match History Console");
        JButton b1 = new JButton(ButtonNames.ADD.getValue());
        b1.setPreferredSize(new Dimension(90,30));

        JLabel labelStatus = new JLabel("Enter win or lose");
        TextField textFieldStatus = new TextField(30);
        textFieldStatus.setColumns(45);

        JLabel labelPoints = new JLabel("Enter points");
        TextField textFieldPoints = new TextField(30);
        textFieldPoints.setColumns(45);

        JLabel labelEnemy = new JLabel("Enter enemy points");
        TextField textFieldEnemy = new TextField(30);
        textFieldEnemy.setColumns(45);

        JLabel labelAgent = new JLabel("Enter agent played");
        TextField textFieldAgent = new TextField(30);
        textFieldAgent.setColumns(45);

        panel.add(labelStatus);
        panel.add(textFieldStatus);
        panel.add(labelPoints);
        panel.add(textFieldPoints);
        panel.add(labelEnemy);
        panel.add(textFieldEnemy);
        panel.add(labelAgent);
        panel.add(textFieldAgent);
        panel.add(b1);
        frame.add(panel);
        frame.setVisible(true);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    gameStatus = textFieldStatus.getText().toLowerCase();
                    points = textFieldPoints.getText();
                    enemyPoints = textFieldEnemy.getText();
                    String played = textFieldAgent.getText().toUpperCase();
                    gameType = getController().getType(played);
                    getController().addCommand(gameStatus, points, enemyPoints, gameType);
                }
            }
        });
    }
}
