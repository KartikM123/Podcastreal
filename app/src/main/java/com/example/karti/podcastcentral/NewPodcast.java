package com.example.karti.podcastcentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewPodcast extends AppCompatActivity {

    EditText etName, etAuthor, etRSS, etWebsite;
    Button bAddPodcast, bBack;
    TextView tvError;
    String genre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_podcast);

        etName = (EditText) findViewById(R.id.etName);
        etAuthor = (EditText) findViewById(R.id.etAuthor);
        etRSS = (EditText) findViewById(R.id.etRSS);
        etWebsite = (EditText) findViewById(R.id.etWebsite);
        bAddPodcast = (Button) findViewById(R.id.bNewPodcast);
        bBack = (Button) findViewById(R.id.bBack);
        tvError = (TextView) findViewById(R.id.tvError);

        String name = etName.getText().toString();
        String author = etAuthor.getText().toString();
        String rss = etRSS.getText().toString();
        String website = etWebsite.getText().toString();
        Podcast p = new Podcast(name, author, website);

        Bundle genreData = getIntent().getExtras();
        if (genreData != null){
            genre = genreData.getString("Genre");
        } else {
            System.out.println("Extra for Genre not found!");
        }
        if (rssConnect(rss)) {
            p.setRssfeed(rss);
        }
    }

    public boolean rssConnect(String rssfeed){
        //INCLUDE CODE ON HOW TO CHECK WHETHER THE RSS FEED CONNECTION IS SUCCESSFUL!
        return true;
    }

    public void goBack(View view){
        Intent i = new Intent (this, PodcastList.class);
        startActivity(i);

        i.putExtra("Genre",genre);
    }
}
