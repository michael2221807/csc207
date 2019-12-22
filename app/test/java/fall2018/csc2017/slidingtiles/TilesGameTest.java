package fall2018.csc2017.slidingtiles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TilesGameTest {
    TilesGame tilesGame;

    @Before
    public void setUp() throws Exception {
        tilesGame = new TilesGame();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void set_Board_Manager() {
        BoardManager temp = new BoardManager();
        tilesGame.setBoardManager(temp);
        assertEquals(temp, tilesGame.getBoardManager());
    }

    @Test
    public void get_Board_Manager() {
        BoardManager temp = new BoardManager();
        tilesGame.setBoardManager(temp);
        assertEquals(temp, tilesGame.getBoardManager());
    }
}