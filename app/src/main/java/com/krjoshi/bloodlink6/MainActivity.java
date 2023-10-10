package com.krjoshi.bloodlink6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button button1 ;
    Button button2;
    TextView textView;
    EditText editText;
    String url = "http://192.168.0.216:8080/member";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> donors = new ArrayList<>();
        donors.add("Khem Raj ");
        textView = findViewById(R.id.textView);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        editText = findViewById(R.id.editTextText);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://192.168.0.216:8080/userlogin";
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                Toast.makeText(MainActivity.this, "click gare", Toast.LENGTH_SHORT).show();
                JSONObject jsonRequest = new JSONObject();
                try {
                    jsonRequest.put("phoneNo", "9812637355");
                    jsonRequest.put("password", "suman@123");
                } catch (JSONException e) {
                    editText.setText(e.toString());
                }
                editText.setText(jsonRequest.toString());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonRequest, response -> Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_LONG).show(), new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
// Add the request to the Volley request que
                requestQueue.add(jsonObjectRequest);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest
                        jsonArrayRequest
                        = new JsonArrayRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response)
                            {
                                Toast.makeText(getApplicationContext()," length of response "+response.length(),Toast.LENGTH_SHORT).show();
                                for(int i = 0; i <response.length(); i++)
                                {
                                    try {
                                        JSONObject responseObject = response.getJSONObject(i);
                                        String k = responseObject.getString("firstName");
                                        Toast.makeText(MainActivity.this, k, Toast.LENGTH_SHORT).show();


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, //
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
//                               textView.setText(error.toString());
                                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
                requestQueue.add(jsonArrayRequest);
            }
        });
    }




}

