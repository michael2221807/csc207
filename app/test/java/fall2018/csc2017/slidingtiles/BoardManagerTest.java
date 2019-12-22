package fall2018.csc2017.slidingtiles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoardManagerTest {

    /** The board manager for testing. */
    BoardManager boardManager;
    Board board;


    @Before
    public void setUp() throws Exception {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = Board.NUM_ROWS * Board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }
        board = new Board(tiles);
        boardManager = new BoardManager(board);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getBoard() {
        assertEquals(board, boardManager.getBoard());
    }

    /**
     * Test whether puzzleSolved method works
     */
    @Test
    public void puzzleSolved() {
        assertTrue(boardManager.puzzleSolved());
        boardManager.getBoard().swapTiles(0, 0 , 1, 0);
        assertFalse(boardManager.puzzleSolved());
    }

    /**
     * Test whether isValidTap method works
     */
    @Test
    public void isValidTap() {
        assertTrue(boardManager.isValidTap((Board.NUM_COLS*Board.NUM_ROWS)-2)); //The tile on the left of the blank tile
        assertFalse(boardManager.isValidTap(1));
    }
    //TODO: implementthis
    /**
     * Test whether touchMove method works
     */
    @Test
    public void touchMove() {
        boardManager.touchMove(14);
        assertEquals(15, boardManager.getBoard().getTile(3,3).getId());

    }

    /**
     * test for scanning the board.
     * by default, blank space is at
     * row 3 col 3 and there are two
     * tiles that have available move.
     */
    @Test
    public void testScanBoard(){
        assert boardManager.scanBoard().size() == 2;
    }

    /**
     * test for shuffling the board.
     * after moving the last position
     * which originally was id 16 blank tile
     * will be replaced.
     */
    @Test
    public void testShuffleBoard(){
        Tile tile = boardManager.getBoard().getTile(3,3);
        boardManager.shuffleBoard();
        assert tile.getId() != boardManager.getBoard().getTile(3,3).getId();
    }

    /**
     * This constructor will create a board with
     * shuffled tiles but the id of the tiles should
     * not change.
     */
    @Test
    public void testBoardManagerConstructor(){
        for (int i = 0; i < 20; i++){
            boardManager = new BoardManager();
            int id = boardManager.getBoard().getTile(3,3).getId();
            assert id <= 16;
        }
    }

}