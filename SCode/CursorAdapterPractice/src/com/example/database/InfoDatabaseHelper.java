package com.example.database;

import com.example.constant.PersonInfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class InfoDatabaseHelper extends SQLiteOpenHelper {
   
	private static final String dataBaseName="ContactsInfo.db";
	private static final int version=1;
	
	private static final String DB_CREATE_CONTACTS="create table if not exists "
			            +PersonInfo.PERSON_INFO_TABLE+"("+"_id INTEGER primary key autoincrement,"
						+ PersonInfo.NAME+" TEXT,"
						+PersonInfo.PHONENUMBER+" TEXT)";
	
	@SuppressLint("NewApi")
	public InfoDatabaseHelper(Context context) {
		super(context, dataBaseName, null, version, null);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATE_CONTACTS);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
