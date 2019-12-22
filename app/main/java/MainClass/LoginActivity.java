package MainClass;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;

        import fall2018.csc2017.slidingtiles.R;

/**
 * the LoginActivity that is used to login user
 */
public class LoginActivity extends AppCompatActivity {
    private static AccountManager am;
    public static final String Score_file = "Score_info.ser";

    public ScoreBoard scoreboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        loadFromFile(Score_file);
        setLogInButtonListener();
        setSignUpButtonListener();

        am = AccountManager.getAm();

    }

    /**
     * activate login button
     */
    private void setLogInButtonListener(){
        Button addLogInButton = findViewById(R.id.login_button);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        TextView messageBox = findViewById(R.id.messageBox);

        addLogInButton.setOnClickListener((v) -> {
            if (am.login(username.getText().toString(), password.getText().toString(), this)) {
                Intent tmp = new Intent(this, GameCenterActivity.class);
                loadFromFile(Score_file);
                tmp.putExtra("username", username.getText().toString());
                tmp.putExtra("scoreboard" ,scoreboard);
                saveToFile(Score_file);
                startActivity(tmp);
            } else {
                messageBox.setText("Failed!");

            }
        });
    }

    /**
     * activate sign up button
     */
    private void setSignUpButtonListener(){
        Button addSignUpButton = findViewById(R.id.SignUp_button);
        addSignUpButton.setOnClickListener((v) ->{
            saveToFile(Score_file);
            Intent tmp = new Intent(this, SignupActivity.class);
            startActivity(tmp);

        });
    }

    /**
     * load from file
     * @param fileName the save file
     */
    private void loadFromFile(String fileName) {

        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                scoreboard = (ScoreBoard) input.readObject();
                if (scoreboard == null){
                    scoreboard = new ScoreBoard();
                }
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
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(scoreboard);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
