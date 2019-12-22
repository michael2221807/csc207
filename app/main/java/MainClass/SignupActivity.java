package MainClass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fall2018.csc2017.slidingtiles.R;

/**
 * the sign up Activity class used for signing up user
 */
public class SignupActivity extends AppCompatActivity {

    private static AccountManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setupRegisterButtonListener();

        am = AccountManager.getAm();
    }

    /**
     * set up Register Button
     */
    private void setupRegisterButtonListener() {
        Button registerButton = findViewById(R.id.register_button);
        EditText newUserName = findViewById(R.id.newusername);
        EditText newPassword = findViewById(R.id.newpassword);
        TextView register_massageBox = findViewById(R.id.register_messageBox);

        registerButton.setOnClickListener((v) -> {
            if (!am.contains(newUserName.getText().toString(), this)) {
                am.signUp(newUserName.getText().toString(), newPassword.getText().toString(), this);
                Intent tmp = new Intent(this, LoginActivity.class);
                startActivity(tmp);
            }
            else register_massageBox.setText("InValid Username.");
        });
    }
}
