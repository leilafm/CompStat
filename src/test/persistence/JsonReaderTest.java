package persistence;

import model.Player;
import model.Deck;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Source attribution: JsonSerializationDemo given by course instructors

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/notreal.json");
        try {
            Deck deck = reader.read();
            fail("IOException expected here");
        } catch (IOException e) {
            // test passes!
        }
    }

    @Test
    void testReaderEmptyDeck() {
        

        JsonReader reader = new JsonReader("./data/testReaderEmptyDeck.json");


        try {
            Deck deck = reader.read();
            assertEquals("My deck", deck.getName());
            assertEquals(0, deck.numPlayers());
            assertEquals(0, deck.numTeams());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDeck() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDeck.json");
        try {
            List<Integer> stats = new ArrayList<>();
            List<Player> bengalPlayer = new ArrayList<>();
            List<Player> ravensPlayer = new ArrayList<>();
            bengalPlayer.add(new Player("Joe Burrow", 25, "Des Moines, Iowa", "Quarterback", stats));
            ravensPlayer.add(new Player("Lamar Jackson", 29, "Pompano, FL", "Quarterback", stats));
            stats.add(25);
            Deck deck = reader.read();
            assertEquals("My deck", deck.getName());
            List<Player> players = deck.getPlayers();
            assertEquals(2, players.size());
            checkPlayer("Joe Burrow", 25, "Des Moines, Iowa", "Quarterback", stats, players.get(0));
            checkPlayer("Lamar Jackson", 29, "Pompano, FL", "Quarterback", stats, players.get(1));
            checkTeam("Cincinatti Bengals", bengalPlayer, deck.getTeams().get(0));
            checkTeam("Baltimore Ravens", ravensPlayer, deck.getTeams().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
