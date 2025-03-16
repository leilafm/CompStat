package persistence;

import model.Player;
import model.Team;
import model.Deck;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Source attribution: JsonSerializationDemo given by course instructors 

// Represents a reader that reads Deck from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructor for a reader that reads from JSON source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads a Deck from file and returns it to application, throws IOException 
    // if an error occurs when reading data from the file
    public Deck read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDeck(jsonObject);
    }

    // EFFECTS: reads source file as string and returns file to application, throws IOException
    // if an error occurs when reading data from the file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Deck for JSON object and returns it to application
    private Deck parseDeck(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Deck deck = new Deck(name);
        addPlayers(deck, jsonObject);
        addTeams(deck, jsonObject);
        return deck;
    }

    // MODIFIES: Deck
    // EFFECTS: parses players from JSON object and adds them to Deck
    private void addPlayers(Deck deck, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(deck, nextPlayer);
        }
    }

    // MODIFIES: Deck
    //EFFECTS: parses player from JSON object and adds it to Deck
    private void addPlayer(Deck deck, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String hometown = jsonObject.getString("hometown");
        int age = jsonObject.getInt("age");
        String position = jsonObject.getString("position");
        List<Integer> stats = new ArrayList<Integer>();
        
        for (int i = 0; i < jsonObject.getJSONArray("stats").length(); i++) {
            stats.add((int)jsonObject.getJSONArray("stats").get(i));
        }
        
        Player player = new Player(name, age, hometown, position, stats);
        deck.addPlayer(player);
    }

    // MODIFIES: Deck
    // EFFECTS: parses teams from JSON object and adds them to Deck
    private void addTeams(Deck deck, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teams");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(deck, nextTeam);
        }
    }

    // MODIFIES: Deck
    //EFFECTS: parses teams from JSON object and adds it to Deck
    private void addTeam(Deck deck, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Team team = new Team(name);
    
        JSONArray playersArray = jsonObject.getJSONArray("players");
        for (Object json : playersArray) {
            JSONObject playerJson = (JSONObject) json;
        
            String playerName = playerJson.getString("name");
            String hometown = playerJson.getString("hometown");
            int age = playerJson.getInt("age");
            String position = playerJson.getString("position");
            List<Integer> stats = new ArrayList<Integer>();
        
            for (int i = 0; i < playerJson.getJSONArray("stats").length(); i++) {
                stats.add((int)playerJson.getJSONArray("stats").get(i));
            }
        
            Player player = new Player(playerName, age, hometown, position, stats);
            team.addPlayerToTeam(player);
        }

        deck.addTeam(team);
    }

}
