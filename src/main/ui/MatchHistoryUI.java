package ui;

import model.AgentType;
import model.Event;
import model.EventLog;
import model.Game;
import model.MatchHistory;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import static java.awt.Color.*;


// The image was taken from: https://1000logos.net/valorant-logo/
// User Interface for match history application
public class MatchHistoryUI extends JFrame {
    public static final int IMAGE_INDEX = 0;
    public static final int LIGHT_DARK_INDEX = 1;
    public static final int HOME_TAB_INDEX = 2;
    public static final int ADD_TAB_INDEX = 3;
    public static final int WINRATE_AGENT_TAB_INDEX = 4;
    public static final int VIEW_TAB_INDEX = 5;
    public static final int SAVE_LOAD_TAB_INDEX = 6;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private static final String JSON_STORE = "./data/matchhistory.json";

    private JFrame frame;
    private JTabbedPane sidebar;
    private JPanel lightDarkTab;
    private JPanel homeTab;
    private JPanel addTab;
    private JPanel winrateAgentTab;
    private JPanel viewTab;
    private JPanel saveLoadTab;
    private Color original;

    private MatchHistory matchHistory;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static void main(String[] args) {
        try {
            new MatchHistoryUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

    //MODIFIES: this
    //EFFECTS: creates MatchHistoryUI and read/writers, displays sidebar and tabs
    private MatchHistoryUI() throws FileNotFoundException {
        frame = new JFrame();
        frame.setTitle("MatchHistory Console");
        frame.setSize(WIDTH, HEIGHT);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLogEvent();
                System.exit(0);
            }
        });

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        matchHistory = new MatchHistory();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        loadTabs();
        frame.add(sidebar);
        frame.setVisible(true);
    }

    // EFFECTS: returns Match History object controlled by this UI
    public MatchHistory getMatchHistory() {
        return matchHistory;
    }

    // MODIFIES: this
    // EFFECTS: takes user's input for game details and adds that game into match history
    public void addCommand(String gameStatus, String points, String enemyPoints, AgentType gameType) {
        doAddGame(matchHistory, new Game(gameStatus, points, enemyPoints, gameType));
    }

    // EFFECTS: calculates and displays user's win rate percent
    public String winrateCommand() {
        double wr = doCalculate(matchHistory);
        return "Your win rate is: " + wr + "%";
    }

    // EFFECTS: returns a list of how many games user played on each agent
    public double[] getGamesOnAgents(MatchHistory mh) {
        return mh.getGamesOnAgent();
    }

    // EFFECTS: Gets the user's win rate
    public double doCalculate(MatchHistory mh) {
        return mh.calculateWinRate();
    }

    // MODIFIES: this
    // EFFECTS: Adds a game to the user's match history
    private void doAddGame(MatchHistory mh, Game g) {
        mh.addGame(g);
    }

    // EFFECTS: saves match history to file
    public void saveMatchHistory() {
        try {
            jsonWriter.open();
            jsonWriter.write(matchHistory);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads match history from file
    public void loadMatchHistory() {
        try {
            matchHistory = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds home tab, view tab, save/load tab to this UI
    private void loadTabs() {
        lightDarkTab = new LightDarkTab(this);
        homeTab = new HomeTab(this);
        addTab = new AddTab(this);
        winrateAgentTab = new WinrateAgentTab(this);
        viewTab = new ViewTab(this);
        saveLoadTab = new SaveLoadTab(this);

        sidebar.add(new JLabel(new ImageIcon("Fonts/Valorant-Logo1.png")), IMAGE_INDEX);
        sidebar.add(lightDarkTab, LIGHT_DARK_INDEX);
        sidebar.setTitleAt(LIGHT_DARK_INDEX, "Mode");
        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(addTab, ADD_TAB_INDEX);
        sidebar.setTitleAt(ADD_TAB_INDEX, "Add");
        sidebar.add(winrateAgentTab, WINRATE_AGENT_TAB_INDEX);
        sidebar.setTitleAt(WINRATE_AGENT_TAB_INDEX, "Winrate/Agent");
        sidebar.add(viewTab, VIEW_TAB_INDEX);
        sidebar.setTitleAt(VIEW_TAB_INDEX, "View");
        sidebar.add(saveLoadTab, SAVE_LOAD_TAB_INDEX);
        sidebar.setTitleAt(SAVE_LOAD_TAB_INDEX, "Save/Load");

        original = homeTab.getBackground();
    }

    // MODIFIES: this
    // EFFECTS: changes background colour to light mode
    public void changeThemeLight() {
        lightDarkTab.setBackground(original);
        homeTab.setBackground(original);
        addTab.setBackground(original);
        winrateAgentTab.setBackground(original);
        viewTab.setBackground(original);
        saveLoadTab.setBackground(original);
        sidebar.setBackground(original);
        frame.getContentPane().setBackground(original);
    }

    // MODIFIES: this
    // EFFECTS: changes background colour to dark mode
    public void changeThemeDark() {
        lightDarkTab.setBackground(GRAY);
        homeTab.setBackground(GRAY);
        addTab.setBackground(GRAY);
        winrateAgentTab.setBackground(GRAY);
        viewTab.setBackground(GRAY);
        saveLoadTab.setBackground(GRAY);
        sidebar.setBackground(GRAY);
        frame.getContentPane().setBackground(GRAY);
    }

    // EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }

    // EFFECTS: iterates through event log and prints out the events
    public void printLogEvent() {
        Iterator<Event> it =  EventLog.getInstance().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
