package MainClass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {
    Score testScore;

    @Before
    public void setUp() throws Exception {
        testScore = new Score(60, "jimmy", "tiles");
    }

    @After
    public void tearDown() throws Exception {
        testScore = null;
    }

    @Test
    public void getScore() {
        assertEquals(testScore.getScore(), 60);
    }

    @Test
    public void setScore() {
        testScore.setScore(50);
        assertEquals(testScore.getScore(), 50);
    }

    @Test
    public void getUsername() {
        assertEquals(testScore.getUsername(), "jimmy");
    }

    @Test
    public void setUsername() {
        testScore.setUsername("Oscar");
        assertEquals(testScore.getUsername(), "Oscar");
    }

    @Test
    public void getGameName() {
        assertEquals(testScore.getGameName(), "tiles");
    }

    @Test
    public void setGameName() {
        testScore.setGameName("connect4");
        assertEquals(testScore.getGameName(), "connect4");
    }

    @Test
    public void compareTo() {
        Score compareScore = new Score(60, "jimmy", "tiles");
        assertEquals(testScore.compareTo(compareScore), 0);

    }

}