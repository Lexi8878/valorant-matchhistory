package ui.tabs;

import ui.ButtonNames;
import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTab extends Tab {
    private static final String VIEW_GEN_MESSAGE = "Current Match History";

    private JScrollPane viewPane;
    private JTextArea viewText;
    private JLabel viewMessage;

    //REQUIRES: MatchHistoryUI controller that holds this tab
    //EFFECTS: creates report tab with buttons and application status functionality
    public ViewTab(MatchHistoryUI controller) {
        super(controller);

        placeViewButton();

        JPanel reportBlock = new JPanel(new GridLayout(2, 1));
        reportBlock.setSize(MatchHistoryUI.WIDTH - (MatchHistoryUI.WIDTH / 5),
                MatchHistoryUI.HEIGHT - (MatchHistoryUI.HEIGHT / 5));
        viewMessage = new JLabel("");
        viewPane = new JScrollPane(new JTextArea(6, 40));
        viewText = new JTextArea("", 6, 40);
        viewText.setVisible(true);

        reportBlock.add(viewMessage);
        reportBlock.add(viewPane);

        add(reportBlock);
    }

    //MODIFIES: this
    //EFFECTS: adds a generate report button that prints app status when clicked
    private void placeViewButton() {
        JButton b1 = new JButton(ButtonNames.GENERATE_MATCH_HISTORY.getValue());
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.GENERATE_MATCH_HISTORY.getValue())) {
                    viewMessage.setText(VIEW_GEN_MESSAGE);
                    viewText.setText(getController().getMatchHistory().matchRunningStatus());
                    viewPane.setViewportView(viewText);
                }
            }
        });

        this.add(buttonRow);
    }
}
