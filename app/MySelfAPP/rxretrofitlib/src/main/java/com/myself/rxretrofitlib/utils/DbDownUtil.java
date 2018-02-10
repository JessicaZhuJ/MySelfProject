package com.myself.rxretrofitlib.utils;

import com.myself.rxretrofitlib.download.DownInfo;
import com.myself.rxretrofitlib.utils.dao.DownInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * DownInfo 数据库工具类
 * Created by Administrator on 2017/6/19 0019.
 */

public class DbDownUtil {
    private static DbDownUtil instance;

    private DbDownUtil() {
    }

    public static DbDownUtil getInstance() {
        if (instance == null) {
            synchronized (DbDownUtil.class) {
                if (instance == null) {
                    instance = new DbDownUtil();
                }
            }
        }
        return instance;
    }

    public void insertDown(DownInfo downInfo) {
        DaoUtils.getInstance().getSession().getDownInfoDao().insert(downInfo);
    }

    public void updateDown(DownInfo info){
        DaoUtils.getInstance().getSession().getDownInfoDao().update(info);
    }

    public void delete(DownInfo info){
        DaoUtils.getInstance().getSession().getDownInfoDao().delete(info);
    }

    public void deleteAll(){
        DaoUtils.getInstance().getSession().getDownInfoDao().deleteAll();
    }

    public DownInfo queryDownInfoBy(long id){
        QueryBuilder<DownInfo> qb= DaoUtils.getInstance().getSession().getDownInfoDao().queryBuilder();
        qb.where(DownInfoDao.Properties.Id.eq(id));
        List<DownInfo> list = qb.list();
        if (!list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }
    }

    public List<DownInfo> queryDownInfo(){
        QueryBuilder<DownInfo> qb = DaoUtils.getInstance().getSession().getDownInfoDao().queryBuilder();
        return qb.list();
    }
}
