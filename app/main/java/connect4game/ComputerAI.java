package connect4game;


import java.util.ArrayList;
import java.util.Random;

/**
 *  This class represents the main move decider for Connect4.
 */
public class ComputerAI {

    private int[][] board;
    private String complexity;

    ComputerAI(GridManager gm, String complexity){
        this.board = gm.getBoard();
        this.complexity = complexity;
    }

    /**
     * This method returns an ArrayList containing all the positions available for player to
     * choose.
     * @return An ArrayList of Integers containing all positions available for player to choose.
     */
    private ArrayList<Integer> getPotentialMoves(){
        ArrayList<Integer> listOfPositions = new ArrayList<>();
        for(int col = 0; col < 7; col++){
            for(int row = 0; row < 6 ; row++){
                if (board[row][col] == 0){
                    listOfPositions.add(row*7 + col);
                    break;
                }
            }
        }
        return listOfPositions;
    }

    /**
     * This method returns the most valuable position for the player to choose.
     * @return An int representing the position of the most valuable position.
     */

    protected int selectMove(){
        // Get a list of moves that we may make.
        ArrayList<Integer> listOfPositions = getPotentialMoves();
        // Randomly select moves from the list if the difficulty is Beginner.
        if (complexity.equals("Beginner")){
            int index = new Random().nextInt(listOfPositions.size());
            return listOfPositions.get(index);
        }
        // Choose the position with the most value.
        int positionToReturn = listOfPositions.get(0);
        for (int position: listOfPositions){
            if(calculateValue(positionToReturn) < calculateValue(position)){
                positionToReturn = position;
            }
        }
        return positionToReturn;
    }
    /**
     * This method returns whether the player wins the game horizontally.
     * @param board the board waiting to be checked
     * @return True if there are four stones of the same connect horizontally.
     */

    private boolean checkHorizontalWin(int[][] board){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7 - 3; j++) {
                if (board[i][j] != 0){
                if (board[i][j + 1] == board[i][j] && board[i][j + 2] == board[i][j] &&
                        board[i][j + 3] == board[i][j]){
                    return true;
                }
            }}
        }
        return false;
    }

    /**
     * This method returns whether the player wins the game vertically.
     * @param board the board waiting to be checked
     * @return True if there are four stones of the same connect vertically.
     */

    private boolean checkVerticalWin(int[][] board){
        for (int i = 0; i < 6 - 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] != 0){
                    if (board[i + 1][j] == board[i][j] && board[i + 2][j] == board[i][j] &&
                            board[i + 3][j] == board[i][j]){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method returns whether the player wins the game alone two diagonals.
     * @param board the board waiting to be checked
     * @return True if there are four stones of the same connect alone two diagonals.
     */

    private boolean checkDiagonalWin(int[][] board){
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 7 - 3; j++) {
                if (board[i][j] != 0){
                    if (board[i - 1][j + 1] == board[i][j] &&
                            board[i - 2][j + 2] == board[i][j] && board[i - 3][j + 3] == board[i][j]) {
                        return true;
                    }
                }
            }
        }
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 7; j++) {
                if(board[i][j] != 0){
                    if (board[i - 1][j - 1] == board[i][j] && board[i - 2][j - 2] == board[i][j]
                            && board[i - 3][j - 3] == board[i][j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method returns an int representing the value of given position.
     * @param position The int representation of the position to be calculated.
     * @return An int representing the value of given position.
     */


    private int calculateValue(int position){
        int row = position / 7;
        int col = position % 7;
        int[][] tempBoard = new int[6][7];
        for (int i = 0; i < 6; i++) {
            System.arraycopy(board[i], 0, tempBoard[i], 0, 7);
        }
        //If this move makes computer win the game, then this move worth the most value.
        tempBoard[row][col] = 2;
        if (checkDiagonalWin(tempBoard) || checkHorizontalWin(tempBoard) ||
                checkVerticalWin(tempBoard)) {return 100;}
        // If this move prevents the enemy from wining the game, then this move worth the second
        // most value.
        tempBoard[row][col] = 1;
        if (checkDiagonalWin(tempBoard) || checkHorizontalWin(tempBoard) ||
                checkVerticalWin(tempBoard)){return 99;}
        // Add 3 score for each stone in the surrounding layer that is the same as this stone,
        // and 2 score for each enemy stone.
        int value = 0;
        for (int i = Math.max(row - 2, 0); i <= Math.min(row + 2, 5); i++){
            for (int j = Math.max(col - 2, 0); j <= Math.min(col + 2, 6); j++){
                if (tempBoard[i][j] == 1){
                    value += 2;
                } else if (tempBoard[i][j] == 2){
                    value += 3;
                }
            }
        }

        return value;
    }
}
