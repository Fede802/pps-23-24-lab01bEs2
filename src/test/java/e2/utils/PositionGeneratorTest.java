package e2.utils;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionGeneratorTest {
    @Test
    void generatePosition(){
        int maxIndex = 5;
        Pair<Integer, Integer> position = new RandomPositionGenerator().generatePosition(maxIndex);
        assertAll(
                () -> assertTrue(position.getX() >= 0),
                () -> assertTrue(position.getX() < maxIndex),
                () -> assertTrue(position.getY() >= 0),
                () -> assertTrue(position.getY() < maxIndex)
        );
    }
}
