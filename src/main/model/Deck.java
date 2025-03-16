package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Deck implements Writable {
    private String name;
    private List<Player> players;
    private List<Team> teams;
    
    // EFFECTS: constructs deck with name and empty list of players
    public Deck(String name) {
        this.name = name;
        players = new ArrayList<>();
        teams = new ArrayList<>();
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds player to this deck, if player does not already exist
    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    // EFFECTS: returns a list of players in this deck
    public List<Player> getPlayers() {
        return players;
    }

    // MODIFIES: this
    // EFFECTS: adds team to this deck, if team does not already exist
    public void addTeam(Team team) {
        if (!teams.contains(team)) {
            teams.add(team);
        }
    }

    // EFFECTS: returns list of teams
    public List<Team> getTeams() {
        return teams;
    }

    // EFFECTS: returns number of players in this deck
    public int numPlayers() {
        return players.size();
    }

    //EFFECTS: returns number of teams in this deck
    public int numTeams() {
        return teams.size();
    }

    // EFFECTS: returns this as a JSON object
    @Override 
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("players", playersToJson());
        json.put("teams", teamsToJson());
        return json;
    }

    // EFFECTS: returns players in this deck as a JSON array'
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : players) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    //EFFECTS: returns teams in this deck as a JSON array
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t : teams) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
