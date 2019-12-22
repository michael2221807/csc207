package connect4game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerAITest {
    int[][] board;
    GridManager gridManager;
    ComputerAI computerAI;


    @Before
    public void setUp() throws Exception {
        gridManager = new GridManager(6, 7);
        computerAI = new ComputerAI(gridManager, "Intermediate");
        this.board = gridManager.getBoard();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test whether AI chooses the winning move
     */
    @Test
    public void testMove() {
        board[0][0] = 1;
        board[1][0] = 2;
        board[1][1] = 2;
        board[1][2] = 2;
        board[2][0] = 1;
        board[2][1] = 1;
        board[3][0] = 1;
        assertEquals(1,computerAI.selectMove());

    }

    /**
     * Test whether AI chooses the move that blocks player's winning move
     */
    @Test
    public void testblockwinMove() {
        board[0][0] = 1;
        board[1][0] = 2;
        board[1][1] = 2;
        board[0][1] = 2;
        board[2][0] = 1;
        board[2][1] = 1;
        board[2][2] = 1;
        assertEquals(2,computerAI.selectMove());

    }

    /**
     * Test whether AI chooses the winning move over the move that blocks player's winning move
     */
    @Test
    public void testpriorityMove() {
        board[0][0] = 1;
        board[1][0] = 2;
        board[1][1] = 2;
        board[1][2] = 2;
        board[2][0] = 1;
        board[2][1] = 1;
        board[3][0] = 1;
        assertEquals(1,computerAI.selectMove());

    }

    /**
     * Test AI's respond when no winning move or block winning move will occur
     */
    @Test
    public void testothercaseMove() {
        board[0][0] = 1;
        assertEquals(7, computerAI.selectMove());

    }
}