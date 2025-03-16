package persistence;

import org.json.JSONObject; 

// Source attribution: JsonSerializationDemo given by course instructors

public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}

