package com.example.simplecursoradapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shenjiaqi on 2017/8/4.
 */

public class InfoDatabaseHelper extends SQLiteOpenHelper {

    private static final String dataBaseName = "ContactsInfo";
    private static final int version = 1;

    private final String DB_CREATE_CONTACTS = "create table if not exists "
            + Person.PERSON_INFO_TABLE + "("+"_id INTEGER primary key autoincrement,  "
            + Person.NAME + " TEXT,"
            + Person.PHONENUMBER + " TEXT)";
    @SuppressLint("NewApi")
    public InfoDatabaseHelper (Context context){
        super(context, dataBaseName, null, version, null);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(DB_CREATE_CONTACTS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
