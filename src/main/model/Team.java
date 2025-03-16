package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// import org.json.JSONObject;

//A class representing a American football team with a name and list of players on the team
public class Team implements Writable {
    String name;
    List<Player> players;

    //EFFECTS: creates new team with team name and empty list of players
    public Team(String name) {
        this.name = name;
        players = new ArrayList<Player>();
    }


    //MODIFIES: this
    //EFFECTS: adds given player to team, if player is already on the team: does nothing
    public void addPlayerToTeam(Player p) {
        if (!players.contains(p)) {
            players.add(p);
        }
    }

    //EFFECTS: returns list of Player on team
    public List<Player> getPlayers() {
        return players;
    }

    //EFFECTS: return name of team
    public String getName() {
        return name;
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);

        JSONArray playersArray = new org.json.JSONArray();
        for (Player player : players) {
            playersArray.put(player.toJson());
        }

        json.put("players", playersArray);
        return json;
    }
}
