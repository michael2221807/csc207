package mineSweeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class MineGameTest {
    MineGame mineGame = new MineGame();
//    MineManager mineManager = new MineManager();
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMineManager() {
        MineManager mineManager =  new MineManager();
        mineGame.setMineManager(mineManager);
        assertEquals(mineManager, mineGame.getMineManager());
    }

    @Test
    public void setMineManager() {
        MineManager mineManager =  new MineManager();
        mineGame.setMineManager(mineManager);
        assertEquals(mineManager, mineGame.getMineManager());
    }

    @Test
    public void getSize() {
        int size = 10;
        mineGame.setSize(size);
        assertEquals(10, mineGame.getSize());
    }

    @Test
    public void setSize() {
        int size = 10;
        mineGame.setSize(size);
        assertEquals(10, mineGame.getSize());
    }

    @Test
    public void getPositions() {
        List<Integer> positions = new ArrayList<>();
        mineGame.setPositions(positions);
        assertEquals(positions, mineGame.getPositions());
    }

    @Test
    public void setPositions() {
        List<Integer> positions = new ArrayList<>();
        mineGame.setPositions(positions);
        assertEquals(positions, mineGame.getPositions());
    }
}