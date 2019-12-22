package connect4game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import MainClass.AccountManager;
import MainClass.GameCenterActivity;
import MainClass.Score;
import MainClass.ScoreBoard;
import MainClass.User;
import fall2018.csc2017.slidingtiles.R;

public class Connect4ComputerAIActivity extends AppCompatActivity implements View.OnClickListener {


    TextView txt_Turn;
    Button btn_Undo;
    Button btn_Restart;
    Button btn_LoadBoard;
    private int rows = 6 ;
    private int columns =  7;
    private int buttons[] = {R.drawable.coin_silver, R.drawable.coin_gold};
    private String[] s_Turns = {"Silver", "Gold"};
    private int win_buttons[] = { R.drawable.coin_silver_win,R.drawable.coin_gold_win};
    private int temp_buttons[] ={ R.drawable.empty_bracket,R.drawable.coin_silver, R.drawable.coin_gold};
    private ComputerAI computerAI;
    private AccountManager am;
    private GridManager gridManager;
    private GridGame gridGame;
    private User user;
    private String username;
    private String difficulty;
    private String theme;
    private ScoreBoard GameScoreBoard;


    GridView gridview;
    /**
     * @param savedInstanceState
     * The method initialize the activity when it is created.
     * initialize undo button, load button, and start new game button
     * It setup the listener for this three button, and it create a
     * new gridview for this activity. Then it load gridGame for this user
     * Finally, it add button listener for each of the board buttons.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect4__game_);
        setTheme();
        Log.e("1", s_Turns[1]);


        txt_Turn = findViewById(R.id.txt_Turn);
        btn_Undo = findViewById(R.id.btn_Undo);
        btn_LoadBoard = findViewById(R.id.btn_LoadBoard);
        btn_Restart = findViewById(R.id.btn_Restart);
    // Register the Login button to click listener
    // Whenever the button is clicked, onClick is called

        btn_Undo.setOnClickListener(this);
        btn_Restart.setOnClickListener(this);
        btn_LoadBoard.setOnClickListener(this);


        gridview = findViewById(R.id.gridview);
        gridview.setNumColumns(7);
        gridview.setAdapter(new GridGenerator(this, rows*columns));
        txt_Turn.setText(s_Turns[0]);
        Log.e("","On create!");


        loadFromUser();
        gridview.setOnItemClickListener((parent, v, position, id) -> {
            computerAI = new ComputerAI(gridManager, difficulty);

            if(!gridManager.finished){
                int addPosition = gridManager.addToColumn(position);
                txt_Turn.setText(s_Turns[(gridManager.getCount()+1)%2]);
                if(addPosition<rows*columns){
                    makeMove(addPosition, parent);

                    finishMove(parent);}

                }


            if(!gridManager.finished){

                int addAiPosition = gridManager.addToColumn(computerAI.selectMove());
                txt_Turn.setText(s_Turns[(gridManager.getCount()+1)%2]);
                if(addAiPosition<rows*columns){
                    makeMove(addAiPosition, parent);

                    finishMove(parent);}

//                    }
            }


        });


    }
    /**
     * set theme for the connect 4 game by the setting passes from setting activity.
     */
    private void setTheme(){
        Intent intent = getIntent();
        this.theme = (String) intent.getExtras().get("theme");
        Log.e("",this.theme);
        if (this.theme.equals("pokemon")) {
            this.buttons = new int[]{R.drawable.pikachu, R.drawable.eevee};
            this.temp_buttons = new int[]{R.drawable.empty_bracket, R.drawable.pikachu, R.drawable.eevee};
            this.s_Turns = new String[] {"Pikachu's turn", "Eevee's turn"};
            this.win_buttons = new int[]{R.drawable.raichu, R.drawable.jolteon};
        }
    }

    /**
     * @param position the position user clicks.
     * @param parent the view of the game.
     * Make move and change the view of the game base on the user click.
     */
    private void makeMove(int position, AdapterView parent){
        ImageView chosen =  (ImageView) parent.getChildAt(position);
        chosen.setImageResource(buttons[getTurn()]);
    }

