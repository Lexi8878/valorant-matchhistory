package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatchHistoryTest {
    MatchHistory mh1;
    MatchHistory mh2;
    Game g;
    Game g2;

    @BeforeEach
    void runBefore() {
        mh1 = new MatchHistory();
        mh2 = new MatchHistory();
        g = new Game("win", 13, 0);
        g2 = new Game("lose", 5, 13);
    }

    @Test
    void testMatchHistory() {
        assertEquals(0, mh1.getNumGames());
        assertEquals(0, mh2.getNumGames());
    }

    @Test
    void testAddGame() {
        mh1.addGame(g);
        assertEquals(1, mh1.getNumGames());
        assertEquals(1, mh1.getNumGamesWon());

        mh2.addGame(g);
        mh2.addGame(g2);
        assertEquals(2, mh2.getNumGames());
        assertEquals(1, mh2.getNumGamesWon());
    }

    @Test
    void testGetDisplay() {
        mh1.addGame(g);
        assertEquals("WIN 13-0", mh1.getDisplay());
    }

    @Test
    void testCalculateWinRate() {
        mh1.addGame(g);
        assertEquals(100, mh1.calculateWinRate());

        mh2.addGame(g);
        mh2.addGame(g2);
        assertEquals(50, mh2.calculateWinRate());
    }
}
