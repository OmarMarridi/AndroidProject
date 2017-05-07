package edu.birzeit.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static edu.birzeit.androidproject.R.styleable.View;

public class AdminActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button createAccount=(Button) findViewById(R.id.button4);
        Button deleteAccount=(Button) findViewById(R.id.button5);
        Button logOut=(Button) findViewById(R.id.button7);
        Button showUsers=(Button) findViewById(R.id.button10);
        showUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminActivity.this,ShowUsers.class);
                startActivity(intent);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminActivity.this,CreateAccount.class);
                startActivity(intent);
            }
        });
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminActivity.this,DeleteAccount.class);
                startActivity(intent);
            }
        });
    }
}