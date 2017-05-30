package edu.birzeit.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static edu.birzeit.androidproject.R.styleable.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button goto_login = (Button) findViewById(R.id.button2);
       goto_login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent= new Intent(MainActivity.this,LoginActivity.class);
               startActivity(intent);
                finish();
           }
       });

    }




}
