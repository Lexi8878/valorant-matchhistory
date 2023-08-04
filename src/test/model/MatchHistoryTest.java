package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

import static model.AgentType.*;

public class MatchHistoryTest {
    MatchHistory mh1;
    MatchHistory mh2;
    MatchHistory mh3;
    MatchHistory mh4;
    MatchHistory mh5;
    Game g;
    Game g2;
    Game g3;
    Game g4;

    @BeforeEach
    void runBefore() {
        mh1 = new MatchHistory();
        mh2 = new MatchHistory();
        mh3 = new MatchHistory();
        mh4 = new MatchHistory();
        mh5 = new MatchHistory();
        g = new Game("win", "13", "0", SOVA);
        g2 = new Game("lose", "5", "13", SOVA);
        g3 = new Game("win", "13", "12", JETT);
        g4 = new Game("lose", "9", "13", SOVA);
    }

    @Test
    void testMatchHistory() {
        assertEquals(0, mh1.getNumGames());
        assertEquals(0, mh2.getNumGames());
        assertEquals(0, mh3.getNumGames());
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
        mh2.addGame(g3);
        assertEquals(3, mh2.getNumGames());

        assertEquals(0, mh3.getNumGames());
    }

    @Test
    void testGetDisplay() {
        mh1.addGame(g);
        List<String> testHistoryList = mh1.getDisplay();
        assertEquals("WIN 13-0 as SOVA", testHistoryList.get(0));
        assertEquals(1, testHistoryList.size());

        mh2.addGame(g);
        mh2.addGame(g2);
        List<String> testHistoryList2 = mh2.getDisplay();
        assertEquals("WIN 13-0 as SOVA", testHistoryList2.get(0));
        assertEquals("LOSE 5-13 as SOVA", testHistoryList2.get(1));
        mh2.addGame(g3);
        List<String> testHistoryList3 = mh2.getDisplay();
        assertEquals("WIN 13-12 as JETT", testHistoryList3.get(2));
        assertEquals(3, testHistoryList3.size());

        List<String> testHistoryList4 = mh3.getDisplay();
        assertEquals(0, testHistoryList4.size());
    }

    @Test
    void testGetNumGamesWon() {
        mh1.addGame(g);
        assertEquals(1, mh1.getNumGamesWon());

        mh2.addGame(g);
        mh2.addGame(g2);
        mh2.addGame(g3);
        assertEquals(2, mh2.getNumGamesWon());

        assertEquals(0, mh3.getNumGamesWon());

        mh4.addGame(g2);
        mh4.addGame(g4);
        assertEquals(0, mh4.getNumGamesWon());
    }

    @Test
    void testCalculateWinRate() {
        mh1.addGame(g);
        assertEquals(100, mh1.calculateWinRate());

        mh2.addGame(g);
        mh2.addGame(g2);
        assertEquals(50, mh2.calculateWinRate());

        mh3.addGame(g);
        mh3.addGame(g2);
        mh3.addGame(g3);
        assertEquals(66.66666666666666, mh3.calculateWinRate());

        mh4.addGame(g2);
        assertEquals(0, mh4.calculateWinRate());

        assertEquals(0, mh5.calculateWinRate());
    }

    @Test
    void testGetMatchesRunning() {
        mh1.addGame(g);
        mh1.addGame(g2);
        mh1.addGame(g3);
        List<String> testList = mh1.getMatchesRunning();
        assertEquals(3, testList.size());
        assertEquals("WIN 13-0 as SOVA", testList.get(0));
        assertEquals("LOSE 5-13 as SOVA", testList.get(1));
        assertEquals("WIN 13-12 as JETT", testList.get(2));
    }

    @Test
    void testMatchRunningStatus() {
        mh1.addGame(g);
        mh1.addGame(g2);
        mh1.addGame(g3);
        assertEquals("\nWIN 13-0 as SOVA\nLOSE 5-13 as SOVA\nWIN 13-12 as JETT", mh1.matchRunningStatus());
    }

    @Test
    void testGetGamesOnAgent() {
        mh1.addGame(g);
        mh1.addGame(g2);
        mh1.addGame(g3);
        mh1.addGame(new Game("win", "13", "0", PHOENIX));
        mh1.addGame(new Game("win", "13", "0", SAGE));
        mh1.addGame(new Game("win", "13", "0", BRIMSTONE));

        List<Double> testDouble = Arrays.stream(mh1.getGamesOnAgent()).boxed().collect(Collectors.toList());
        assertEquals( 5,testDouble.size());
        assertEquals( 2,testDouble.get(0));
        assertEquals( 1,testDouble.get(1));
        assertEquals( 1,testDouble.get(2));
        assertEquals( 1,testDouble.get(3));
        assertEquals( 1,testDouble.get(4));
    }
}
