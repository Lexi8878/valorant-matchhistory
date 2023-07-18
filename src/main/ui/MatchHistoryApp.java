package ui;

import model.AgentType;
import model.Game;
import model.MatchHistory;

import java.util.Scanner;

import static model.AgentType.*;


// Match history application
public class MatchHistoryApp {
    private MatchHistory matchHistory;
    private String userName;
    private Scanner input;
    private AgentType agent;

    // EFFECTS: Constructs a match history application with the user's name and runs the application
    public MatchHistoryApp() {
        runMatchHistory();
    }

    // The following code is taken from the TellerApp class in the TellerApp project:
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java

    // MODIFIES: this
    // EFFECTS: Processes user input
    private void runMatchHistory() {
        boolean conditionTrue = true;
        String command;

        init();
        System.out.println("Welcome");
        System.out.println("Enter username: ");
        userName = input.next();

        while (conditionTrue) {
            displayMenu();
            command = input.next();
            command = command.toUpperCase();

            if (command.equals("QUIT")) {
                conditionTrue = false;
                System.out.println("Quitting program..");
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Initializes user's match history and scanner
    private void init() {
        matchHistory = new MatchHistory();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: Processes user's command
    @SuppressWarnings("methodlength")
    private void processCommand(String command) {
        if (command.equals("ADD")) {
            addCommand();
        } else if (command.equals("VIEW")) {
            viewCommand();
        } else if (command.equals("WINRATE")) {
            winrateCommand();
        } else if (command.equals("AGENT")) {
            agentCommand();
        } else {
            System.out.println("Selection is not valid");
        }
    }

    private void agentCommand() {
        System.out.print("Enter name of agent:");
        String type = input.next().toUpperCase();
        int numGamesPlayed = doCalculateNumGamesPlayedAgent(matchHistory, type);
        System.out.println(type + ": " + numGamesPlayed);
    }

    private void winrateCommand() {
        System.out.println("Calculating...");
        double wr = doCalculate(matchHistory);
        System.out.println("Your win rate is: " + wr + "%");
    }

    private void viewCommand() {
        String history = doDisplayMatches(matchHistory);
        System.out.println("Here is your match history:");
        System.out.println(userName + ": " + history + " as " + agent);
    }

    private void addCommand() {
        System.out.println("Enter win or lose: ");
        String gameStatus = input.next().toLowerCase();
        System.out.println("Enter your team's points: ");
        int points = input.nextInt();
        System.out.println("Enter the enemy team's points: ");
        int enemyPoints = input.nextInt();
        System.out.print("Enter name of agent:");
        String played = input.next().toUpperCase();
        AgentType gameType = getType(played);
        doAddGame(matchHistory, new Game(gameStatus, points, enemyPoints, gameType));
        System.out.println("Added a game to match history");
    }

    // EFFECTS: Displays menu to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1. ADD -> Add a match");
        System.out.println("\t2. VIEW -> View your most recent match");
        System.out.println("\t3. WINRATE -> Calculate your win rate");
        System.out.println("\t4. AGENT -> Calculate the number of times you played a certain agent");
        System.out.println("\t5. QUIT -> Quit program");
    }

    // MODIFIES: this
    // EFFECTS: Adds a game to the user's match history
    private void doAddGame(MatchHistory mh, Game g) {
        mh.addGame(g);
    }

    // MODIFIES: this
    // EFFECTS: Displays the user's match history
    private String doDisplayMatches(MatchHistory mh) {
        return mh.getDisplay();
    }

    // MODIFIES: this
    // EFFECTS: Gets the user's win rate
    private double doCalculate(MatchHistory mh) {
        return mh.calculateWinRate();
    }

    private AgentType getType(String type) {
        if (type.equals("SOVA")) {
            this.agent = SOVA;
        } else if (type.equals("SAGE")) {
            this.agent = SAGE;
        } else if (type.equals("PHOENIX")) {
            this.agent = PHOENIX;
        } else if (type.equals("BRIMSTONE")) {
            this.agent = BRIMSTONE;
        } else {
            this.agent = JETT;
        }
        return agent;
    }

    private int doCalculateNumGamesPlayedAgent(MatchHistory mh, String type) {
        getType(type);
        return mh.calculateNumGamesPlayedAgent(agent);
    }

}


