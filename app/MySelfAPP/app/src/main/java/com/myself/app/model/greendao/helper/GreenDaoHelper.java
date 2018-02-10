package com.myself.app.model.greendao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.myself.app.model.greendao.dao.DaoMaster;
import com.myself.app.model.greendao.dao.DaoSession;
import com.myself.app.utils.Utils;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.generator.Schema;

/**
 * greendao辅助类，主要用于获取session，使用单例模式，防止创建多个对象
 * GreenDaoOpenHelper 用于进行数据库的版本控制
 * Created by zhujl on 2017/6/14 0014.
 */

public class GreenDaoHelper {
    private static final String TAG = "GreenDaoHelper";
    private Context context;
    private static GreenDaoHelper instance;
    private DaoSession daoSession;
    //    private SQLiteDatabase database;
    Database database;

    private GreenDaoHelper(Context context, String dbName) {
        DatabaseOpenHelper helper = new GreenDaoOpenHelper(context, dbName);
//        database = helper.getEncryptedWritableDb(dbName);//加密数据库
        database = helper.getWritableDb();//普通数据库

        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public static GreenDaoHelper getInstance(Context context, String dbName) {
        if (null == instance) {
            instance = new GreenDaoHelper(context, dbName);
        }
        return instance;
    }

    /**
     * 获取DaoSession
     *
     * @return daoSession
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 获取database
     *
     * @return database
     */
    public Database getDatabase() {
        return database;
    }
//    public SQLiteDatabase getDatabase() {
//        return database;
//    }


    private class GreenDaoOpenHelper extends DaoMaster.DevOpenHelper {

        public GreenDaoOpenHelper(Context context, String dbName) {
            super(context, dbName);
            Schema schema = new Schema(1, context.getPackageName());
        }

        @Override
        public void onCreate(Database db) {
            super.onCreate(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            super.onUpgrade(db, oldVersion, newVersion);
        }


    }
}
