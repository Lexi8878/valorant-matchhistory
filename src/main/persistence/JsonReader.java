package persistence;

import model.AgentType;
import model.Game;
import model.MatchHistory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads MatchHistory from JSON data stored in file
// The following code is taken from the JsonReader class in the JsonSerializationDemo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonReader.java
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads match history from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MatchHistory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMatchHistory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses match history from JSON object and returns it
    private MatchHistory parseMatchHistory(JSONObject jsonObject) {
        MatchHistory mh = new MatchHistory();
        addGames(mh, jsonObject);
        return mh;
    }

    // MODIFIES: mh
    // EFFECTS: parses matches from JSON object and adds them to match history
    private void addGames(MatchHistory mh, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("games");
        for (Object json : jsonArray) {
            JSONObject nextGame = (JSONObject) json;
            addGame(mh, nextGame);
        }
    }

    // MODIFIES: mh
    // EFFECTS: parses match from JSON object and adds it to match history
    private void addGame(MatchHistory mh, JSONObject jsonObject) {
        String status = jsonObject.getString("status");
        String teamPoints = jsonObject.getString("teamPoints");
        String enemyPoints = jsonObject.getString("enemyPoints");
        AgentType agent = AgentType.valueOf(jsonObject.getString("agent"));
        Game g = new Game(status, teamPoints, enemyPoints, agent);
        mh.addGame(g);
    }
}
