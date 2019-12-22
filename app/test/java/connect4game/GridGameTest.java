package connect4game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridGameTest {
    GridManager gridManager;
    GridGame gridGame;
    GridManager gridManagerPVP;
    GridManager gM;

    @Before
    public void setUp() throws Exception {
        gridManager = new GridManager(6, 7);
        gridGame = new GridGame();


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getGridManager() {
        assertEquals(null, gridGame.getGridManager());
    }

    @Test
    public void setGridManager() {
        gM = new GridManager(6, 7);
        gridGame.setGridManager(gM);
        assertEquals(gM,gridGame.getGridManager());

    }

    @Test
    public void getGridManagerPVP() {
        assertEquals(gridManagerPVP, gridGame.getGridManagerPVP());
    }

    @Test
    public void setGridManagerPVP() {
        gM = new GridManager(6, 7);
        gridGame.setGridManagerPVP(gM);
        assertEquals(gM,gridGame.getGridManagerPVP());
    }
}