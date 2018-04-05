package com.example.karti.podcastcentral;

/**
 * Created by karti on 1/22/2018.
 */

public class RSSLink {
    String title, url, genre;

    public RSSLink(){
        this.title   = "";
        this.url = "";
        this.genre = "";
    }
    public RSSLink(String title, String url, String genre){
        this.title = title;
        this.url = url;
        this.genre = genre;
    }
}
