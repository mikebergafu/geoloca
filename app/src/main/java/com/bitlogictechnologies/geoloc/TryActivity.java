package com.bitlogictechnologies.geoloc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mike-berg on 04-07-2017.
 */

public class TryActivity extends AppCompatActivity {
    Entities StudentEntity = new Entities();
    TableController tc=new TableController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.try_ui);
        final EditText txt_firstname = (EditText) findViewById(R.id.txt_firstname);
        final EditText txt_email = (EditText) findViewById(R.id.txt_email);
        Button btn_add = (Button) findViewById(R.id.btn_view);
        Button btn_create = (Button) findViewById(R.id.btn_create);

        btn_create.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TryActivity.this, "Started.", Toast.LENGTH_SHORT).show();
            }
        });
        btn_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String studentFirstname = txt_firstname.getText().toString();
                String studentEmail = txt_email.getText().toString();

                //StudentEntity.firstname=studentFirstname;
               // StudentEntity.email=studentEmail;


                boolean createSuccessful = tc.create(StudentEntity);

                if(createSuccessful){
                    Toast.makeText(TryActivity.this, "Student information was saved.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TryActivity.this, "Unable to save student information.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
