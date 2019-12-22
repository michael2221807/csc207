package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    BoardManager(Board board) {
        this.board = board;
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Manage a new shuffled board.
     */
    BoardManager() {
        List<Tile> tiles = new ArrayList<>();
        int numTiles = Board.NUM_ROWS * Board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        this.board = new Board(tiles);
        int i = 0;
        while (i < 201){
            shuffleBoard();
            i++;
        }
    }

    /**
     * @return an arraylist of all positions
     * that has at least one available move.
     */
    List<Integer> scanBoard(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < board.numTiles(); i++){
            if (isValidTap(i)){
                list.add(i);

            }
        }
        return list;
    }

    /**
     * Choose a random move from the list create by scan board.
     */
    void shuffleBoard(){

        List<Integer> list = scanBoard();
        Integer random = list.get(new Random().nextInt(list.size()));
        touchMove(random);
        this.board.setSteps(0);
    }

    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        boolean solved = true;
        Iterator<Tile> iter = board.iterator();
        Tile first = iter.next();
        for (int i = 1; iter.hasNext(); i++){
            if (first.getId() != i){
                solved = false;
            }
            first = iter.next();
        }
        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / Board.NUM_COLS;
        int col = position % Board.NUM_COLS;
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == Board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == Board.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate. And save the step
     * into undo stack.
     *
     * @param position the position
     */
    void touchMove(int position) {

        int row = position / Board.NUM_ROWS;
        int col = position % Board.NUM_COLS;
        int blankId = board.numTiles();
        // TODO: figure out when to call board.swapTiles. Specifically, if any of the neighbouring
        // tiles is the blank tile, swap by calling Board's swap method.
        if (isValidTap(position)){
            Tile left = row == 0 ? null : board.getTile(row - 1, col);
            Tile right = row == Board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
            Tile below = col == 0 ? null : board.getTile(row, col - 1);
            Tile above = col == Board.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
            if (left != null && left.getId() == blankId){
                int position1 = row * board.NUM_ROWS + col;
                int position2 = (row-1) * board.NUM_ROWS + col;
                List l = new ArrayList();
                l.add(position1);
                l.add(position2);
                board.u.add1(l);

                board.swapTiles(row, col, row - 1, col);}

            if (right != null && right.getId() == blankId){
                int position1 = row * board.NUM_ROWS + col;
                int position2 = (row+1) * board.NUM_ROWS + col;
                List l = new ArrayList();
                l.add(position1);
                l.add(position2);
                board.u.add1(l);

                board.swapTiles(row, col, row + 1, col);}

            if (below != null && below.getId() == blankId){
                int position1 = row * board.NUM_ROWS + col;
                int position2 = row * board.NUM_ROWS + (col-1);
                List l = new ArrayList();
                l.add(position1);
                l.add(position2);
                board.u.add1(l);

                board.swapTiles(row, col, row, col - 1);}

            if (above != null && above.getId() == blankId){
                int position1 = row * board.NUM_ROWS + col;
                int position2 = row * board.NUM_ROWS + (col+1);
                List l = new ArrayList();
                l.add(position1);
                l.add(position2);
                board.u.add1(l);

                board.swapTiles(row, col, row, col + 1);}



        }
    }

}
