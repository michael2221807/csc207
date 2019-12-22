package MainClass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.io.IOException;
import java.io.ObjectOutputStream;

import connect4game.Connect4SettingActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.StartingActivity;

/**
 * GameCenterActivity that is used launch different game
 */
public class GameCenterActivity extends AppCompatActivity {

    private String username;

    private ScoreBoard scoreboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_center);
        setupStartConnect4ButtonListener();
        setUpScoreBoardUserButtonListener();
        setupStartSlidingTilesButtonListener();
        setupStartMineSweeperButtonListener();
        setUpScoreBoardTopButtonListener();
        setupLogOutButtonListener();
        Intent intent = getIntent();
        username = (String) intent.getExtras().get("username");
        scoreboard = (ScoreBoard) intent.getExtras().get("scoreboard");
        saveToScore("Score_info.ser");

    }

    /**
     * the button used to enter the User ScoreBoard Activity
     */
    private void setUpScoreBoardUserButtonListener(){
        Button ScoreBoardButton = findViewById(R.id.bt_game_house_scoreboarduser);
        ScoreBoardButton.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, ScoreActivity.class);
            tmp.putExtra("username", username);
            tmp.putExtra("scoreboard", scoreboard);
            startActivity(tmp);

        });

    }

    /**
     * the button used to enter the Top 5 ScoreBoard activity
     */
    private void setUpScoreBoardTopButtonListener(){
        Button ScoreBoardButton = findViewById(R.id.bt_game_house_scoreboardtop5);
        ScoreBoardButton.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, ScoreBoardActivity.class);
            tmp.putExtra("username", username);
            tmp.putExtra("scoreboard", scoreboard);
            startActivity(tmp);

        });

    }

    /**
     * set up startSlidingTiles button
     */
    private void setupStartSlidingTilesButtonListener() {
        Button slidingTilesButton = findViewById(R.id.slidingtiles_button);
        slidingTilesButton.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, StartingActivity.class);
            tmp.putExtra("username", username);
            tmp.putExtra("scoreboard", scoreboard);
            startActivity(tmp);

        });


    }

    /**
     * set up logOutButton button
     */
    private void setupLogOutButtonListener() {
        Button logOutButton = findViewById(R.id.log_out_button);
        logOutButton.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, LoginActivity.class);
            saveToScore("Score_info.ser");
            startActivity(tmp);

        });


    }

    /**
     * set up connect4Tiles button
     */
    private void setupStartConnect4ButtonListener() {
        Button connect4Button = findViewById(R.id.connect4_button);
        connect4Button.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, Connect4SettingActivity.class);
            tmp.putExtra("username", username);
            tmp.putExtra("scoreboard", scoreboard);
            startActivity(tmp);

        });


    }
    /*the button used to enter MineSweeper Setting activity
     */
    private void setupStartMineSweeperButtonListener() {
        Button connect4Button = findViewById(R.id.minesweeper_button);
        connect4Button.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, mineSweeper.MineSweeperSettingActivity.class);
            tmp.putExtra("username", username);
            tmp.putExtra("scoreboard", scoreboard);
            startActivity(tmp);

        });


    }

    /**
     * method used to save the ScoreBoard to Score File
     * @param fileName - the file that is used to save the ScoreBoard
     */
    private void saveToScore(String fileName){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(scoreboard);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
