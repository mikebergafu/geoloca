package com.bitlogictechnologies.geoloc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    //sets count for the screen loader
    private Timer t;
    //actual count incrementer, initialised to 0
    private int TimeCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_ui);
        //calls the login screen to load after all background works are done
        autoScreenLoader();
    }


    //Timer automatically loads various screens after some seconds
    public void autoScreenLoader(){
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        // you can set it to a textView to show it to the user to see the time passing while he is writing
                        TimeCounter++;
                        loadScreen();
                    }
                });

            }
        }, 1000, 1000); // 1000 means start from 1 sec, and the second 1000 is do the loop each 1 sec.
    }


    //loads login screen after
    public void loadScreen() {
        if (TimeCounter > 0) {
            Intent intent = new Intent(this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            t.cancel();
            finish();
            System.exit(0);
        }
    }
}
