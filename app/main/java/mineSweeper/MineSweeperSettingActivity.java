package mineSweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import MainClass.AccountManager;
import MainClass.ScoreBoard;
import MainClass.User;
import fall2018.csc2017.slidingtiles.R;

public class MineSweeperSettingActivity extends AppCompatActivity {

    private MineManager mineManager;

    private MineGame mineGame;

    private String username;

    private AccountManager accountManager;

    private User user;

    private int size = 10;

    private ScoreBoard scoreBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_sweeper_setting);
        addStartButtonListener();
        addLoadButtonListener();
        loadFromUser();
        if (mineManager == null){
            mineManager = new MineManager(size);
            mineManager.initData();
        }
        if (mineGame == null){
            mineGame = new MineGame();
        }
        mineGame.setMineManager(mineManager);
        addSeekBarListener();
        saveToUser();
    }

    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener((v) ->{
            Intent tmp = new Intent(this, MineGameActivity.class);
            tmp.putExtra("username", username);
            tmp.putExtra("scoreboard", scoreBoard);
            mineManager = new MineManager(size);
            mineManager.initData();
            mineGame.setMineManager(mineManager);
            mineGame.setPositions(null);
            saveToUser();
            Toast.makeText(this, "Starting New Game...", Toast.LENGTH_SHORT).show();
            startActivity(tmp);
        });
    }

    private void addLoadButtonListener() {
        Button startButton = findViewById(R.id.LoadButton);
        startButton.setOnClickListener((v) ->{
            Intent tmp = new Intent(this, MineGameActivity.class);
            tmp.putExtra("username", username);
            tmp.putExtra("scoreboard", scoreBoard);
            Toast.makeText(this, "Loading Game...", Toast.LENGTH_SHORT).show();
            startActivity(tmp);
        });
    }

    private void loadFromUser(){
        Intent intent;
        intent = getIntent();
        username = (String) intent.getExtras().get("username");
        accountManager = AccountManager.getAm();
        scoreBoard = (ScoreBoard) intent.getExtras().get("scoreboard");
        user = accountManager.getUser(username,this);
        try {
            mineGame = user.getMineGame();
            size = mineGame.getSize();
            mineManager = mineGame.getMineManager();
        }
        catch (NullPointerException e){
            Log.e("","There are no save gridGame or gridManager!");
        }

    }

    private void saveToUser(){
        mineGame.setMineManager(mineManager);
        mineGame.setSize(size);
        user.setMineGame(mineGame);
        accountManager.m.replace(username, user);
        accountManager.saveToFile1(this);

    }

    private void addSeekBarListener() {
        SeekBar seekBar = findViewById(R.id.boardSize);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i == 0) {
                    size = 10;
                    Toast.makeText(MineSweeperSettingActivity.this, "Set size to 10*10", Toast.LENGTH_SHORT).show();
                }else if (i == 1){
                    size = 15;
                    Toast.makeText(MineSweeperSettingActivity.this, "Set size to 15*15", Toast.LENGTH_SHORT).show();
                }else if (i == 2){
                    size = 20;
                    Toast.makeText(MineSweeperSettingActivity.this, "Set size to 20*20", Toast.LENGTH_SHORT).show();
                }else if (i == 3){
                    size = 25;
                    Toast.makeText(MineSweeperSettingActivity.this, "Set size to 25*25",Toast.LENGTH_SHORT).show();
                }else{
                    size = 30;
                    Toast.makeText(MineSweeperSettingActivity.this, "Set size to 30*30",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
