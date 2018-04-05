package com.example.karti.podcastcentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PodcastData extends AppCompatActivity {

    TextView tvPodcastTitle, tvDescription, tvEpisodesAvailable;
    Button bReturn, bRSSFeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast_data);


        tvPodcastTitle = (TextView) findViewById(R.id.tvPodcastTitle);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvEpisodesAvailable = (TextView) findViewById(R.id.tvEpisodesAvailable);
        bReturn = (Button) findViewById(R.id.bReturn);
        bRSSFeed = (Button) findViewById(R.id.bRSSFeed);
        String genre;
        Bundle genreData = getIntent().getExtras();
        if (genreData != null){
            genre = genreData.getString("Genre");
        } else {
            System.out.println("Extra for Genre not found!");
        }
        String podcast_title;
        if (genreData != null){
            genre = genreData.getString("podcast_title");
            tvPodcastTitle.setText(genre);
        } else {
            System.out.println("Extra for podcast_title not found!");
        }
    }

    public void goPodcastList(View view){
        Intent i = new Intent(this,PodcastList.class);
        startActivity(i);
    }
}
