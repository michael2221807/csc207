package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import MainClass.AccountManager;
import MainClass.ScoreBoard;
import MainClass.User;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "save_file.ser";
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";
    /**
     * The board manager.
     */
    /**
     * The boardManager in this activity.
     */
    private BoardManager boardManager;

    private TilesGame TilesGame;
    /**
     * The user in this activity.
     */
    private String username;

    private User user;
    /**
     * The GameScoreBoard of this activity.
     */
    private ScoreBoard scoreboard;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_);

        Intent intent = getIntent();
        AccountManager am = AccountManager.getAm();
        username = (String) intent.getExtras().get("username");
        user = am.getUser(username, this);
        if (user.getTilesGame() == null){
            user.setTileGame(new TilesGame());
        }

//        Log.e("", Integer.toString(user.getUserComplexity()));
        try{
            if (user.getTilesGame()!= null)
                this.TilesGame = user.getTilesGame();
                boardManager = user.getTilesGame().getBoardManager();
        }
        catch(Exception e){
            Log.e("","No save file to load!");
        }
        am.m.replace(username, user);
        am.saveToFile1(this);
        scoreboard = (ScoreBoard) intent.getExtras().get("scoreboard");
        boardManager = user.getTilesGame().getBoardManager();
        TilesGame = user.getTilesGame();
        saveToFile(SAVE_FILENAME);
        saveToFile(TEMP_SAVE_FILENAME);
        saveToScoreFile("Score_info.ser");

        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addSettingButtonListener();
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.setBoardSize(user.getUserComplexity());
//                scoreboard.set_user_name(username);
                boardManager = new BoardManager();
                user.getTilesGame().setBoardManager(boardManager);
                TilesGame.setBoardManager(boardManager);



                switchToGame();
            }
        });
    }



    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile(SAVE_FILENAME);

                if (TilesGame.getBoardManager() != null){
                    saveToFile(TEMP_SAVE_FILENAME);
                    makeToastLoadedText();
                    switchToGame();
                }

                else {
                    Log.e("", "empty TileGame!");
                    Toast.makeText(StartingActivity.this, "You have no saved games.", Toast.LENGTH_LONG).show();
                }


            }
        });
    }



    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile(SAVE_FILENAME);
                saveToFile(TEMP_SAVE_FILENAME);
                makeToastSavedText();
            }
        });
    }


    /**
     * Activate the ScoreBoard button.
     */
    private void addSettingButtonListener(){
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton rb2 = findViewById((R.id.rb2));
        AccountManager am = AccountManager.getAm();
        rb2.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb1){
                    user.setComplexity(3);
                    am.m.put(username, user);
                    am.saveToFile1(context);
                }else if (i == R.id.rb2){
                    user.setComplexity(4);
                    am.m.put(username, user);
                    am.saveToFile1(context);
                }else {
                    user.setComplexity(5);
                    am.m.put(username, user);
                    am.saveToFile1(context);
                }
            }
        });

    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }
    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadFromFile(TEMP_SAVE_FILENAME);
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        saveToFile(StartingActivity.TEMP_SAVE_FILENAME);
        tmp.putExtra("username", username);
        tmp.putExtra("scoreboard", scoreboard);
        startActivity(tmp);
    }
    /**
     * Switch to the GameActivity view to play the game.
     */

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) {

        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                TilesGame= (TilesGame) input.readObject();
                scoreboard = (ScoreBoard) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, this.MODE_PRIVATE));
            outputStream.writeObject(TilesGame);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        AccountManager am = AccountManager.getAm();
        User user1 = am.getUser(user.userName, this);
        user1.setTileGame(TilesGame);
        am.m.replace(user.userName, user1);
        am.saveToFile1(this);

    }
    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */

    /**
     * Save the score to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToScoreFile(String fileName) {
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