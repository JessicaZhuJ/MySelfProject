package com.myself.rxretrofitlib.http.cookie;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * post请求缓存数据
 * Created by Administrator on 2017/6/16 0016.
 */
@Entity
public class CookieResult {
    @Id
    private long id;
    private String url;
    private String result;
    private long time;

    public CookieResult(String url,String result, long time){
        this.url = url;
        this.result = result;
        this.time = time;
    }

    @Generated(hash = 1914207567)
    public CookieResult(long id, String url, String result, long time) {
        this.id = id;
        this.url = url;
        this.result = result;
        this.time = time;
    }

    public CookieResult(){

    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getResult() {
        return result;
    }

    public long getTime() {
        return time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
