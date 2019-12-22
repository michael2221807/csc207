package fall2018.csc2017.slidingtiles;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import MainClass.ScoreBoard;
import MainClass.User;

import static org.junit.Assert.*;

public class MovementControllerTest {

    BoardManager boardManager;
    Board board;
    Context context;

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

    /**
     * test for setter.
     */
    @Test
    public void setBoardManager() {
        MovementController movementController = new MovementController();
        movementController.setBoardManager(boardManager);
        assert movementController.boardManager.getBoard().getTile(0,0).getId() == 1;

    }
    /**
     * test for setter.
     */
    @Test
    public void setGameScoreBoard() {
        MovementController movementController = new MovementController();
        movementController.setGameScoreBoard(new ScoreBoard());
        assert movementController.gameScoreBoard != null;
    }
    /**
     * test for setter.
     */
    @Test
    public void setUsername() {
        MovementController movementController = new MovementController();
        movementController.setUsername("kiki");
        assert movementController.username.equals("kiki");
    }

    /**
     * test for setter.
     */
    @Test
    public void setUser() {
        MovementController movementController = new MovementController();
        movementController.setUser(new User("kiki","doyouloveme"));
        assert movementController.user.password.equals("doyouloveme");

    }
    /**
     * test if tiles swap after tapping.
     */
//    @Test
//    public void processTapMovement() {
//        MovementController movementController = new MovementController();
//        movementController.setBoardManager(boardManager);
//        movementController.processTapMovement(context, 14, true);
//        assert movementController.boardManager.getBoard().getTile(3,3).getId() == 15;
//    }
//    /**
//     * test if tiles swap back to previous position after tapping undo.
//     */
//    @Test
//    public void doubleTapMovement() {
//        MovementController movementController = new MovementController();
//        movementController.setBoardManager(boardManager);
//        movementController.processTapMovement(context, 14, true);
//        List<Integer> positions = new ArrayList<>();
//        positions.add(14);
//        positions.add(15);
//        movementController.doubleTapMovement(positions);
//        assert movementController.boardManager.getBoard().getTile(3,3).getId() == 16;
//
//    }
}