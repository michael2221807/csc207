package MainClass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class ScoreBoardTest {
    ScoreBoard  scoreBoard;

    @Before
    public void setUp() throws Exception {
        scoreBoard = new ScoreBoard();
    }

    @After
    public void tearDown() throws Exception {
        scoreBoard = null;
    }

    @Test
    public void addScore() {
        Score i = new Score(60, "jimmy", "tiles");
        scoreBoard.addScore(i);
        assertEquals(scoreBoard.getGameScoreBoard("tiles").get(0).getScore(),60);

    }

    @Test
    public void isempty() {
        Score i = new Score(60, "jimmy", "tiles");
        scoreBoard.addScore(i);
        assertFalse(scoreBoard.isEmpty());
    }

    @Test
    public void getUserScoreBoard() {
        Score i = new Score(60, "jimmy", "tiles");
        Score i2 = new Score(670, "Oscar", "tiles");
        scoreBoard.addScore(i);
        scoreBoard.addScore(i2);
        ArrayList<Score> check_list = new ArrayList<>();
        Collections.addAll(check_list, i);
        assertEquals(scoreBoard.getUserScoreBoard("jimmy", "tiles"), check_list);
    }

    @Test
    public void getGameScoreBoard() {
        Score i = new Score(60, "jimmy", "tiles");
        Score i2 = new Score(670, "Oscar", "connect4");
        scoreBoard.addScore(i);
        scoreBoard.addScore(i2);
        ArrayList<Score> check_list = new ArrayList<>();
        Collections.addAll(check_list, i);
        assertEquals(scoreBoard.getGameScoreBoard("tiles"), check_list);

    }
}