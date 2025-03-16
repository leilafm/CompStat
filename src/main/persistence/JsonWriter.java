package persistence;

import model.Deck;
import org.json.JSONObject;


import java.io.*;

// Source attribution: JsonSerializationDemo given by course instructors

// Represents a writer that writes JSON representation of a Deck to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructor for writer to write to dest file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens json writer, throws FileNotFoundException if the destination file
    // cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of the Deck file
    public void write(Deck wr) {
        JSONObject json = wr.toJson();

        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: saves the Deck to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}
