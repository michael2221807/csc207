package MainClass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import fall2018.csc2017.slidingtiles.R;

/**
 * ScoreActivity is a Activity that use to display Various Score
 */
public class ScoreActivity extends AppCompatActivity {
    private ScoreBoard gamescoreboard;
    private String user_name;
    private User user;
    private TextView c41, c42, c43, m1, m2, m3, s1, s2, s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        setVariable();
        Intent intent = getIntent();
        AccountManager am = AccountManager.getAm();
        user_name = (String) intent.getExtras().get("username");
        user = am.getUser(user_name, this);
        gamescoreboard = (ScoreBoard) intent.getExtras().get("scoreboard");
        setScore();

    }

    /**
     * set different score variable
     */
    private void setVariable() {
        c41 = findViewById(R.id.userScore1);
        c42 = findViewById(R.id.userScore2);
        c43 = findViewById(R.id.userScore3);
        m1 = findViewById(R.id.userScore4);
        m2 = findViewById(R.id.userScore5);
        m3 = findViewById(R.id.userScore6);
        s1 = findViewById(R.id.userScore7);
        s2 = findViewById(R.id.userScore8);
        s3 = findViewById(R.id.userScore9);


    }

    /**
     * set to display top 3 personal score in each of the game
     */
    private void setScore() {
        ArrayList<Score> tilesList = gamescoreboard.getUserScoreBoard(user_name, "tiles");
        ArrayList<Score> MinesList = gamescoreboard.getUserScoreBoard(user_name,"mines");
        ArrayList<Score> connect4List = gamescoreboard.getUserScoreBoard(user_name, "connect4");
        ArrayList<TextView> connect4 = new ArrayList<>();
        Collections.addAll(connect4, c41,c42,c43);
        ArrayList<TextView> Mines = new ArrayList<>();
        Collections.addAll(Mines, m1, m2 ,m3);
        ArrayList<TextView> Tiles = new ArrayList<>();
        Collections.addAll(Tiles, s1,s2,s3);
        int start_index = 0;
        while(start_index <3){
            if(tilesList.size() > start_index ) {
                Tiles.get(start_index).setText(Integer.toString(tilesList.get(start_index
                ).getScore()));
            }
            else{
                Tiles.get(start_index).setText("empty");
            }
            if(MinesList.size() > start_index ){
                Mines.get(start_index).setText(Integer.toString((MinesList.get(start_index)).getScore()));

            }
            else{
                Mines.get(start_index).setText("empty");
            }

            if(connect4List.size() >start_index ){
                connect4.get(start_index).setText(Integer.toString(connect4List.get(start_index).getScore()));
            }
            else{
                connect4.get(start_index).setText("empty");
            }
            start_index++;
        }

    }

}
