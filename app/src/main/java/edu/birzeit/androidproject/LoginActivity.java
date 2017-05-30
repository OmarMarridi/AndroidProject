package edu.birzeit.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class LoginActivity extends AppCompatActivity {
    private static final String url = "http://192.168.1.240:2000/android-server/user_authentication.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    private final String username = "o";//admin username
    private final String password = "1";//admin password
    private String dummy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button) findViewById(R.id.button);
        requestQueue = Volley.newRequestQueue(this);
        final EditText user_name = (EditText) findViewById(R.id.editText);
        final EditText user_password = (EditText) findViewById(R.id.editText2);
        final TextView alert = (TextView) findViewById(R.id.textView);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user_name.getText().toString().equals(username) && user_password.getText().toString().equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    startActivity(intent);
                    finish();
                } else if (user_name.getText().toString() != null && user_password.getText().toString() != null && !user_name.getText().toString().isEmpty() && !user_password.getText().toString().isEmpty()) {
                    request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.names().get(0).equals("match")) {
                                    dummy=user_name.getText().toString();
                                    Intent intent = new Intent(LoginActivity.this, SuperMarketActivity.class);

                                    intent.putExtra("username",dummy);
                                    startActivity(intent);
                                    finish();
                                } else if(jsonObject.names().get(0).equals("fail")){
                                        alert.setText("Authentication Failed, Check username or Internet");
                                }else {
                                    alert.setText("Authentication Failed, Check username or Internet");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("email", user_name.getText().toString());
                            hashMap.put("password", user_password.getText().toString());
                            return hashMap;
                        }
                    };
                    requestQueue.add(request);
                }else{alert.setText("Authentication Failed");}
            }

        });

    }
}