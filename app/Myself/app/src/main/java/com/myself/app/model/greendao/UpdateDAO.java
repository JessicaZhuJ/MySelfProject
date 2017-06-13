package com.myself.app.model.greendao;

import android.database.sqlite.SQLiteOpenHelper;

import org.greenrobot.greendao.database.DatabaseOpenHelper;


/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class UpdateDAO<T> {
    private static final String TAG = "UpdateDAO";
    private static UpdateDAO instance= null;

    private DatabaseOpenHelper helper;

    private UpdateDAO(){

    }

    public static UpdateDAO getInstance() {
        if (instance == null){
            instance = new  UpdateDAO();
        }
        return instance;
    }
}
