package persistence;

import model.Game;
import model.MatchHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static model.AgentType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MatchHistory mh = new MatchHistory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            MatchHistory mh = new MatchHistory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(mh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            mh = reader.read();
            assertEquals(0, mh.getNumGames());
            assertEquals(0, mh.getNumGamesWon());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MatchHistory mh = new MatchHistory();
            Game g1 = new Game("win", 13, 9, SAGE);
            Game g2 = new Game("lose", 4, 13, BRIMSTONE);
            mh.addGame(g1);
            mh.addGame(g2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(mh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            mh = reader.read();
            assertEquals(2, mh.getNumGames());
            assertEquals(1, mh.getNumGamesWon());
            checkGame("win", 13, 9, SAGE, g1);
            checkGame("lose", 4, 13, BRIMSTONE, g2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
