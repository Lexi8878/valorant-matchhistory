package persistence;

import model.Game;
import model.MatchHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static model.AgentType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// The following code is taken from the JsonWriterTest class in the JsonSerializationDemo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonWriterTest.java
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\3illegal:fileName.json");
            MatchHistory mh = new MatchHistory();
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriteMatchHistoryNoGames() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriteMatchHistoryNoGames.json");
            JsonReader reader = new JsonReader("./data/testWriteMatchHistoryNoGames.json");
            MatchHistory mh = new MatchHistory();
            writer.open();
            writer.write(mh);
            writer.close();
            mh = reader.read();

            assertEquals(0, mh.getNumGames());
            assertEquals(0, mh.getNumGamesWon());
        } catch (IOException e) {
            fail("Couldn't write to file");
        }
    }

    @Test
    void testWriteMatchHistoryHasGames() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriteMatchHistoryHasGames.json");
            JsonReader reader = new JsonReader("./data/testWriteMatchHistoryHasGames.json");
            MatchHistory mh = new MatchHistory();
            Game g1 = new Game("win", 13, 9, SAGE);
            Game g2 = new Game("lose", 4, 13, BRIMSTONE);
            mh.addGame(g1);
            mh.addGame(g2);
            writer.open();
            writer.write(mh);
            writer.close();
            mh = reader.read();

            assertEquals(2, mh.getNumGames());
            assertEquals(1, mh.getNumGamesWon());
            checkGame("win", 13, 9, SAGE, g1);
            checkGame("lose", 4, 13, BRIMSTONE, g2);

        } catch (IOException e) {
            fail("Couldn't write to file");
        }
    }
}
