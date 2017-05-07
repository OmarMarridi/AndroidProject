package edu.birzeit.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by OmarMarridi on 5/5/2017.
 */
public class CreateAccount extends AppCompatActivity {
    private static final String url="http://192.168.1.240:80/android-server/create_account.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccounts);
        final EditText useremail=(EditText) findViewById(R.id.editText3);
        final EditText pass=(EditText) findViewById(R.id.editText4);
        Button create=(Button) findViewById(R.id.button3);
        final TextView responseMessage=(TextView)  findViewById(R.id.textView8);
        Button back=(Button) findViewById(R.id.button8);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(CreateAccount.this,AdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // if(tempUE.equals("")&&tempUP.equals("")){
                 //   responseMessage.setText("Wrong Input,Or Check Internet Connection");
              //  }
                request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.names().get(0).equals("empty")){
                                responseMessage.setText("Wrong Input Inserted please try again");
                            }
                            if(jsonObject.names().get(0).equals("match")){
                                responseMessage.setText("Account Already Exist Creation Failed");
                            }
                            else if(jsonObject.names().get(0).equals("success")){
                                responseMessage.setText("Creation Successful");
                            }
                            else{
                                responseMessage.setText("Creation Failed Wrong Input");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap=new HashMap<String, String>();
                        hashMap.put("email",useremail.getText().toString());
                        hashMap.put("password",pass.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue.add(request);
            }
        });
    }
}
