package model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    Player testPlayer;
    String team;
    List<Integer> stats;

    @BeforeEach
    void runBefore() {
        stats = new ArrayList<Integer>();
        testPlayer = new Player("Joe Burrow", 28, "Ames, Iowa", "Quarterback", stats);
    }

    @Test
    void testPlayer1() {
        assertEquals("Joe Burrow", testPlayer.getName());
        assertEquals(28, testPlayer.getAge());
        assertEquals("Ames, Iowa", testPlayer.getHometown());
        assertEquals("Quarterback", testPlayer.getPosition());

        List<Integer> statistics = testPlayer.getStats();
        assertEquals(0, statistics.size());
    }

    @Test
    void testIsEliteStatusQbAbove() {
        testPlayer.setPosition("Quarterback");

        testPlayer.setStats(20);
        testPlayer.setStats(0);
        testPlayer.setStats(500);

        assertTrue(testPlayer.isEliteStatus(testPlayer));
        
        List<Integer> statistics = testPlayer.getStats();
        assertEquals(20, statistics.get(0));
        assertEquals(0, statistics.get(1));
        assertEquals(500, statistics.get(2));
        assertEquals("Quarterback", testPlayer.getPosition());
    }

    @Test
    void testIsEliteStatusQbEqual() {
        testPlayer.setPosition("Quarterback");

        testPlayer.setStats(15);
        testPlayer.setStats(0);
        testPlayer.setStats(500);

        assertTrue(testPlayer.isEliteStatus(testPlayer));
        
        List<Integer> statistics = testPlayer.getStats();
        assertEquals(15, statistics.get(0));
        assertEquals(0, statistics.get(1));
        assertEquals(500, statistics.get(2));

        assertEquals("Quarterback", testPlayer.getPosition());
    }

    @Test
    void testIsEliteStatusQbBelow() {
        testPlayer.setPosition("Quarterback");

        testPlayer.setStats(14);
        testPlayer.setStats(0);
        testPlayer.setStats(500);

        assertFalse(testPlayer.isEliteStatus(testPlayer));
        
        List<Integer> statistics = testPlayer.getStats();
        assertEquals(14, statistics.get(0));
        assertEquals(0, statistics.get(1));
        assertEquals(500, statistics.get(2));

        assertEquals("Quarterback", testPlayer.getPosition());
    }

    @Test
    void testIsEliteWrRbAbove() {
        Player rbPlayer = new Player("Saquon", 25, "New York, NY", "Running Back", stats);

        rbPlayer.setPosition("Running Back");

        rbPlayer.setStats(1050);
        rbPlayer.setStats(50);
        rbPlayer.setStats(100);

        assertTrue(rbPlayer.isEliteStatus(rbPlayer));

        List<Integer> statistics = rbPlayer.getStats();
        assertEquals(1050, statistics.get(0));
        assertEquals(50, statistics.get(1));
        assertEquals(100, statistics.get(2));

        assertEquals("Running Back", rbPlayer.getPosition());
    }

    @Test
    void testIsEliteWrRbEqual() {
        Player rbPlayer = new Player("Saquon", 25, "New York, NY", "Running Back", stats);
        rbPlayer.setPosition("Running Back");

        rbPlayer.setStats(1000);
        rbPlayer.setStats(50);
        rbPlayer.setStats(100);

        assertTrue(rbPlayer.isEliteStatus(rbPlayer));

        List<Integer> statistics = rbPlayer.getStats();
        assertEquals(1000, statistics.get(0));
        assertEquals(50, statistics.get(1));
        assertEquals(100, statistics.get(2));

        assertEquals("Running Back", rbPlayer.getPosition());
    }

    @Test
    void testIsEliteStatusRbBelow() {
        Player rbPlayer = new Player("Saquon", 25, "New York, NY", "Running Back", stats);

        rbPlayer.setPosition("Running Back");

        rbPlayer.setStats(990);
        rbPlayer.setStats(50);
        rbPlayer.setStats(100);

        assertFalse(rbPlayer.isEliteStatus(rbPlayer));

        List<Integer> statistics = rbPlayer.getStats();
        assertEquals(990, statistics.get(0));
        assertEquals(50, statistics.get(1));
        assertEquals(100, statistics.get(2));

        assertEquals("Running Back", rbPlayer.getPosition());
    }

    @Test
    void testIsEliteWrAbove() {
        Player rbPlayer = new Player("Saquon", 25, "New York, NY", "Running Back", stats);

        rbPlayer.setPosition("Wide Receiver");

        rbPlayer.setStats(1050);
        rbPlayer.setStats(50);
        rbPlayer.setStats(100);

        assertTrue(rbPlayer.isEliteStatus(rbPlayer));

        List<Integer> statistics = rbPlayer.getStats();
        assertEquals(1050, statistics.get(0));
        assertEquals(50, statistics.get(1));
        assertEquals(100, statistics.get(2));

        assertEquals("Wide Receiver", rbPlayer.getPosition());
    }

    @Test
    void isEliteStatusOLBelow() {
        Player olPlayer = new Player("Cam J", 30, "Philadelphia, PA", "Offensive Line", stats);

        olPlayer.setPosition("Offensive Line");

        olPlayer.setStats(1);
        olPlayer.setStats(0);

        assertTrue(olPlayer.isEliteStatus(olPlayer));

        List<Integer> statistics = olPlayer.getStats();
        assertEquals(1, statistics.get(0));
        assertEquals(0, statistics.get(1));

        assertEquals("Offensive Line", olPlayer.getPosition());
    }

    @Test
    void isEliteStatusOLEqual() {
        Player olPlayer = new Player("Cam J", 30, "Philadelphia, PA", "Offensive Line", stats);

        olPlayer.setPosition("Offensive Line");

        olPlayer.setStats(2);
        olPlayer.setStats(1);

        assertTrue(olPlayer.isEliteStatus(olPlayer));

        List<Integer> statistics = olPlayer.getStats();
        assertEquals(2, statistics.get(0));
        assertEquals(1, statistics.get(1));

        assertEquals("Offensive Line", olPlayer.getPosition());
    }

    @Test
    void isEliteStatusOLAbove() {
        Player olPlayer = new Player("Cam J", 30, "Philadelphia, PA", "Offensive Line", stats);

        olPlayer.setPosition("Offensive Line");

        olPlayer.setStats(4);
        olPlayer.setStats(2);

        assertFalse(olPlayer.isEliteStatus(olPlayer));

        List<Integer> statistics = olPlayer.getStats();
        assertEquals(4, statistics.get(0));
        assertEquals(2, statistics.get(1));

        assertEquals("Offensive Line", olPlayer.getPosition());
    }

    @Test
    void isEliteStatusDLAbove() {
        Player dlPlayer = new Player("Jalen Carter", 22, "Philadelphia, PA", "Defensive Line", stats);

        dlPlayer.setPosition("Defensive Line");

        dlPlayer.setStats(10);
        dlPlayer.setStats(2);
        dlPlayer.setStats(10);

        assertTrue(dlPlayer.isEliteStatus(dlPlayer));

        List<Integer> statistics = dlPlayer.getStats();
        assertEquals(10, statistics.get(0));
        assertEquals(2, statistics.get(1));
        assertEquals(10, statistics.get(2));

        assertEquals("Defensive Line", dlPlayer.getPosition());
    }

    @Test
    void isEliteStatusDLEqual() {
        Player dlPlayer = new Player("Jalen Carter", 22, "Philadelphia, PA", "Defensive Line", stats);
        dlPlayer.setPosition("Defensive Line");

        dlPlayer.setStats(9);
        dlPlayer.setStats(2);
        dlPlayer.setStats(10);

        assertTrue(dlPlayer.isEliteStatus(dlPlayer));

        List<Integer> statistics = dlPlayer.getStats();
        assertEquals(9, statistics.get(0));
        assertEquals(2, statistics.get(1));
        assertEquals(10, statistics.get(2));

        assertEquals("Defensive Line", dlPlayer.getPosition());
    }

    @Test
    void isEliteStatusDLBelow() {
        Player dlPlayer = new Player("Jalen Carter", 22, "Philadelphia, PA", "Defensive Line", stats);

        dlPlayer.setPosition("Defensive Line");

        dlPlayer.setStats(5);
        dlPlayer.setStats(2);
        dlPlayer.setStats(10);

        assertFalse(dlPlayer.isEliteStatus(dlPlayer));

        List<Integer> statistics = dlPlayer.getStats();
        assertEquals(5, statistics.get(0));
        assertEquals(2, statistics.get(1));
        assertEquals(10, statistics.get(2));

        assertEquals("Defensive Line", dlPlayer.getPosition());
    }

    @Test
    void isEliteStatusDbAbove() {
        Player lbPlayer = new Player("Frankie Luvu", 25, "Honolulu, HI", "Defensive Back", stats);

        lbPlayer.setPosition("Defensive Back");

        lbPlayer.setStats(7);
        lbPlayer.setStats(20);
        lbPlayer.setStats(2);

        assertTrue(lbPlayer.isEliteStatus(lbPlayer));

        List<Integer> statistics = lbPlayer.getStats();
        assertEquals(7, statistics.get(0));
        assertEquals(20, statistics.get(1));
        assertEquals(2, statistics.get(2));

        assertEquals("Defensive Back", lbPlayer.getPosition());
    }

    @Test
    void isEliteStatusDbBelow() {
        Player lbPlayer = new Player("Frankie Luvu", 25, "Honolulu, HI", "Defensive Back", stats);
        lbPlayer.setPosition("Defensive Back");

        lbPlayer.setStats(1);
        lbPlayer.setStats(20);
        lbPlayer.setStats(2);

        assertFalse(lbPlayer.isEliteStatus(lbPlayer));

        List<Integer> statistics = lbPlayer.getStats();
        assertEquals(1, statistics.get(0));
        assertEquals(20, statistics.get(1));
        assertEquals(2, statistics.get(2));

        assertEquals("Defensive Back", lbPlayer.getPosition());
    }

    @Test
    void isEliteStatusDbEqual() {
        Player lbPlayer = new Player("Frankie Luvu", 25, "Honolulu, HI", "Defensive Back", stats);
        lbPlayer.setPosition("Defensive Back");

        lbPlayer.setStats(5);
        lbPlayer.setStats(20);
        lbPlayer.setStats(2);

        assertTrue(lbPlayer.isEliteStatus(lbPlayer));

        List<Integer> statistics = lbPlayer.getStats();
        assertEquals(5, statistics.get(0));
        assertEquals(20, statistics.get(1));
        assertEquals(2, statistics.get(2));

        assertEquals("Defensive Back", lbPlayer.getPosition());
    }

    @Test
    void isEliteStatusLbDbAbove() {
        Player lbPlayer = new Player("Frankie Luvu", 25, "Honolulu, HI", "Linebacker", stats);

        lbPlayer.setPosition("Linebacker");

        lbPlayer.setStats(7);
        lbPlayer.setStats(20);
        lbPlayer.setStats(2);

        assertTrue(lbPlayer.isEliteStatus(lbPlayer));

        List<Integer> statistics = lbPlayer.getStats();
        assertEquals(7, statistics.get(0));
        assertEquals(20, statistics.get(1));
        assertEquals(2, statistics.get(2));

        assertEquals("Linebacker", lbPlayer.getPosition());
    }

    @Test
    void isEliteStatusLbDbEqual() {
        Player lbPlayer = new Player("Frankie Luvu", 25, "Honolulu, HI", "Linebacker", stats);

        lbPlayer.setPosition("Linebacker");

        lbPlayer.setStats(5);
        lbPlayer.setStats(20);
        lbPlayer.setStats(2);

        assertTrue(lbPlayer.isEliteStatus(lbPlayer));

        List<Integer> statistics = lbPlayer.getStats();
        assertEquals(5, statistics.get(0));
        assertEquals(20, statistics.get(1));
        assertEquals(2, statistics.get(2));

        assertEquals("Linebacker", lbPlayer.getPosition());
    }

    @Test
    void isEliteStatusLbDbLower() {
        Player lbPlayer = new Player("Frankie Luvu", 25, "Honolulu, HI", "Linebacker", stats);

        lbPlayer.setPosition("Linebacker");

        lbPlayer.setStats(2);
        lbPlayer.setStats(20);
        lbPlayer.setStats(2);

        assertFalse(lbPlayer.isEliteStatus(lbPlayer));

        List<Integer> statistics = lbPlayer.getStats();
        assertEquals(2, statistics.get(0));
        assertEquals(20, statistics.get(1));
        assertEquals(2, statistics.get(2));

        assertEquals("Linebacker", lbPlayer.getPosition());
    }

}