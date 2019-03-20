package com.example.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BreedAdapter breedAdapter;
    private ArrayList<Breed> breedList;
    private RequestQueue requestQueue;
    private BreedDAO breedDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        breedList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        breedDAO = BreedDB.getDatabase(getApplicationContext()).breedDAO();
        parseJSON();
        Button button = findViewById(R.id.but1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        breedDAO.insertAll(breedList);
                    }
                }).start();
            }
        });
    }

    private void parseJSON() {
        String URL = "https://api.thecatapi.com/v1/breeds/";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                String name = obj.getString("name");
                                String temperament = obj.getString("temperament");
                                String desc = obj.getString("description");
                                breedList.add( new Breed(name, temperament, desc));
                            }
                            breedAdapter = new BreedAdapter(MainActivity.this, breedList);
                            recyclerView.setAdapter(breedAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                               List<Breed> breeds = breedDAO.getAllBreeds();
                               breedAdapter = new BreedAdapter(MainActivity.this, breeds);
                               recyclerView.setAdapter(breedAdapter);
                            }
                        }).start();
                    }
        });
        requestQueue.add(request);
    }

}
