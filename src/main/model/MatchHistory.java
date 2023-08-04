package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a user's match history, that stores all the games a user has played
public class MatchHistory implements Writable {
    private List<Game> games;
    private double sovaCount;
    private double phoenixCount;
    private double brimstoneCount;
    private double jettCount;
    private double sageCount;

    // EFFECTS: Constructs a match history with an empty list of games
    public MatchHistory() {
        this.games = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds a single game to the user's match history
    public void addGame(Game g) {
        this.games.add(g);
    }

    // EFFECTS: Displays the details of the most recent game in the match history
    public List<String> getDisplay() {
        String history;
        List<String> historyList = new ArrayList<>();
        for (Game g: games) {
            String s = g.getStatus();
            String tp = g.getTeamPoints();
            String ep = g.getEnemyPoints();
            String a = String.valueOf(g.getAgent());
            history = s.toUpperCase() + " " + tp + "-" + ep + " as " + a;
            historyList.add(history);
        }
        return historyList;
    }

    // EFFECTS: Gets the number of games won in a match history
    public int getNumGamesWon() {
        int numGamesWon = 0;
        for (Game g: games) {
            if (g.wasGameWon()) {
                numGamesWon++;
            }
        }
        return numGamesWon;
    }

    // EFFECTS: Calculates user's average win rate based off of the games that have been
    //          added into match history
    public double calculateWinRate() {
        double numG = getNumGames();
        double numGW = getNumGamesWon();
        if (numG == 0) {
            return 0;
        } else {
            return (numGW / numG) * 100;
        }
    }

    // EFFECTS: Gets the number of games played in a match history
    public int getNumGames() {
        return this.games.size();
    }

    // EFFECTS: returns games in match history as JSON objects
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("games", gamesToJson());
        return json;
    }

    // EFFECTS: returns games in match history as a JSON array
    private JSONArray gamesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Game g : games) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: returns a list of matches currently in match history
    public List<String> getMatchesRunning() {
        return getDisplay();
    }

    //EFFECTS: returns a String list of matches currently in match history
    public String matchRunningStatus() {
        List<String> running = getMatchesRunning();
        StringBuilder status = new StringBuilder();

        for (String s : running) {
            status.append("\n").append(s);
        }
        return status.toString();
    }

    // EFFECTS: iterates through the games list and adds 1 to the correct count
    public void getCounts() {
        for (Game g: games) {
            switch (String.valueOf(g.getAgent())) {
                case "SOVA":
                    sovaCount++;
                    break;
                case "PHOENIX":
                    phoenixCount++;
                    break;
                case "BRIMSTONE":
                    brimstoneCount++;
                    break;
                case "SAGE":
                    sageCount++;
                    break;
                default:
                    jettCount++;
                    break;
            }
        }
    }

    // EFFECTS: adds the num of games played on each champ into a list and returns it
    public double[] getGamesOnAgent() {
        sovaCount = 0;
        phoenixCount = 0;
        brimstoneCount = 0;
        jettCount = 0;
        sageCount = 0;
        List<Double> doubleList = new ArrayList<>();

        getCounts();

        doubleList.add(sovaCount);
        doubleList.add(phoenixCount);
        doubleList.add(brimstoneCount);
        doubleList.add(sageCount);
        doubleList.add(jettCount);
        return doubleList.stream().mapToDouble(i -> i).toArray();
    }
}
