package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Represents a user's match history, that stores all the games a user has played
public class MatchHistory implements Writable {
    private List<Game> games;

    // EFFECTS: Constructs a match history with an empty list of games
    public MatchHistory() {
        this.games = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds a single game to the user's match history
    public void addGame(Game g) {
        this.games.add(g);
    }

    // getters
    // EFFECTS: Displays the details of the most recent game in the match history
    public List<String> getDisplay() {
        String history = null;
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

    // EFFECTS: Gets the number of games played in a match history
    public int getNumGames() {
        return this.games.size();
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

    // EFFECTS: Calculates how many times the user has played a certain agent
    public List<List<AgentType>> calculateNumGamesPlayedAgent() {
        List<List<AgentType>> typeListCollection = new ArrayList<>();
        AgentType type = null;
        for (Game g: games) {
            if (g.getAgent() != type) {
                List<AgentType> typeList = new ArrayList<>();
                type = g.getAgent();
                typeList.add(type);
                typeListCollection.add(typeList);
            } else {
                for (List<AgentType> lists: typeListCollection) {
                    for (AgentType at: lists) {
                        if (at == type) {
                            lists.add(type);
                        }
                    }
                }
            }
        }
        return typeListCollection;
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

    //EFFECTS: returns a list of matches in this currently in match history
    public List<String> getMatchesRunning() {
        List<String> matchesRunning = getDisplay();
        return matchesRunning;
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

    public double[] getWinsOnAgent() {
        double sovaCount = 0;
        double phoenixCount = 0;
        double brimstoneCount = 0;
        double jettCount = 0;
        double sageCount = 0;
        List<Double> doubleList = new ArrayList<>();

        for (Game g: games) {
            if (String.valueOf(g.getAgent()).equals("SOVA")) {
                sovaCount++;
            } else if (String.valueOf(g.getAgent()).equals("PHOENIX")) {
                phoenixCount++;
            } else if (String.valueOf(g.getAgent()).equals("BRIMSTONE")) {
                brimstoneCount++;
            } else if (String.valueOf(g.getAgent()).equals("JETT")) {
                jettCount++;
            } else {
                sageCount++;
            }
        }
        doubleList.add(sovaCount);
        doubleList.add(phoenixCount);
        doubleList.add(brimstoneCount);
        doubleList.add(jettCount);
        doubleList.add(sageCount);
        double[] array = doubleList.stream().mapToDouble(i -> i).toArray();
        return array;
    }

}
