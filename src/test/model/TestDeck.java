package model;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDeck {
    Deck testDeck;
    List<Player> testPlayers;
    List<Team> testTeams;
    List<Integer> stats;
    Team rams;
    Team eagles;

    @BeforeEach
    public void runBefore() {
        testDeck = new Deck("Tester");
        testPlayers = new ArrayList<Player>();
        testTeams = new ArrayList<Team>();
        stats = new ArrayList<>();
        rams = new Team("Los Angeles Rams");
        eagles = new Team("Philadelphia Eagles");
    }

    @Test
    public void testDeck() {
        assertEquals("Tester", testDeck.getName());
        assertEquals(0, testDeck.numPlayers());
        assertEquals(0, testDeck.numTeams());
    }

    @Test
    public void testAddPlayerAddedTwice() {
        Player testPlayer1 = new Player("Tester1", 22, "Vancouver, BC", "Quarterback", stats);
        testDeck.addPlayer(testPlayer1);
        testPlayers.add(testPlayer1);

        assertEquals(testPlayers, testDeck.getPlayers());

        testDeck.addPlayer(testPlayer1);

        assertEquals(testPlayers, testDeck.getPlayers());
    }

    @Test
    public void testAddPlayer2Diff() {
        Player testPlayer1 = new Player("Tester1", 22, "Vancouver, BC", "Quarterback", stats);
        Player testPlayer2 = new Player("Tester2", 30, "Toronto, ON", "Quarterback", stats);
        testDeck.addPlayer(testPlayer1);
        testPlayers.add(testPlayer1);

        assertEquals(testPlayers, testDeck.getPlayers());

        testDeck.addPlayer(testPlayer2);
        testPlayers.add(testPlayer2);

        assertEquals(testPlayers, testDeck.getPlayers());
    }

    @Test
    public void testAddTeam() {
        testDeck.addTeam(rams);
        testTeams.add(rams);

        assertEquals(testTeams, testDeck.getTeams());

        testDeck.addTeam(rams);

        assertEquals(testTeams, testDeck.getTeams());

        testDeck.addTeam(eagles);
        testTeams.add(eagles);

        assertEquals(testTeams, testDeck.getTeams());
    }
    
}