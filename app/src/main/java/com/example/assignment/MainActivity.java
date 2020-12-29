package com.example.assignment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.Adapters.ExpandAdapter;
import com.example.assignment.Models.Root;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fetching Json from Assets Folder
        new Thread() {
            @Override
            public void run() {
                super.run();
                String strJson = loadJsonFromAssert();
                Log.d("TAG", strJson);
                Gson gson = new GsonBuilder().create();
                Root[] root = gson.fromJson(strJson, Root[].class);
                runOnUiThread(() -> {
                    //Dynamically creating views
                    ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

                    getSupportActionBar().setTitle("Choose Equipment");
                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    RecyclerView recyclerView = new RecyclerView(MainActivity.this);
                    recyclerView.setBackgroundColor(Color.LTGRAY);
                    recyclerView.setLayoutParams(layoutParams);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    //setting adapter to recyclerView
                    recyclerView.setAdapter(new ExpandAdapter(root, MainActivity.this));
                    constraintLayout.addView(recyclerView);

                });
            }
        }.start();


    }

    private String loadJsonFromAssert() {
        String json = null;
        try {
            InputStream is = getAssets().open("assignment.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return json;
    }
}