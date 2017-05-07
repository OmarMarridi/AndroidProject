package edu.birzeit.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static edu.birzeit.androidproject.R.styleable.View;

public class LoginActivity extends AppCompatActivity {
    private final String username="omarmarridi@admin.com";//admin username
    private final String password="qwerty123";//admin password
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button) findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText user_name = (EditText) findViewById(R.id.editText);
                EditText user_password = (EditText) findViewById(R.id.editText2);
                if(user_name.getText().toString().equals(username)&&user_password.getText().toString().equals(password)){
                    Intent intent= new Intent(LoginActivity.this,AdminActivity.class);
                    startActivity(intent);
                    finish();
                }
              /*  else if((user_name.getText()!=null)&&(user_password.getText()!=null)){
                        //check user validty ,create sql table for offers,link offers with users,offer contain array of pic and detail

                }
                */
                     else{
                    TextView alert=(TextView) findViewById(R.id.textView);
                    alert.setText("User Information Incorrect! Check your Internet Connection");
                }
            }
        });

    }
}
