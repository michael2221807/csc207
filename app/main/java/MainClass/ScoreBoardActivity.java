package MainClass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import fall2018.csc2017.slidingtiles.R;

public class ScoreBoardActivity extends AppCompatActivity{
    private ScoreBoard scoreBoard;
    private String userName;
    private User user;
    private TextView c41, c42, c43, c44, c45, s1, s2, s3, s4, s5, m1, m2, m3, m4, m5;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        setVariable();
        Intent intent = getIntent();
        AccountManager am = AccountManager.getAm();
        userName = (String) intent.getExtras().get("username");
        user = am.getUser(userName, this);
        scoreBoard = (ScoreBoard) intent.getExtras().get("scoreboard");
        setScore();
    }

    /**
     * set different score variable
     */
    private void setVariable(){
        c41 =  findViewById(R.id.c4first);
        c42 =  findViewById(R.id.c4second);
        c43 =  findViewById(R.id.c4third);
        c44 =  findViewById(R.id.c4fourth);
        c45 =  findViewById(R.id.c4fifth);
        s1 =  findViewById(R.id.tile1);
        s2 =  findViewById(R.id.tile2);
        s3 =  findViewById(R.id.tile3);
        s4 =  findViewById(R.id.tile4);
        s5 =  findViewById(R.id.tile5);
        m1 = findViewById(R.id.Mine1);
        m2 = findViewById(R.id.Mine2);
        m3 = findViewById(R.id.Mine3);
        m4 = findViewById(R.id.Mine4);
        m5 = findViewById(R.id.Mine5);
    }

    /**
     * set the top scores for 3 different games
     */
    private void setScore(){
        ArrayList<Score> listOfC4Scores = scoreBoard.getGameScoreBoard("connect4");
        ArrayList<Score> listOfMineScores = scoreBoard.getGameScoreBoard("mines");
        ArrayList<Score> listOfTileScores = scoreBoard.getGameScoreBoard("tiles");


        ArrayList<TextView> listOfC4Tw = new ArrayList<>();
        ArrayList<TextView> listOfMinesTw = new ArrayList<>();
        ArrayList<TextView> listOfTilesTw = new ArrayList<>();
        Collections.addAll(listOfC4Tw, c41, c42, c43, c44, c45);
        Collections.addAll(listOfMinesTw, m1, m2, m3, m4, m5);
        Collections.addAll(listOfTilesTw, s1, s2, s3, s4, s5);


        int start_index = 0;
        while (start_index < 5){
            if (listOfC4Scores.size() > start_index){
                listOfC4Tw.get(start_index).setText(Integer.toString(
                        listOfC4Scores.get(start_index).getScore()) + "     user: "+listOfC4Scores.get(start_index).getUsername());
                start_index ++;
            }
            else{
                listOfC4Tw.get(start_index).setText("empty");
                start_index ++;
            }
        }

        int start_index_mine = 0;
        while (start_index_mine < 5){
            if (listOfMineScores.size() > start_index_mine){
                listOfMinesTw.get(start_index_mine).setText(Integer.toString(
                        listOfMineScores.get(start_index_mine).getScore())+ "     user: "+ listOfMineScores.get(start_index_mine).getUsername());
                start_index_mine ++;
            }
            else{
                listOfMinesTw.get(start_index_mine).setText("empty");
                start_index_mine ++;
            }
        }

        int start_Tile = 0;
        while(start_Tile < 5){
            if (listOfTileScores.size() >start_Tile ){
                listOfTilesTw.get(start_Tile).setText(Integer.toString(
                        listOfTileScores.get(start_Tile).getScore()) + "     user: "+ listOfTileScores.get(start_Tile).getUsername());
                start_Tile++;
            }
            else{
                listOfTilesTw.get(start_Tile).setText("empty");
                start_Tile++;
            }
        }

    }



}
