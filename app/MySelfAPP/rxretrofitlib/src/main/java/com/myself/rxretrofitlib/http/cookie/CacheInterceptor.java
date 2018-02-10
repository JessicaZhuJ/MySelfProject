package com.myself.rxretrofitlib.http.cookie;

import com.myself.rxretrofitlib.RxRetrofitApp;
import com.myself.rxretrofitlib.utils.AppUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * get缓存方式拦截器
 * Created by Administrator on 2017/6/19 0019.
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!AppUtil.isNetworkAvailable(RxRetrofitApp.getApplication())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        Response responseLatest;
        if (AppUtil.isNetworkAvailable(RxRetrofitApp.getApplication())) {
            int maxAge = 60;// 有网失效时间 1分钟
            responseLatest = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public,max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 6;// 没网失效时间6小时
            responseLatest = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale)
                    .build();
        }
        return responseLatest;
    }
}
