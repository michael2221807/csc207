package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class Board extends Observable implements Serializable, Iterable<Tile> {


    UndoStack<List> u = new UndoStack<>(3);

    private int step = 0;

    private int score = 100;

    /**
     * The number of rows.
     */
    static int NUM_ROWS = 4;

    /**
     * The number of rows.
     */
    static int NUM_COLS = 4;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles = new Tile[NUM_ROWS][NUM_COLS];

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles) {

        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }

    }

    /**
     * calculate current board score using step
     * @return int score
     */
     int calculateScore(){
        return score - step;
    }

    /**
     * return the number of steps moved in this board
     * @return int step
     */
    public int getSteps(){
        return step;
    }

    public void setSteps(int steps){this.step = steps;}
    /**
     * set the Board size
     * @param size
     */
    static void setBoardSize(int size){
        NUM_ROWS = size;
        NUM_COLS = size;
    }
    /**
     * Return the number of tiles on the board.
     * @return the number of tiles on the board.
     */
    int numTiles() {
        return NUM_COLS * NUM_ROWS;
    }

    /**
     * return number of rows of this board
     * @return int number of rows
     */
    int getNumRow(){return NUM_ROWS;}

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {

        Tile first_tile = getTile(row1, col1);
        Tile second_tile = getTile(row2, col2);
        tiles[row1][col1] = second_tile;
        tiles[row2][col2] = first_tile;

        step ++; //Add 1 to step after swapping.

        setChanged();
        notifyObservers();
    }

    /**
     * return the string of the Board.
     * @return the string of the Board.
     */
    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    /**
     * Return a new BoardIterator for Board.
     * @return A new BoardIterator that contains tiles.
     */
    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new BoardIterator();
    }

    /**
     * BoardIterator contains tiles.
     */
    private class BoardIterator implements Iterator<Tile> {

        /**
         * The next element in BoardIterator. Initial is 0.
         */
        private int next;

        /**
         * @return if there is next element.
         */
        @Override
        public boolean hasNext() {
            return next < numTiles();
        }

        /**
         * @return the next element if there is any, throw
         * NoSuchElementException if there is no next element.
         */
        @Override
        public Tile next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            Tile result = tiles[next / NUM_ROWS][next % NUM_ROWS];
            next++;
            return  result;
        }
    }
}
