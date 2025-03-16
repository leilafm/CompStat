package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

// Source attribution: JsonSerializationDemo given by course instructors

public class JsonTest {
    protected void checkPlayer(String name, int age, String hometown, String position, 
            List<Integer> stats, Player player) {
        assertEquals(name, player.getName());
        assertEquals(age, player.getAge());
        assertEquals(hometown, player.getHometown());
        assertEquals(position, player.getPosition());
        assertEquals(stats, player.getStats());
    }

    protected void checkTeam(String name, List<Player> players, Team team) {
        assertEquals(players.size(), team.getPlayers().size());

        for (int i = 0; i < players.size(); i++) {
            Player expected = players.get(i);
            Player actual = team.getPlayers().get(i);

            checkPlayer(expected.getName(), expected.getAge(), expected.getHometown(), 
                    expected.getPosition(), expected.getStats(), actual);
        }
    }
}
