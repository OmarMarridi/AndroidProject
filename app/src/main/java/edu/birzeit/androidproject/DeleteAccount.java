package edu.birzeit.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class DeleteAccount extends AppCompatActivity {
    private static final String url="http://192.168.1.240:80/android-server/delete_account.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteaccount);
        final EditText id=(EditText) findViewById(R.id.editText5);
       final  TextView res=(TextView) findViewById(R.id.textView9);
        Button deletebtn=(Button) findViewById(R.id.button6);
        Button back=(Button) findViewById(R.id.button9);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DeleteAccount.this,AdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.names().get(0).equals("deleted")){
                                res.setText("Delete Successful");
                            }
                            else if(jsonObject.names().get(0).equals("fail")) {
                                res.setText("Delete Failed Account Not found");
                            }
                            else{
                                res.setText("Check Internet Connection");
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
                    protected Map<String,String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap=new HashMap<String, String>();
                        hashMap.put("id",id.getText().toString());
                        hashMap.put("dummy","0");
                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });

    }

}
