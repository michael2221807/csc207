package connect4game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import fall2018.csc2017.slidingtiles.Board;

import static org.junit.Assert.*;

public class GridManagerTest {
    GridManager gridManager;

    @Before
    public void setUp() throws Exception {
        gridManager = new GridManager(6, 7);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void restart() {
        gridManager.restart();
        assertEquals(-1, gridManager.getCount());
        assertFalse(gridManager.getIsFinished());

    }

    @Test
    public void getBoard() {
        int[][] board = new int[6][7];
        gridManager.setBoard(board, 0);
        assertEquals(board[0][0], gridManager.getBoard()[0][0]);
    }

    @Test
    public void getScore() {
        int oldScore = gridManager.getScore();
        gridManager.addToColumn(0);
        assertNotEquals(oldScore, gridManager.getScore());
    }

    @Test
    public void unDo() {
        ArrayList temp = new ArrayList<Integer>();
        gridManager.setAlAddPos(temp);
        temp.add(0, 1);
        gridManager.getAlAddPos().add(0, 1);
        gridManager.getAlAddPos().add(1, 5);
        gridManager.setCount(1);
        gridManager.unDo();
        assertEquals(temp, gridManager.getAlAddPos());
    }

    @Test
    public void addToColumn() {
        gridManager.addToColumn(0);
        assertEquals(1, gridManager.getBoard()[0][0]);
    }

    @Test
    public void setBoard() {
        int[][] board = new int[6][7];
        gridManager.setBoard(board, 0);
        assertEquals(board[0][0], gridManager.getBoard()[0][0]);
    }

    @Test
    public void getPosition() {
        assertEquals(41, gridManager.getPosition(5, 6));
    }

    @Test
    public void getRow() {
        assertEquals(3, gridManager.getRow(26));
    }

    @Test
    public void getColumn() {
        assertEquals(6, gridManager.getColumn(27));
    }

    @Test
    public void getCount() {
        gridManager.setCount(5);
        assertEquals(5, gridManager.getCount());
    }

    @Test
    public void setCount() {
        gridManager.setCount(5);
        assertEquals(5, gridManager.getCount());
    }

    @Test
    public void getTurn() {
        gridManager.setCount(5);
        assertEquals(2, gridManager.getTurn());
    }

    @Test
    public void getLastPos() {
        gridManager.addToColumn(0);
        gridManager.addToColumn(1);
        assertEquals(1, gridManager.getLastPos());

    }
}