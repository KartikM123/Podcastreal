package com.example.karti.podcastcentral;

/**
 * Created by karti on 10/17/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RSS.db";
    public static final String TABLE_PRODUCTS = "RSSItem";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_GENRE= "genre";
    public static final String COLUMN_URL= "url";
    public static final String COLUMN_TITLE= "title";


    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table with tablename
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                //the following words add the traits to the product
                // ONLY ONE PRIMARY KEY
                COLUMN_GENRE+ " TEXT "+
                COLUMN_TITLE+ " TEXT "+
                COLUMN_URL+" TEXT " +
                ");";
        //remember database already input
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS );
        onCreate(db);
    }

    public void addProduct (RSSLink link){
        ContentValues values = new ContentValues();
        values.put(COLUMN_GENRE, link.genre);
        values.put(COLUMN_URL, link.url);
        values.put(COLUMN_TITLE, link.title);
        SQLiteDatabase db = getWritableDatabase();
        //ctrl + q gets documentation
        db.insert(TABLE_PRODUCTS,null, values);

    }

    //delete a product from the database
    public void deleteProduct (String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_TITLE +
                "=\"" + productName + "\";");

    }

    //print DataBase
    public String databaseToString (){
        SQLiteDatabase db = getWritableDatabase();
        String dbString = "";
        String query = "SELECT * FROM " +  TABLE_PRODUCTS + " WHERE 1";
        //* means all. 1 means "where every condition is met"

        //cursor points to locations in table
        Cursor c = db.rawQuery(query,null);
        //moves cursor to first row in results
        c.moveToFirst();

        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("title"))!=null){
                dbString += c.getString(c.getColumnIndex("title"));
                dbString+="\n";
                //last part just puts it on a new line
            }
        }
        db.close();
        return dbString;


    }

    public ArrayList<RSSLink> databaseToArrayList (){
        ArrayList<RSSLink> dbvalues = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String dbString = "";
        String query = "SELECT * FROM " +  TABLE_PRODUCTS + " WHERE 1";
        //* means all. 1 means "where every condition is met"

        //cursor points to locations in table
        Cursor c = db.rawQuery(query,null);
        //moves cursor to first row in results
        c.moveToFirst();

        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("title"))!=null){
                RSSLink rowval = new RSSLink();
                rowval.title = c.getString(c.getColumnIndex("title"));
                rowval.genre = c.getString(c.getColumnIndex("genre"));
                rowval.url = c.getString(c.getColumnIndex("url"));
                dbvalues.add(rowval);
                //dbString+="\n";
                //last part just puts it on a new line
            }
        }
        db.close();
        return  dbvalues;

    }

}
