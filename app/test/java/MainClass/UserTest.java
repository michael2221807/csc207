package MainClass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import connect4game.GridGame;
import fall2018.csc2017.slidingtiles.TilesGame;
import mineSweeper.MineGame;

import static org.junit.Assert.*;

public class UserTest {

    User user = new User("Jas","1" );

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setTileGame() {
        TilesGame tile = new TilesGame();
        user.setTileGame(tile);
        assertEquals(tile,user.getTilesGame());
    }

    @Test
    public void getTilesGame() {
        TilesGame tile = new TilesGame();
        user.setTileGame(tile);
        assertEquals(tile,user.getTilesGame());
    }

    @Test
    public void setComplexity() {
        Integer complex = 4;
        user.setComplexity(complex);
        assertEquals(4, user.getUserComplexity());
    }

    @Test
    public void getUserComplexity() {
        Integer complex = 4;
        user.setComplexity(complex);
        assertEquals(4, user.getUserComplexity());
    }

    @Test
    public void getGridGame() {
        GridGame gridGame = new GridGame();
        user.setGridGame(gridGame);
        assertEquals(gridGame,user.getGridGame());
    }

    @Test
    public void setGridGame() {
        GridGame gridGame = new GridGame();
        user.setGridGame(gridGame);
        assertEquals(gridGame,user.getGridGame());
    }


    @Test
    public void getMineGame() {
        MineGame mineGame = new MineGame();
        user.setMineGame(mineGame);
        assertEquals(mineGame, user.getMineGame());
    }

    @Test
    public void setMineGame() {
        MineGame mineGame = new MineGame();
        user.setMineGame(mineGame);
        assertEquals(mineGame, user.getMineGame());
    }
}