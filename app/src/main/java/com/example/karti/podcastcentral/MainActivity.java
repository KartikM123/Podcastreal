package com.example.karti.podcastcentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //ListView lvPodcasts;
    Button bGenres, bFeatured;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bGenres = (Button) findViewById(R.id.bGenres);


    }

    public void goGenres(View view){
        Intent i = new Intent (this, Genres.class);
        startActivity(i);
    }
}

