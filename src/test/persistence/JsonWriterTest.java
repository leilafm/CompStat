package persistence;

import model.Player;
import model.Team;
import model.Deck;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Source attribution: JsonSerializationDemo given by course instructors

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Deck deck = new Deck("My deck");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // test passes!
        }
    }

    @Test
    void testWriterEmptyDeck() {
        try {
            Deck deck = new Deck("My deck");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDeck.json");
            writer.open();
            writer.write(deck);
            writer.close();
    

            JsonReader reader = new JsonReader("./data/testWriterEmptyDeck.json");
            deck = reader.read();
            assertEquals("My deck", deck.getName());
            assertEquals(0, deck.numPlayers());
            assertEquals(0, deck.numTeams());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDeckPlayers() {
        try {
            List<Integer> stats = new ArrayList<>();
            Deck deck = new Deck("My deck");
            deck.addPlayer(new Player("Joe Burrow", 25, "Des Moines, Iowa", "Quarterback", stats)); 
            deck.addPlayer(new Player("Lamar Jackson", 29, "Pompano, FL", "Quarterback", stats));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDeck.json");
            writer.open();
            writer.write(deck);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDeck.json");
            deck = reader.read();
            assertEquals("My deck", deck.getName());
            List<Player> players = deck.getPlayers();
            assertEquals(2, players.size());
            checkPlayer("Joe Burrow", 25, "Des Moines, Iowa", "Quarterback", stats, players.get(0));
            checkPlayer("Lamar Jackson", 29, "Pompano, FL", "Quarterback", stats, players.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    //start here
    @Test
    void testWriterGeneralDeckTeams() {
        try {
            List<Integer> stats = new ArrayList<>();
            Deck deck = new Deck("My deck");
            Team ravens = new Team("Baltimore Ravens");
            ravens.addPlayerToTeam(new Player("Lamar Jackson", 29, "Pompano, FL", "Quarterback", stats));
            ravens.addPlayerToTeam(new Player("Mark Andrews", 28, "Scottsdale, AZ", "Tight End", stats));
            deck.addTeam(ravens);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDeckTeams.json");
            writer.open();
            writer.write(deck);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralDeckTeams.json");
            deck = reader.read();
            assertEquals("My deck", deck.getName());
            assertEquals(1, deck.getTeams().size());
            List<Player> expectedRavensPlayers = new ArrayList<>();
            expectedRavensPlayers.add(new Player("Lamar Jackson", 29, "Pompano, FL", "Quarterback", stats));  
            expectedRavensPlayers.add(new Player("Mark Andrews", 28, "Scottsdale, AZ", "Tight End", stats));
            checkTeam("Baltimore Ravens", expectedRavensPlayers, deck.getTeams().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
