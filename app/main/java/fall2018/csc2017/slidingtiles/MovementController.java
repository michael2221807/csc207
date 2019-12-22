package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.List;

import MainClass.AccountManager;
import MainClass.GameCenterActivity;
import MainClass.Score;
import MainClass.ScoreBoard;
import MainClass.User;

/**
 *  MovementController class that control movement of board
 */
public class MovementController {
    private Context context;
    protected BoardManager boardManager = null;
    protected ScoreBoard gameScoreBoard = null;
    protected String username = null;
    protected User user = null;
    MovementController(Context context) {
        this.context = context;
    }
    MovementController(){
    }

    void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }
    void setGameScoreBoard(ScoreBoard gameScoreBoard){this.gameScoreBoard = gameScoreBoard;}

    public void setUsername(String name){this.username = name;}

    public void setUser(User user){
        this.user = user;
    }


    /**
     * process the each tap movement and addscore to GameScoreBoard and UserScoreBaord
     * @param context gameactivity
     * @param position position
     * @param display display
     */
     void processTapMovement(Context context, int position, boolean display) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                gameScoreBoard.addScore(new Score(boardManager.getBoard().calculateScore(),
                        user.userName, "tiles"));
                Intent intent = new Intent(context, GameCenterActivity.class);
                AccountManager am = AccountManager.getAm();

                am.m.replace(user.userName, user);
                am.saveToFile1(context);
                intent.putExtra("username" , user.userName);
                intent.putExtra("scoreboard", this.gameScoreBoard);
                context.startActivity(intent);

            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * process the double tap movement
     * @param position position of tap
     */
     void doubleTapMovement(List position){
        Integer position1 = (Integer) position.remove(0);
        Integer position2 = (Integer) position.remove(0);

        int row1 = position1 / boardManager.getBoard().getNumRow();
        int col1 = position1 % boardManager.getBoard().getNumRow();
        int row2 = position2 / boardManager.getBoard().getNumRow();
        int col2 = position2 % boardManager.getBoard().getNumRow();
        boardManager.getBoard().swapTiles(row1, col1, row2, col2);
    }
}
