package ui;

import ui.tabs.HomeTab;
import ui.tabs.SaveLoadTab;
import ui.tabs.ViewTab;

import javax.swing.*;
import java.io.FileNotFoundException;

public class MatchHistoryUI extends JFrame {
    public static final int HOME_TAB_INDEX = 0;
    public static final int VIEW_TAB_INDEX = 1;
    public static final int SAVE_LOAD_TAB_INDEX = 2;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTabbedPane sidebar;
    private MatchHistoryApp matchHistoryApp;

    public static void main(String[] args) {
        new MatchHistoryUI();
    }

    //MODIFIES: this
    //EFFECTS: creates MatchHistoryUI, loads SmartHome appliances, displays sidebar and tabs
    private MatchHistoryUI() {
        super("MatchHistory Console");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);

        try {
            matchHistoryApp = new MatchHistoryApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

    //EFFECTS: returns Match History object controlled by this UI
    public MatchHistoryApp getMatchHistoryApp() {
        return matchHistoryApp;
    }

    //MODIFIES: this
    //EFFECTS: adds home tab, view tab, save/load tab to this UI
    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel viewTab = new ViewTab(this);
        JPanel quitTab = new SaveLoadTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(viewTab, VIEW_TAB_INDEX);
        sidebar.setTitleAt(VIEW_TAB_INDEX, "View");
        sidebar.add(quitTab, SAVE_LOAD_TAB_INDEX);
        sidebar.setTitleAt(SAVE_LOAD_TAB_INDEX, "Save/Load");
    }

    //EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }
}
