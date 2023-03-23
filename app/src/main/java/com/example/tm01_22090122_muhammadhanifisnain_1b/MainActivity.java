package com.example.tm01_22090122_muhammadhanifisnain_1b;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private TextView myResponse;
    private Button myButton;
    private RequestQueue myQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myResponse = findViewById(R.id.txtResponse);
        myButton = findViewById(R.id.btnSubmit);
        myQueue = Volley.newRequestQueue(this);
    }

    public void GetJSON(View v){
        getRequest();
    }

    private void getRequest(){
        String url = "https://harber.mimoapps.xyz/api/getaccess.php";
        JSONArray jsonArray=null;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);

                                String message = obj.getString("Message");
                                String status = obj.getString("Status");
                                String comment = obj.getString("Comment");


                                myResponse.append("Message: " + message + "\n" +
                                        "Status: " + status + "\n" +
                                        "Comment: " + comment + "\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        myQueue.add(request);

    }

    public void clikexit( View v){
        finish();
    }
}