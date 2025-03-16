package ui;


import model.Player;
import model.Team;
import model.Deck;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Source attribution: JsonSerializationDemo given by course instructors

//Runs the CompareStat application
public class CompareStatApp {
    private Scanner input;

    private static final String JSON_STORE = "./data/deck.json";
    private Deck deck;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    
    //EFFECTS: runs the compare stat application
    public CompareStatApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        deck = new Deck("Leila's deck");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runCompareStat();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runCompareStat() {
        boolean stillUsing = true;
        String command = null;

        makeTeamAndPlayerList();

        while (stillUsing) {
            mainMenu();
            command = input.next();

            if (command.equals("q")) {
                stillUsing = false;
            } else {
                commandCenter(command);
            }
        }
        System.out.println("\n Bye, see you soon!");
    }

    //MODIFIES: TeamList, PlayerList
    //EFFECTS: creates allTeam list and allPlayers list in TeamList and PlayerList object
    private void makeTeamAndPlayerList() {
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    //MODIFIES: this
    //EFFECTS: processes the user commands
    private void commandCenter(String command) {
        if (command.equals("p")) {
            doAddPlayer();
        } else if (command.equals("t")) {
            printTeams();
        } else if (command.equals("e")) {
            printTeamPlayers();
        } else if (command.equals("a")) {
            printPlayers();
        } else if (command.equals("s")) {
            saveDeck();
        } else if (command.equals("l")) {
            loadDeck();
        }
    }

    //EFFECTS: displays menu of user option
    private void mainMenu() {
        System.out.println("\n Select your choice:");
        System.out.println("\tp -> Add player");
        System.out.println("\tt -> View all teams");
        System.out.println("\te -> View all players on a team");
        System.out.println("\ta -> View all players");
        System.out.println("\ts -> Save players added to file");
        System.out.println("\tl -> Load players added from file");
        System.out.println("\tq -> Quit app");
    }


    //MODIFIES: this
    //EFFECTS: adds player to app
    private void doAddPlayer() {
        System.out.println("\n Player name: ");
        String name = input.next();
        System.out.println("\n Age: ");
        int age = Integer.parseInt(input.next());
        System.out.println("\n Hometown: ");
        String hometown = input.next();
        System.out.println("\nPosition: ");
        String position = input.next();
        System.out.println("\nTeam: ");
        Team team = new Team(input.next());
        List<Integer> listOfStats = new ArrayList<Integer>();
        Player p = new Player(name, age, hometown, position, listOfStats);
        deck.addPlayer(p);
        deck.addTeam(team);
        team.addPlayerToTeam(p);
    }

    //EFFECTS: shows list of all teams
    private void printTeams() {
        for (Team t : deck.getTeams()) {
            System.out.println(t.getName());
        }
    }

    //EFFECTS: shows list of players inputted so far
    private void printPlayers() {
        for (Player p : deck.getPlayers()) {
            System.out.println(p.getName());
        }
    }

    //EFFECTS: shows list of players on given team
    private void printTeamPlayers() {
        System.out.println("\nWhich team? ");
        String teamWanted = input.next();
        Boolean keepLooking = true;
        for (Team t : deck.getTeams()) {
            if (teamWanted.equals(t.getName())) {
                List<Player> players = t.getPlayers();
                for (Player p : players) {
                    System.out.println(p.getName());
                    keepLooking = false;
                    break;
                }
                break; 
            } 
            if (keepLooking) {
                System.out.println("Team not found");
            }
        }
    }

    // EFFECTS: saves deck to file
    private void saveDeck() {
        try {
            jsonWriter.open();
            jsonWriter.write(deck);
            jsonWriter.close();
            System.out.print("Saved " + deck.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads deck from file
    private void loadDeck() {
        try {
            deck = jsonReader.read();
            System.out.println("Loaded " + deck.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

