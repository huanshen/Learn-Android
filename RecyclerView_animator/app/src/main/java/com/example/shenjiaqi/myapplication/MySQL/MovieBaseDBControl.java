package com.example.shenjiaqi.myapplication.MySQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.concurrent.Executor;

/**
 * Created by shenjiaqi on 2018/2/25.
 */

public class MovieBaseDBControl {

    private static final String TAG = "MovieBaseDBControl";
    public static final String DB_NAME = "HomeFeed.db";

    public static final int DB_VERSION;


    /**Context.*/
    protected static Context mContext;

    /**Used to perform history write operations asynchronously.*/
    protected final Executor mExecutor;

    /**SQLiteOpenHelper.*/
    protected final SQLiteOpenHelper mOpenHelper;

    /** Sprint10.2的数据库版本  */
    private static final int DB_VERSION_1 = 100;

    static {
        DB_VERSION = DB_VERSION_1;
    }

    protected MovieBaseDBControl(Context context, Executor executor, SQLiteOpenHelper openHelper) {
        mContext = context;
        mExecutor = executor;
        mOpenHelper = openHelper;
    }



    public static final class DbOpenHelper extends SQLiteOpenHelper {
        /**
         * Database path.
         */
        private String mPath;
        /**
         * 单例.
         */
        private static volatile DbOpenHelper mDbOpenHelper;

        /**
         * 创建首页Feed流列表数据库
         *
         * @param db SQLiteDatabase
         */
        private void createFeedListTable(SQLiteDatabase db) {
            DBControl feedDBControl = DBControl.getInstance(AppRuntime.getAppContext());
            String sql = feedDBControl.getCreateFeedListTableSql();
            db.execSQL(sql);
        }

        /**
         * DbOpenHelper constructor.
         * @param context Context
         * @param name Database name
         * @param version Database version
         * */
        private DbOpenHelper(Context context, String name, int version) {
            super(context, name, null, version);
        }

        /**
         * 获得单例.
         * @param context Context
         * @param name Database name
         * @param version Database version
         * @return {@link DbOpenHelper}
         */
        public static DbOpenHelper getInstance(Context context, String name, int version) {
            if (mDbOpenHelper == null) {
                synchronized (DbOpenHelper.class) {
                    if (mDbOpenHelper == null) {
                        mDbOpenHelper = new DbOpenHelper(context, name, version);
                    }
                }
            }
                Log.i(TAG, "current  homefeed db version = " + DB_VERSION);

            return mDbOpenHelper;
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            mPath = db.getPath();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            createFeedListTable(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            int version = oldVersion;
            Log.i(TAG, "DB new version= " + newVersion + "DB old version=" + version);


            while (version < newVersion) {
                switch (version) {
                    case DB_VERSION_1:
                        // 预留高版本升级
                    default:
                        break;
                }
                version++;
            }
        }
    }
}
