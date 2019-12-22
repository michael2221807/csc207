package fall2018.csc2017.slidingtiles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import static org.junit.Assert.*;

public class BoardTest extends Observable {
    Board board;
    int step;

    @Before
    public void setUp() throws Exception {
        board.setBoardSize(5);
        step = 5;
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = Board.NUM_ROWS * Board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }
        board = new Board(tiles);

    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test whether calculateScore method returns score of the game
     */
    @Test
    public void calculate_score(){
        assertEquals(100, board.calculateScore());
        board.setSteps(5);
        assertEquals(95, board.calculateScore());
    }

    /**
     * Test whether getSteps method returns number of steps player moves
     */
    @Test
    public void get_steps() {
        board.setSteps(step);
        assertEquals(step, board.getSteps());
    }

    /**
     * Test whether setSteps method updates the total steps player moves on the board correctly
     */
    @Test
    public void set_steps() {
        board.setSteps(10);
        assertEquals(10, board.getSteps());
    }

    /**
     * Test whether setBoardSize method sets up the board with correct number of column and rows
     */
    @Test
    public void setBoardSize() {
        assertEquals(5, board.NUM_COLS);
        assertEquals(5,board.NUM_ROWS);
    }

    /**
     * Test whether numTiles method returns the correct number of tiles on the board
     */
    @Test
    public void numTiles() {
        assertEquals(25,board.numTiles());
    }

    /**
     * Test whether getNumRow method returns the correct number of rows of the board
     */
    @Test
    public void get_numRow() {
        assertEquals(5, board.getNumRow());
    }

    /**
     * Test whether getTile method returns the correct tile
     */
//todo: I am not sure about this test
    @Test
    public void getTile() {
        assertEquals(1,board.getTile(0,0).getId());

    }

    /**
     * Test whether swapTile method swaps the tile
     */
    @Test
    public void swapTiles() {
        assertEquals(1, board.getTile(0, 0).getId());
        assertEquals(2, board.getTile(0, 1).getId());
        board.swapTiles(0, 0, 0, 1);
        assertEquals(2, board.getTile(0, 0).getId());
        assertEquals(1, board.getTile(0, 1).getId());
    }


//TODO:What is wrong with this method?!
//    @Test
//    public void toString() {
//    }

    @Test
    public void iterator() {
    }
}