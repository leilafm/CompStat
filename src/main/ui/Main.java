package ui;

import java.io.FileNotFoundException;

// Source attribution: JsonSerializationDemo given by course instructors

public class Main {
    public static void main(String[] args) {
        try {
            new CompareStatApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
