package com.example.shaheed.takeaclimbchallenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by shaheed on 4/7/18.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Records_DB";
    public static final String TB_NAME = "Medicine_TB";
    public static final String TB_NAME_2 = "Users";
    public static final int DB_VER = 2;
    SQLiteDatabase db;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +  TB_NAME + " " +
                "( _id INTEGER primary key autoincrement, name TEXT not null," +
                "interval DATE not null," + "medName TEXT not null," +
                " startDate DATE not null, endDate DATE not null, description TEXT not null);");
        /*db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME_2 + " " +
        );*/
    }

    public boolean insertEntry (String interval, String medName,
                                String startDate, String endDate, String description) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("interval", interval);
        contentValues.put("medName", medName);
        contentValues.put("startDate", String.valueOf(startDate));
        contentValues.put("endDate", String.valueOf(endDate));
        contentValues.put("description", description);
        db.insert(TB_NAME, null, contentValues);
        return true;
    }

    /*public boolean removeEntry (String name_val, int shoulder_val, int trouser_val,
                                  int shirt_val, int hand_val, long ph_val) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name_val);
        contentValues.put("shoulder", shoulder_val);
        contentValues.put("trouser", trouser_val);
        contentValues.put("shirt", shirt_val);
        contentValues.put("hand", hand_val);
        contentValues.put("phone_number", ph_val);
        db.delete(TB_NAME, null, contentValues);
        return true;
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(db);
    }
}
