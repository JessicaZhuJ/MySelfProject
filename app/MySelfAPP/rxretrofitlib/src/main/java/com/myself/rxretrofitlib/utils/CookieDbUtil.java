package com.myself.rxretrofitlib.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.myself.rxretrofitlib.RxRetrofitApp;
import com.myself.rxretrofitlib.http.cookie.CookieResult;
import com.myself.rxretrofitlib.utils.dao.CookieResultDao;
import com.myself.rxretrofitlib.utils.dao.DaoMaster;
import com.myself.rxretrofitlib.utils.dao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 数据缓存
 * 数据库恐惧类，greendao
 * Created by Administrator on 2017/6/16 0016.
 */

public class CookieDbUtil {
    private static CookieDbUtil db;

    private CookieDbUtil() {
    }

    public static CookieDbUtil getInstance() {
        if (db == null) {
            synchronized (CookieDbUtil.class) {
                if (db == null) {
                    db = new CookieDbUtil();
                }
            }
        }
        return db;
    }



    public void saveCookie(CookieResult info) {
        CookieResultDao downInfoDao = DaoUtils.getInstance().getSession().getCookieResultDao();
        downInfoDao.insert(info);
    }

    public void updateCookie(CookieResult info) {
        DaoUtils.getInstance().getSession().getCookieResultDao().update(info);
    }

    public void deleteCookie(CookieResult info) {
        DaoUtils.getInstance().getSession().getCookieResultDao().delete(info);
    }

    public void deleteAll(){
        DaoUtils.getInstance().getSession().getCookieResultDao().deleteAll();
    }

    public CookieResult queryCookieBy(String url) {
        QueryBuilder<CookieResult> qb = DaoUtils.getInstance().getSession().getCookieResultDao().queryBuilder();
        qb.where(CookieResultDao.Properties.Url.eq(url));
        List<CookieResult> list = qb.list();

        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public List<CookieResult> queryCookieAll() {
        return DaoUtils.getInstance().getSession().getCookieResultDao().queryBuilder().list();
    }
}