    /**
     * @param parent the view of the current game.
     * If the move will lead to ones victory, change the button background to the win button
     * and display the text for the winner or draw game.
     */
    private void finishMove(AdapterView parent){
        if(gridManager.finished){
            if(!gridManager.win.isEmpty())
                for(int i : gridManager.win){
                    ImageView chosen = (ImageView) parent.getChildAt(i);
                    chosen.setImageResource(win_buttons[getTurn()]);
                    Toast.makeText(Connect4ComputerAIActivity.this, "Game Over  "+
                                    s_Turns[getTurn()] + " Won",
                            Toast.LENGTH_SHORT).show();
                    btn_Undo.setEnabled(false);
                }
            else{
                Toast.makeText(Connect4ComputerAIActivity.this, "Game Over, Draw",
                        Toast.LENGTH_SHORT).show();
            }
            GameScoreBoard.addScore(new Score(gridManager.getScore(),username,
                    "connect4"));
            returnToGameActivity();

        }
    }

    /**
     * The method that will run when we quit this page.
     */
    public void onPause(){
        super.onPause();
        saveToUser();
        Toast.makeText(Connect4ComputerAIActivity.this, "Auto Saving...", Toast.LENGTH_SHORT).show();

    }

    /**
     * @param v the veiw of the current gme.
     * Action that will be apply when the specific button is click
     * Load the game that was auto saved when load game button is clicked
     * Start new game when Start New Game button is clicked
     * Undo the current step when Undo button is clicked.
     *
     */
    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.btn_LoadBoard){
            btn_LoadBoard.setEnabled(true);
            placeButtons(gridManager.getBoard());
        }
        else if(v.getId()== R.id.btn_Undo){
            btn_Undo.setEnabled(true);
            unDo();
        }
        else if(v.getId()== R.id.btn_Restart){
            txt_Turn.setText(s_Turns[0]);
            btn_Undo.setEnabled(true);
            gridManager.restart();
            for(int i = 0; i<rows*columns; i++){
                ImageView l = (ImageView) gridview.getChildAt(i);
                l.setImageResource(R.drawable.empty_bracket);
            }

        }


    }

    /**
     * undo the previous step and revert the last view for
     * the current player.
     */
    public void unDo(){
        if(gridManager.getCount()>-1){
            int pos = gridManager.unDo();
            ImageView v = (ImageView) gridview.getChildAt(pos);
            v.setImageResource(R.drawable.empty_bracket);
            int pos1 = gridManager.unDo();
            ImageView v1 = (ImageView) gridview.getChildAt(pos1);
            v1.setImageResource(R.drawable.empty_bracket);
        }

    }


    /**
     * @param b The board for the user.
     * place all the buttons according to the board and initialize
     * all the view.
     */
    public void placeButtons(int[][] b){
        gridManager.setBoard(b,0);
        gridManager.setCount(gridManager.getCount() + 1);
        for(int i = 0; i<rows;i++){
            for(int j = 0; j<columns; j++){
                //System.out.print(ge.getPosition(i,j));
                ImageView temp = (ImageView) gridview.getChildAt(gridManager.getPosition(i,j));
                temp.setImageResource(temp_buttons[b[i][j]]);
                //temp.setImageResource(R.drawable.coin_silver_win);

            }
            //System.out.println("");
        }

    }

    /**
     * @return return the current player of the game.
     */
    private int getTurn(){
        return gridManager.getCount()%2;
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
        Intent intent = getIntent();
        GameScoreBoard = (ScoreBoard) intent.getExtras().get("scoreboard");
        difficulty = (String) intent.getExtras().get("difficulty");
        username = (String) intent.getExtras().get("username");
        am = AccountManager.getAm();
        user = am.getUser(username, this);
        gridGame = user.getGridGame();
        gridManager = gridGame.getGridManager();
    }
    /**
     * Return to the GameCenter.
     */
    private void returnToGameActivity(){
        Intent intent = new Intent(this, GameCenterActivity.class);
        intent.putExtra("username" , user.userName);
        intent.putExtra("scoreboard", GameScoreBoard);
        startActivity(intent);
    }


}
