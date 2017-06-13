package com.myself.app.model.greendao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * sqlite open helper
 * Created by zhujl on 2017/6/13 0013.
 */

public class MyselfSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyselfSQLiteOpenHelper";
    private static final String DB_NAME = "";
    private static final int VERSION = 1;
    private static final int OLDVERSION = 1;

    public MyselfSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // 数据库创建操作
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据库更新操作
    }
}
