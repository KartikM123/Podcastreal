package com.example.karti.podcastcentral;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Genres extends AppCompatActivity {
    String genre;
    Button bSports,bPolitics,bScience,bComedy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);

        bSports = (Button) findViewById(R.id.bSports);
        bPolitics = (Button) findViewById(R.id.bPolitics);
        bScience = (Button) findViewById(R.id.bScience);
        bComedy = (Button) findViewById(R.id.bComedy);


    }
    public void goSportsPodcasts(View view) {
        Intent i = new Intent (this, PodcastList.class);
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i.putExtra("Genre","Sports");
        startActivity(i);

    }

    public void goComedyPodcasts(View view) {
        Intent i = new Intent (this, PodcastList.class);
        startActivity(i);
        i.putExtra("Genre","Comedy");

    }

    public void goPoliticalPodcast(View view) {
        Intent i = new Intent (this, PodcastList.class);
        startActivity(i);
        i.putExtra("Genre","Political");

    }
    public void goMenu(View view){
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }

    public void goSciencePodcasts(View view) {
        Intent i = new Intent (this, PodcastList.class);
        startActivity(i);
        i.putExtra("Genre","Science");

    }

}
