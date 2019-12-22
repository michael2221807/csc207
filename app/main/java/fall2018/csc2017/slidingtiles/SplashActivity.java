package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import MainClass.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(SplashActivity.this, LoginActivity.class);
                // intent=new Intent(SplashActivity.this,GameHouseActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}
