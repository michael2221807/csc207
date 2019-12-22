package mineSweeper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import MainClass.AccountManager;
import MainClass.GameCenterActivity;
import MainClass.Score;
import MainClass.User;
import MainClass.ScoreBoard;
import fall2018.csc2017.slidingtiles.R;


public class MineGameActivity extends Activity implements View.OnClickListener,
        View.OnLongClickListener {

    private int score = 100;

    LinearLayout textviews;
    LinearLayout buttons;

    private ScoreBoard GameScoreBoard;
    private String username;
    private User user;
    private MineGame mineGame;
    private MineManager mineManager;
    private AccountManager accountManager;
    private int size;
    private List<Integer> positions;
    int[][] map;

    List<Button> buttonList = new ArrayList<>();


    /**
     * @param savedInstanceState
     * Initialize mineGame, mineManager by loading from user's file.
     * create position list if it is null. Create the view by the board
     * in mineManager and recreate the view by the positions saved.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        textviews = (LinearLayout) findViewById(R.id.textviews);
        buttons = (LinearLayout) findViewById(R.id.buttons);

        loadFromUser();
        if (positions == null) {
            positions = new ArrayList<>();
        }
        map = mineManager.getMap();

        Intent intent = getIntent();
        username = (String) intent.getExtras().get("username");
        GameScoreBoard = (ScoreBoard) intent.getExtras().get("scoreboard");

        initView();
        if (!positions.isEmpty()){
            for (Integer id : positions){
                int row = id / size;
                int col = id % size;
                if (map[row][col] == 0){
                    ShowWhiteSpace(row, col);
                }
                else if (map[row][col] != -1 && map[row][col] != 0){
                    Hide_button(row, col);
                }
            }

        }

    }

    /**
     * create view by the board(map) in mineManager.
     */
    private void initView() {
        int width = getResources().getDisplayMetrics().widthPixels / size;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,
                width);
        for (int i = 0; i < size; i++) {
            LinearLayout tvs = new LinearLayout(this);
            tvs.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout btns = new LinearLayout(this);
            btns.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < size; j++) {
                TextView tv = new TextView(this);
                tv.setBackgroundResource(R.mipmap.mineback2);
                tv.setLayoutParams(params);
                tv.setGravity(Gravity.CENTER);
                if (map[i][j] == -1)
                    tv.setText("*");
                else if (map[i][j] != 0)
                    tv.setText(map[i][j] + "");
                tvs.addView(tv);
                Button btn = new Button(this);
                btn.setBackgroundResource(R.mipmap.mineback);
                btn.setLayoutParams(params);
                btn.setTag(i * size + j);
                btn.setOnClickListener(this);
                btn.setOnLongClickListener(this);
                buttonList.add(btn);
                btns.addView(btn);
            }
            textviews.addView(tvs);
            buttons.addView(btns);
        }
    }

    /**
     * @param v view
     * change the view clicked to invisible so that the
     * mine board could be seen.
     * If player win, show the text and return to GameCenter
     * If player lose, show the text and return to GameCenter
     * If the selected space is safe and the game is not end,
     * flip all the safe spots around it.
     */
    @Override
    public void onClick(View v) {
        score--;
        int id = (Integer) v.getTag();
        positions.add(id);
        int row = id / size;
        int col = id % size;
        v.setVisibility(View.INVISIBLE);
        if (isWin()) {
            Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
            GameScoreBoard.addScore(new Score(score, username, "mines"));
            returnToGameActivity();
        }
        if(isOver(row, col)){
            GameScoreBoard.addScore(new Score(0, username, "mines"));
            returnToGameActivity();
        }
        if (map[row][col] == 0) {
            ShowWhiteSpace(row, col);
        }
    }

    /**
     * @param i row
     * @param j column
     * recursively make views invisible if the views around are
     * safe.
     */
    public void ShowWhiteSpace(int i, int j) {
        buttonList.get(i * size + j).setVisibility(View.INVISIBLE);
        if (i != 0 && j != 0) {
            if (map[i - 1][j - 1] == 0) {
                if (CheckifRecursionIsneeded (i - 1, j - 1))
                    ShowWhiteSpace(i - 1, j - 1);
            } else {
                Hide_button(i - 1, j - 1);
            }
        }
        if (j != 0) {
            if (map[i][j - 1] == 0) {
                if (CheckifRecursionIsneeded (i, j - 1))
                    ShowWhiteSpace(i, j - 1);
            } else {
                Hide_button(i, j - 1);
            }
        }
        if (j != 0 && i != (size-1)) {
            if (map[i + 1][j - 1] == 0) {
                if (CheckifRecursionIsneeded (i + 1, j - 1))
                    ShowWhiteSpace(i + 1, j - 1);
            } else {
                Hide_button(i + 1, j - 1);
            }
        }
        if (i != 0) {
            if (map[i - 1][j] == 0) {
                if (CheckifRecursionIsneeded (i - 1, j))
                    ShowWhiteSpace(i - 1, j);
            } else {
                Hide_button(i - 1, j);
            }
        }
        if (i != (size-1)) {
            if (map[i + 1][j] == 0) {
                if (CheckifRecursionIsneeded (i + 1, j))
                    ShowWhiteSpace(i + 1, j);
            } else {
                Hide_button(i + 1, j);
            }
        }
        if (j != (size-1) && i != 0) {
            if (map[i - 1][j + 1] == 0) {
                if (CheckifRecursionIsneeded (i - 1, j + 1))
                    ShowWhiteSpace(i - 1, j + 1);
            } else {
                Hide_button(i - 1, j + 1);
            }
        }
        if (j != (size-1)) {
            if (map[i][j + 1] == 0) {
                if (CheckifRecursionIsneeded (i, j + 1))
                    ShowWhiteSpace(i, j + 1);
            } else {
                Hide_button(i, j + 1);
            }
        }
        if (j != (size-1) && i != (size-1)) {
            if (map[i + 1][j + 1] == 0) {
                if (CheckifRecursionIsneeded (i + 1, j + 1))
                    ShowWhiteSpace(i + 1, j + 1);
            } else {
                Hide_button(i + 1, j + 1);
            }
        }

    }

    /**
     * @param i row
     * @param j column
     * set individual view invisible regardless if it is safe.
     */
    private void Hide_button(int i, int j) {
        int position = i * size + j;
        buttonList.get(position).setVisibility(View.INVISIBLE);
    }

    /**
     * @param row row
     * @param col col
     * @return if the surrounding views are already invisible.
     */
    boolean CheckifRecursionIsneeded (int row, int col) {
        int position = row * size + col;
        if (buttonList.get(position).getVisibility() == View.INVISIBLE)
            return false;
        else
            return true;
    }

    /**
     * @param row row
     * @param col col
     * @return if the game is over, you click on mine,
     * or you successfully find all the mines.
     */
    private boolean isOver(int row, int col) {
        if (map[row][col] == -1) {
            Toast.makeText(this, "GameOver", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < buttonList.size(); i++) {
                buttonList.get(i).setVisibility(View.INVISIBLE);
            }
            return true;
        }
        return false;
    }

    List<Integer> Flag = new ArrayList<Integer>();

    /**
     * @param v view
     * @return return true to indicate that you have handled the event and it should stop here.
     * when the flags are still available, change the background of selected button to flag
     * otherwise, show the text that indicate not enough flag.
     */
    @Override
    public boolean onLongClick(View v) {
        Button btn = (Button) v;
        int tag = (Integer) v.getTag();
        if (Flag.contains(tag)) {
            btn.setText("");
            Flag.remove((Integer) tag);
        } else {
            if (Flag.size() != size) {
                Flag.add(tag);
                btn.setBackgroundColor(Color.rgb(227,114,0));
            } else {
                Toast.makeText(this, "no more Flag available ", Toast.LENGTH_SHORT).show();
            }

        }
        return true;
    }

    /**
     * @return if the player win the game, when the number of invisible view (safe spot)
     * equal to the total amount of safe spots.
     */
    public boolean isWin() {
        int sum = 0;
        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i).getVisibility() == View.INVISIBLE)
                sum++;
        }
        return sum == size * size - size * size / 10;
    }

    /**
     * Go to GameCenter.
     */
    private void returnToGameActivity(){
        Intent intent = new Intent(this, GameCenterActivity.class);
        intent.putExtra("username" , username);
        intent.putExtra("scoreboard", GameScoreBoard);
        startActivity(intent);
    }

    /**
     * save the mineGame to user when pausing.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveToUser();
        Toast.makeText(this, "Auto Saving...", Toast.LENGTH_SHORT).show();
    }

    /**
     * get mineGame and more data from the user by username passed from last activity.
     */
    private void loadFromUser(){
        Intent intent;
        intent = getIntent();
        username = (String) intent.getExtras().get("username");
        accountManager = AccountManager.getAm();
        user = accountManager.getUser(username,this);

        try {
            mineGame = user.getMineGame();
            positions = mineGame.getPositions();
            size = mineGame.getSize();
            mineManager = mineGame.getMineManager();
        }
        catch (NullPointerException e){
            Log.e("","There are no save gridGame or gridManager!");
        }

    }

    /**
     * save mineGame and more data to the user with the username passed from the last activity.
     */
    private void saveToUser(){
        mineGame.setMineManager(mineManager);
        mineGame.setSize(size);
        mineGame.setPositions(positions);
        user.setMineGame(mineGame);
        accountManager.m.replace(username, user);
        accountManager.saveToFile1(this);

    }
}