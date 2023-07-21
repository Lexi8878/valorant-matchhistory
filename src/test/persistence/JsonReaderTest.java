package persistence;

import static model.AgentType.*;
import model.Game;
import model.MatchHistory;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        try {
            JsonReader reader = new JsonReader("./data/nonExistentFile.json");
            MatchHistory mh = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReadMatchHistoryNoGames() {
        try {
            MatchHistory mh = new MatchHistory();
            JsonReader reader = new JsonReader("./data/testReadMatchHistoryNoGames.json");
            JsonWriter writer = new JsonWriter("./data/testReadMatchHistoryNoGames.json");
            writer.open();
            writer.write(mh);
            writer.close();
            mh = reader.read();
            assertEquals(0, mh.getNumGames());
            assertEquals(0, mh.getNumGamesWon());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadMatchHistoryHasGames() {
        try {
            JsonReader reader = new JsonReader("./data/testReadMatchHistoryHasGames.json");
            JsonWriter writer = new JsonWriter("./data/testReadMatchHistoryHasGames.json");
            MatchHistory mh = new MatchHistory();
            Game g = new Game("win", 13, 7, SOVA);
            Game g2 = new Game("lose", 8, 13, PHOENIX);
            assertEquals(0, mh.getNumGames());
            mh.addGame(g);
            mh.addGame(g2);
            writer.open();
            writer.write(mh);
            writer.close();
            mh = reader.read();
            checkGame("win", 13, 7, SOVA, g);
            checkGame("lose", 8, 13, PHOENIX, g2);
            assertEquals(2, mh.getNumGames());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
