package com.bitlogictechnologies.geoloc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Mike-berg on 02-07-2017.
 */

public class Login extends AppCompatActivity {
    private Button btn_login;
    private String pg_title="Login Form";
    private TextView txtPgTitle, txtUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);

        txtUser=(TextView) findViewById(R.id.username);

        txtPgTitle=(TextView) findViewById(R.id.lbl_pg_title);
        txtPgTitle.setText(pg_title);

        //display_user("mikeberg");
        btn_login=(Button)findViewById(R.id.email_sign_in_button);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadScreen();

            }
        });

    }
    //loads Data Capture screen
    public void loadScreen() {
        Intent intent = new Intent(this, DataCapture.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        System.exit(0);

    }

    //loads Data Capture screen
    public void loadTryScreen() {
        Intent intent = new Intent(this, TryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        System.exit(0);

    }





}
