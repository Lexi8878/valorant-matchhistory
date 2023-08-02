package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a single game/match a user has played. It has a status (win/lose),
// the amount of team/enemy points at the end of the match, and the name of the character played
public class Game implements Writable {
    private AgentType agent;
    private String status;
    private String teamPoints;
    private String enemyPoints;

    // REQUIRES: Sum of team and enemy points <= 25 (13 + 12) and the winning team must score 13
    //           status must be either "win" or "lose"
    // EFFECTS: Constructs a game with a win or lose status, the final score of game, and the agent played
    public Game(String status, String teamPoints, String enemyPoints, AgentType agent) {
        this.status = status;
        this.teamPoints = teamPoints;
        this.enemyPoints = enemyPoints;
        this.agent = agent;
    }

    // EFFECTS: Returns true if game was won, returns false otherwise
    public boolean wasGameWon() {
        return status.equals("win");
    }

    // getters
    // EFFECTS: Gets the game's status (did user win or lose the game)
    public String getStatus() {
        return status;
    }

    // EFFECTS: Gets user's team score at the end of a game
    public String getTeamPoints() {
        return teamPoints;
    }

    // EFFECTS: Gets the enemy team's score at the end of a game
    public String getEnemyPoints() {
        return enemyPoints;
    }

    // EFFECTS: Gets the agent that was played in a game
    public AgentType getAgent() {
        return agent;
    }

    // EFFECTS: returns game details as JSON objects
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("teamPoints", teamPoints);
        json.put("enemyPoints", enemyPoints);
        json.put("agent", agent);
        return json;
    }
}
