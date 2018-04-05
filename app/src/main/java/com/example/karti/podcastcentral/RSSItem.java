package com.example.karti.podcastcentral;

/**
 * Created by karti on 1/1/2018.
 */

public class RSSItem {
    public String title, link, description;

    public RSSItem(String title, String link, String description){
        this.title = title;
        this.link = link;
        this.description = description;

    }
    public RSSItem(){
        this.title = "";
        this.link = "";
        this.description = "";
    }
}
