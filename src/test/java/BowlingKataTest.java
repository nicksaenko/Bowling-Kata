import bowlingkata.model.Bowling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingKataTest {

    private Bowling bowling;

    @BeforeEach
    public void setUp() {
        bowling = new Bowling();
    }

    @Test
    public void testAllStrikes() {
        for (int i = 0; i < 12; i++) {
            bowling.processThrow("X");
        }
        assertEquals(300, bowling.getPoints());
    }

    @Test
    public void testAllNinesAndMisses() {
        for (int i = 0; i < 10; i++) {
            bowling.processThrow("9");
            bowling.processThrow("0");
        }
        assertEquals(90, bowling.getPoints());
    }

    @Test
    public void testAllFivesAndSparesWithFinalFive() {
        for (int i = 0; i < 10; i++) {
            bowling.processThrow("5");
            bowling.processThrow("/");
        }
        bowling.processThrow("5");
        assertEquals(150, bowling.getPoints());
    }

    @Test
    public void testMixed() {

        bowling.processThrow("4");
        bowling.processThrow("2");

        bowling.processThrow("8");
        bowling.processThrow("/");

        bowling.processThrow("6");
        bowling.processThrow("3");

        bowling.processThrow("X");

        bowling.processThrow("3");
        bowling.processThrow("/");

        bowling.processThrow("0");
        bowling.processThrow("8");

        bowling.processThrow("X");

        bowling.processThrow("X");

        bowling.processThrow("7");
        bowling.processThrow("0");

        bowling.processThrow("5");
        bowling.processThrow("/");
        bowling.processThrow("1");

        assertEquals(131, bowling.getPoints());
    }

    @Test
    public void testFinalSpare() {

        bowling.processThrow("2");
        bowling.processThrow("3");

        bowling.processThrow("4");
        bowling.processThrow("5");

        bowling.processThrow("1");
        bowling.processThrow("5");

        bowling.processThrow("X");

        bowling.processThrow("4");
        bowling.processThrow("/");

        bowling.processThrow("0");
        bowling.processThrow("0");

        bowling.processThrow("2");
        bowling.processThrow("/");

        bowling.processThrow("X");

        bowling.processThrow("5");
        bowling.processThrow("0");

        bowling.processThrow("X");
        bowling.processThrow("2");
        bowling.processThrow("/");

        assertEquals(110, bowling.getPoints());
    }

    @Test
    public void testSpareFollowedByStrikesInEighthAndNinthFrames() {

        bowling.processThrow("2");
        bowling.processThrow("3");

        bowling.processThrow("4");
        bowling.processThrow("5");

        bowling.processThrow("1");
        bowling.processThrow("5");

        bowling.processThrow("X");

        bowling.processThrow("4");
        bowling.processThrow("/");

        bowling.processThrow("0");
        bowling.processThrow("0");

        bowling.processThrow("2");
        bowling.processThrow("/");

        bowling.processThrow("X");

        bowling.processThrow("X");

        bowling.processThrow("X");
        bowling.processThrow("2");
        bowling.processThrow("/");

        assertEquals(142, bowling.getPoints());
    }

    @Test
    public void testAllMisses() {
        for (int i = 0; i < 12; i++) {
            bowling.processThrow("0");
            bowling.processThrow("0");
        }
        assertEquals(0, bowling.getPoints());
    }

}