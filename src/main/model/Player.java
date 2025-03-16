package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

//Remove team from player constructor, then in the UI just add player to team
// THIS IS IMPORTANT, START HERE

// A class representing an American football player with a name, hometown, age, team, position and list of statistics
public class Player implements Writable {
    private String name;
    private String hometown;
    private int age;
    private String position;
    private List<Integer> stats;

    //EFFECTS: constructs a player with a name, age, hometown, 3 stats, a team 
    // and a position -- makes new team if team does not exist
    public Player(String name, int age, String hometown, String position, List<Integer> stats) {
        this.name = name;
        this.age = age;
        this.hometown = hometown;
        this.position = position;
        this.stats = stats;
    }

    //EFFECTS: returns true if:
    // - player is QB and has more than 15 touchdowns
    // - player is back/wide receiver with more than 1000 yards
    // - player is OLINE and has sacks allowed <= 2
    // - player is DLINE and has sacks >= 9
    // - player is line backer or defensive back with interceptions >= 5
    // return false if:
    // - not elite
    // - no position set
    public boolean isEliteStatus(Player p) {
        String positionCheck = getPosition();

        if (positionCheck == "Quarterback") {
            if (stats.get(0) >= 15) {
                return true;
            }  
        } else if (positionCheck == "Running Back" || positionCheck == "Wide Receiver") {
            if (stats.get(0) >= 1000) {
                return true;
            } 
        } else if (positionCheck == "Offensive Line") {
            if (stats.get(0) <= 2) {
                return true;
            }
        } else if (positionCheck == "Defensive Line") {
            if (stats.get(0) >= 9) {
                return true;
            } 
        } else if (positionCheck == "Linebacker" || positionCheck == "Defensive Back") {
            if (stats.get(0) >= 5) {
                return true;
            }
        } 
        return false;
    }


    //MODIFIES: this
    //EFFECTS: adds the player's given position to the player profile
    public void setPosition(String position) {
        this.position = position;
    }

    //MODIFIES: this
    //EFFECTS: player stats are added to the player profile, specific set of stats entered dependent on 
    // player position
    public void setStats(int stat) {
        stats.add(stat);
    }

    //EFFECTS: returns player name
    public String getName() {
        return name;
    }

    public String getHometown() {
        return hometown;
    }

    //EFFECTS: returns player age
    public int getAge() {
        return age;
    }

    //EFFECTS: returns player position
    public String getPosition() {
        return position;
    }

    //EFFECTS: returns list of player stats
    public List<Integer> getStats() {
        return stats;
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("hometown", hometown);
        json.put("age", age);
        json.put("position", position);
        json.put("stats", stats);
        return json;
    }
    
}
