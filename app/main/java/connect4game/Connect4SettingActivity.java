package connect4game;

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

public class Connect4SettingActivity extends AppCompatActivity {
    Button btn_Game;
    Intent intent;
    String username;
    AccountManager am;
    User user;
    GridGame gridGame;
    GridManager gridManager;
    GridManager gridManagerPVP;
    String difficulty = "Beginner";
    String theme = "classic";
    ScoreBoard GameScoreBoard;

    /**
     * @param savedInstanceState
     * create the setting interface where we can
     * change theme, pve difficulties, and start pvp game.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect4__setting_);

        btn_Game = (Button)findViewById(R.id.play);

        loadFromUser();
        loadFromUserPVP();
        if (gridManager == null){
            gridManager = new GridManager(6,7);
        }
        if (gridManagerPVP == null){
            gridManagerPVP = new GridManager(6,7);
        }
        if (gridGame == null){
            gridGame = new GridGame();
        }
        gridGame.setGridManager(gridManager);
        gridGame.setGridManagerPVP(gridManagerPVP);
        saveToUser();
        saveToUserPVP();

        addStartC4ButtonListener();
        addStartC42ButtonListener();
        addSeekBarListener();
        addPokemonButtonListener();
        addClassicButtonListener();



        // Register the Login button to click listener
        // Whenever the button is clicked, onClick is called
        //btn_Game.setOnClickListener((View.OnClickListener) this);
    }

    /**
     * Activate the start button.
     */
    private void addStartC4ButtonListener() {
        Button startC4Button = findViewById(R.id.play);
        startC4Button.setOnClickListener(v -> {if(v.getId()== R.id.play){
            Intent intent = new Intent(this, Connect4ComputerAIActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("difficulty", difficulty);
            intent.putExtra("theme", theme);
            intent.putExtra("scoreboard", GameScoreBoard);
            startActivity(intent);
            }
        });
    }

    /**
     * activate start pvp game button.
     */
    private void addStartC42ButtonListener() {
        Button startC4Button = findViewById(R.id.play2);
        startC4Button.setOnClickListener(v -> {if(v.getId()== R.id.play2){
            Intent intent = new Intent(this, Connect4GameActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("theme", theme);
            intent.putExtra("scoreboard", GameScoreBoard);
            startActivity(intent);
            }
        });
    }

    /**
     * Activate seek bar.
     */
    public void addSeekBarListener() {
        SeekBar seekBar = findViewById(R.id.difficulty);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i == 0) {
                    difficulty = "Beginner";
                    Toast.makeText(Connect4SettingActivity.this, difficulty, Toast.LENGTH_SHORT).show();
                } else {
                    difficulty = "ComputerAI";
                    Toast.makeText(Connect4SettingActivity.this, difficulty, Toast.LENGTH_SHORT).show();
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

    /**
     * update the gridManager in user's gridGame
     * and update the user in AccountManager, and
     * save the AccountManager.
     */
    private void saveToUser(){
        gridGame.setGridManager(gridManager);
        user.setGridGame(gridGame);
        am.m.replace(username, user);
        am.saveToFile1(this);
    }

    /**
     * get current user's gridGame and gridManager,
     * and set the game to the user's game.
     */
    private void loadFromUser(){
        intent = getIntent();
        username = (String) intent.getExtras().get("username");
        GameScoreBoard = (ScoreBoard) intent.getExtras().get("scoreboard");
        am = AccountManager.getAm();
        user = am.getUser(username, this);
        try {
            gridGame = user.getGridGame();
            gridManager = gridGame.getGridManager();
        }
        catch (NullPointerException e){
            Log.e("","There are no save gridGame or gridManager!");
        }
    }

    /**
     * update the gridManager in user's gridGame
     * and update the user in AccountManager, and
     * save the AccountManager.
     */
    private void saveToUserPVP(){
        gridGame.setGridManagerPVP(gridManagerPVP);
        user.setGridGame(gridGame);
        am.m.replace(username, user);
        am.saveToFile1(this);
    }

    /**
     * get current user's gridGame and gridManager,
     * and set the game to the user's game.
     */
    private void loadFromUserPVP(){
        intent = getIntent();
        username = (String) intent.getExtras().get("username");
        am = AccountManager.getAm();
        user = am.getUser(username, this);
        try {
            gridGame = user.getGridGame();
            gridManagerPVP = gridGame.getGridManagerPVP();
        }
        catch (NullPointerException e){
            Log.e("","There are no save gridGame or gridManager!");
        }
    }

    /**
     * Activate change to pokemon theme button.
     */
    public void addPokemonButtonListener(){
        Button pokemonButton = findViewById(R.id.Pokemon);
        pokemonButton.setOnClickListener(v -> {if(v.getId()== R.id.Pokemon){
            this.theme = "pokemon";
            Toast.makeText(Connect4SettingActivity.this, "Set theme to Pokemon", Toast.LENGTH_SHORT).show();}
        });
    }

    /**
     * Activate change to classic  theme button.
     */
    public void addClassicButtonListener(){
        Button classicButton = findViewById(R.id.Classic);
        classicButton.setOnClickListener(v -> {if(v.getId()== R.id.Classic){
            this.theme = "classic";
            Toast.makeText(Connect4SettingActivity.this, "Set theme to classic", Toast.LENGTH_SHORT).show();}
        });
    }

}
