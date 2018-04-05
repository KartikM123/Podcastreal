package com.example.karti.podcastcentral;

/**
 * Created by karti on 11/8/2017.
 */

public class Podcast {
    public String name;
    public String author;
    public String website;
    public double length;
    private String rssfeed;

    public Podcast (String name, String author, String website){
        this.name = name;
        this.author = author;
        this.website = website;
        length = 0;
        rssfeed = "";
    }

    public String getRssfeed() {
        return rssfeed;
    }

    public void setRssfeed(String rssfeed) {

        this.rssfeed = rssfeed;
    }
}
