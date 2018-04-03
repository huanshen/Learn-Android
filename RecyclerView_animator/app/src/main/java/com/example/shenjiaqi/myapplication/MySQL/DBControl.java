package com.example.shenjiaqi.myapplication.MySQL;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by shenjiaqi on 2018/2/25.
 */

public class DBControl extends MovieBaseDBControl{

    private static final String TAG = "DBControl";

    /** Single Instance */
    private static DBControl mInstance;

    public static enum MovieListTable {
        title;

        static final String TABLE_NAME = "movielist";

    }


    /**
     * 构造函数.
     *
     * @param executor   Executor
     * @param openHelper SQLiteOpenHelper
     */
    private DBControl(Context context, Executor executor, SQLiteOpenHelper openHelper) {
        super(context, executor, openHelper);
    }

    public String getCreateFeedListTableSql() {
        String sql = "CREATE TABLE " + MovieListTable.TABLE_NAME + " ( "
                + MovieListTable.title.name() + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"

                + ");";
        Log.d(TAG, "getCreateFeedListTableSql sql is:" + sql);

        return sql;
    }

    /**
     * getInstance
     *
     * @return FeedDBControl
     */
    public static synchronized DBControl getInstance(Context context) {
        if (null == mInstance) {
            ThreadFactory logThreadFactory = Executors.defaultThreadFactory();
            Executor logExecutor = Executors.newSingleThreadExecutor(logThreadFactory);
            SQLiteOpenHelper openHelper =
                    DbOpenHelper.getInstance(context, MovieBaseDBControl.DB_NAME, MovieBaseDBControl.DB_VERSION);
            mInstance = new DBControl(context, logExecutor, openHelper);
        }

        return mInstance;
    }



}
