package com.myself.rxretrofitlib.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.myself.rxretrofitlib.RxRetrofitApp;
import com.myself.rxretrofitlib.utils.dao.CookieResultDao;
import com.myself.rxretrofitlib.utils.dao.DaoMaster;
import com.myself.rxretrofitlib.utils.dao.DaoSession;
import com.myself.rxretrofitlib.utils.dao.DownInfoDao;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class DaoUtils {
    private static DaoUtils instance;

    private DaoMaster.DevOpenHelper helper;
    private DaoSession session;
    private Context context;

    private DaoUtils() {
        context = RxRetrofitApp.getApplication();
        helper = new DaoMaster.DevOpenHelper(context, AppUtil.DBNAME);
    }

    public static DaoUtils getInstance() {
        if (instance == null) {
            synchronized (DaoUtils.class) {
                if (instance == null) {
                    instance = new DaoUtils();
                }
            }
        }
        return instance;
    }

    public SQLiteDatabase getReadableDatabase() {
        return helper.getReadableDatabase();
    }

    public SQLiteDatabase getWritableDatabase() {
        return helper.getWritableDatabase();
    }

    public DaoSession getSession() {
        if (session == null) {
            session = new DaoMaster(getWritableDatabase()).newSession();
        }
        return session;
    }
}
