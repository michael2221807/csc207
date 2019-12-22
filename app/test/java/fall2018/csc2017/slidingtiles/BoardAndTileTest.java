package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BoardAndTileTest {

    /** The board manager for testing. */
    BoardManager boardManager;

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<Tile> makeTiles() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = Board.NUM_ROWS * Board.NUM_COLS;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }

        return tiles;
    }

    /**
     * Make a solved Board.
     */
    private void setUpCorrect() {
        List<Tile> tiles = makeTiles();
        Board board = new Board(tiles);
        boardManager = new BoardManager(board);
    }

    /**
     * Shuffle a few tiles.
     */
    private void swapFirstTwoTiles() {
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void testIsSolved() {
        setUpCorrect();
        assertEquals(true, boardManager.puzzleSolved());
        swapFirstTwoTiles();
        assertEquals(false, boardManager.puzzleSolved());
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        setUpCorrect();
        assertEquals(1, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(2, boardManager.getBoard().getTile(0, 1).getId());
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
        assertEquals(2, boardManager.getBoard().getTile(0, 0).getId());
        assertEquals(1, boardManager.getBoard().getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        setUpCorrect();
        assertEquals(15, boardManager.getBoard().getTile(3, 2).getId());
        assertEquals(16, boardManager.getBoard().getTile(3, 3).getId());
        boardManager.getBoard().swapTiles(3, 3, 3, 2);
        assertEquals(16, boardManager.getBoard().getTile(3, 2).getId());
        assertEquals(15, boardManager.getBoard().getTile(3, 3).getId());
    }

    /**
     * Test whether isValidHelp works.
     */
    @Test
    public void testIsValidTap() {
        setUpCorrect();
        assertEquals(true, boardManager.isValidTap(11));
        assertEquals(true, boardManager.isValidTap(14));
        assertEquals(false, boardManager.isValidTap(10));
    }

    /**
     * Test if get background work.
     */
    @Test
    public void testGetBackground(){
        setUpCorrect();
        assertEquals(0, boardManager.getBoard().getTile(0,0).getBackground());
        assertEquals(15, boardManager.getBoard().getTile(3,3).getBackground());

    }

    /**
     * Test if get id work.
     */
    @Test
    public void testGetId(){
        setUpCorrect();
        assertEquals(1, boardManager.getBoard().getTile(0,0).getId());
        assertEquals(16, boardManager.getBoard().getTile(3,3).getId());

    }

    /**
     * test if compare to work, check if tile[0][0] equals tile[0][1]
     */
    @Test
    public void testCompareTo(){
        setUpCorrect();
        assertEquals(1, boardManager.getBoard().getTile(0,0).compareTo(boardManager.getBoard().getTile(0,1)));
    }

    /**
     * Test the overloaded constructor. Background of the drawable
     * tile_6 is 2131165334.
     * More cases are omitted since we tested it by
     * debuging in our virtual devices.
     */
    @Test
    public void testConstructor(){
        Tile tile6 = new Tile(5);
        assertEquals(2131165334, tile6.getBackground());
        assertEquals(6, tile6.getId());

        Tile tile25 = new Tile(24);
        assertEquals(2131165330, tile25.getBackground());
        assertEquals(25, tile25.getId());
    }
}

