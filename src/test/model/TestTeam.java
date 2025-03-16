package model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTeam {
    Team testTeam;
    String testName;
    List<Player> testPlayers;
    Player testPlayer1;
    Player testPlayer2;

    @BeforeEach
    void runBefore() {
        List<Integer> stats = new ArrayList<Integer>();
        testTeam = new Team("Cincinatti Bengals");
        testPlayer1 = new Player("Tester1", 22, "Vancouver, BC", "Quarterback", stats);
        testPlayer2 = new Player("Tester2", 30, "Toronto, ON", "Quarterback", stats);
    }


    @Test
    void testTeamConstructor() {
        assertEquals("Cincinatti Bengals", testTeam.getName());

        testPlayers = testTeam.getPlayers();
        assertEquals(0, testPlayers.size());
    }

    @Test
    void testAddPlayerToTeamNotThere1() {

        testTeam.addPlayerToTeam(testPlayer1);
        List<Player> cbPlayers = testTeam.getPlayers();

        assertEquals(1, cbPlayers.size());
        assertEquals(testPlayer1, cbPlayers.get(0));
    }

    @Test
    void testAddPlayerToTeamNotThere2() {
        testTeam.addPlayerToTeam(testPlayer1);
        testTeam.addPlayerToTeam(testPlayer2);

        List<Player> cbPlayers = testTeam.getPlayers();

        assertEquals(2, cbPlayers.size());

        assertEquals(testPlayer1, cbPlayers.get(0));
        assertEquals(testPlayer2, cbPlayers.get(1));
    }

    @Test
    void testAddPlayerToTeamAlreadyThere() {
        testTeam.addPlayerToTeam(testPlayer1);
        testTeam.addPlayerToTeam(testPlayer1);

        List<Player> cbPlayers = testTeam.getPlayers();

        assertEquals(1, cbPlayers.size());
        assertEquals(testPlayer1, cbPlayers.get(0));
    }
}