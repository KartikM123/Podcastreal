package com.example.karti.podcastcentral;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PodcastList extends AppCompatActivity {
    Button bNewPodcast;
    String genre;
    ListAdapter bAdapter;
    RSSItem[] feed = new RSSItem[20];
    String titles [] = new String [20];
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast_list);
        url = "http://rss.nytimes.com/services/xml/rss/nyt/World.xml";
        for (int i = 0; i < 20; i++){
            titles[i] = "Loading";
            feed[i] = new RSSItem();
        }
        RetrieveFeedTask rf = new RetrieveFeedTask();
        rf.execute();


        bAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);

        ListView lvPodcasts = (ListView) findViewById(R.id.lvPodcasts);

        bNewPodcast = (Button) findViewById(R.id.bNewPodcast);
        lvPodcasts.setAdapter(bAdapter);

        Bundle genreData = getIntent().getExtras();


        for (int i = 0; i < 20; i++){
            if (feed[i].title.equals(titles[i]) ){
                System.out.println("value match at index " + i );
            }
            System.out.println("no value match at index " + i + "| title : " + feed[i].title + "stringlist: " + titles[i] );

        }
        if (genreData != null) {
            genre = genreData.getString("Genre");
        } else {
            System.out.println("Extra for Genre not found!");
        }


        lvPodcasts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String title= titles[position];
                System.out.println("TITLE OF WEBPAGE: " + title + "AT INDEX: " + position);
                System.out.println();
                System.out.println();
                String address = "www.google.com";
                for (int i = 0; i < 20; i++){
                    if (feed[i].title.equals(title) ){
                        address = feed[i].link;
                    }
                }
                Uri uriUrl = Uri.parse(address);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                // Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                //        .setAction("No action", null).show();
            }
        });
       /* lvPodcasts.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String podcast_title = String.valueOf(parent.getItemAtPosition(position));
                        Intent i = new Intent ((View)findViewById(R.id.relativeview), PodcastData.class);
                        startActivity(i);
                        i.putExtra("Genre",genre);
                        i.putExtra("podcast_title",podcast_title);

                    }
                }
        );*/

    }

    public void goNewPodcast(View view) {
        Intent i = new Intent(this, PodcastList.class);
        startActivity(i);
        i.putExtra("Genre", genre);
    }

    public void goPodcastData(View view) {
        Intent i = new Intent(this, PodcastData.class);
        startActivity(i);
        i.putExtra("Genre", "Sports");
    }


    class RetrieveFeedTask extends AsyncTask<Integer, Void, Void> {
        private Exception exception;
        RSSItem xxp[];


        protected void onPostExecute() {
            //do stuff
            System.out.println("Success");
            for (int i = 0; i < 20; i++) {
                System.out.println(xxp[i].title);
            }

        }
        @Override
        protected Void doInBackground(Integer... params) {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            String webContents = "";
            StringBuilder sb = new StringBuilder();

            //https://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html
            try {
                //"http://rss.nytimes.com/services/xml/rss/nyt/World.xml"
                URL site = new URL(url);
                HttpURLConnection con = (HttpURLConnection) site.openConnection();
                con.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                //https://stackoverflow.com/questions/6343166/how-do-i-fix-android-os-networkonmainthreadexception
                while ((webContents = in.readLine()) != null) {
                    //System.out.println("Line Read" + webContents);
                    sb.append(webContents);
                }
                in.close();
                //see to maybe that disconnect variable should be within the webContents whileloop
                con.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                xxp = parse(sb.toString());
                System.out.println(sb.toString());

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public boolean hasValue (String str, RSSItem [] rs, int index){
            for (int i = 0; i < index; i++){
                if(rs[i].title.equalsIgnoreCase(str)){
                    return true;
                }
            }
            return false;
        }
        public RSSItem[] parse(String file) throws XmlPullParserException, IOException {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(new StringReader(file));
            int eventType = parser.getEventType();
            boolean intag = false;


            //if (isXML(file) == false) {
            //    System.out.println("URL not Valid.");
            //    return feed;
            //}
            int index = -1;
            int pointer = 0;
            // pointer values: title = 1, title = 2, title 3

            while (eventType != XmlPullParser.END_DOCUMENT && index < 20) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("title")) {
                        if ( index >= 0 ){
                            if (feed[index].link.equals("") || hasValue(feed[index].title,feed, index)) {
                                if ( hasValue(feed[index].title,feed,index)){
                                    System.out.println("DUPLICATE OF VALUE " + feed[index].title);
                                }
                                feed[index].title  = "";
                                feed[index].description  = "";
                                feed[index].link = "";
                                index--;
                            }
                        }
                        index++;
                        pointer = 1;
                        intag = true;
                        System.out.println("TITLEFOUND " + "at index" + index + "and pointer" + pointer);

                    } else if (parser.getName().equals("link")) {
                        pointer = 2;
                        System.out.println("LINKSTART");
                        intag = true;
                    } else if (parser.getName().equals("description")) {
                        System.out.println("DESCRIPTIONSTART");
                        pointer = 3;
                        intag = true;
                    }



        //            System.out.println("TAG: " + parser.getName());


                } else if (eventType == XmlPullParser.TEXT) {
                    System.out.println("Index text found: " + pointer + " text: " + parser.getText());
                        if (index >= 0) {
                            if (pointer == 1) {
                                feed[index].title = parser.getText();
                            } else if (pointer == 2) {
                                feed[index].link = parser.getText();
                                System.out.println("Add to index " + index);
                            } else if (pointer == 3) {
                                feed[index].description = parser.getText();
                            }
                        }


                   // System.out.println("TEXT: " + parser.getText());


                } else if (eventType == XmlPullParser.END_TAG) {
                    if (pointer <= 3 && pointer > 0) {
                        intag = false;
                    }
                    pointer = 0;

                }

                eventType = parser.next();
            }

            for (int i = 0;  i<20; i++){
                titles[i] = feed[i].title;
                //System.out.println("Title " + i + "  " + feed[i].title);
            }
            return feed;
        }

        public boolean isXML(String url) {
            String ending = url.substring(url.length() - 5, url.length() - 1);
            return (ending.equalsIgnoreCase(".XML"));
        }


    }

}

