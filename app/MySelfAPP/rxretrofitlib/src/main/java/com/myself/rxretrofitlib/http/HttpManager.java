package com.myself.rxretrofitlib.http;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.myself.rxretrofitlib.RxRetrofitApp;
import com.myself.rxretrofitlib.api.BaseApi;
import com.myself.rxretrofitlib.exception.RetryWhenNetworkException;
import com.myself.rxretrofitlib.http.func.ExceptionFunc;
import com.myself.rxretrofitlib.http.func.ResultRunc;
import com.myself.rxretrofitlib.listener.HttpOnNextListener;
import com.myself.rxretrofitlib.listener.HttpOnNextSubListener;
import com.myself.rxretrofitlib.subscribers.ProgressSubscriber;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * http交互处理类
 * Created by Administrator on 2017/6/15 0015.
 */

public class HttpManager {
    private SoftReference<HttpOnNextListener> onNextListener;
    private SoftReference<HttpOnNextSubListener> onNextSubListener;
    private SoftReference<RxAppCompatActivity> appCompatActivity;

    public HttpManager(HttpOnNextListener onNextListener, RxAppCompatActivity appCompatActivity) {
        this.onNextListener = new SoftReference<>(onNextListener);
        this.appCompatActivity = new SoftReference<>(appCompatActivity);
    }

    public HttpManager(HttpOnNextSubListener onNextSubListener,RxAppCompatActivity appCompatActivity){
        this.onNextSubListener = new SoftReference<>(onNextSubListener);
        this.appCompatActivity = new SoftReference<>(appCompatActivity);
    }

    /**
     * 处理Http请求
     * @param basePar 封装的请求数据
     */
    public void doHttpDeal(final BaseApi basePar){
        Retrofit retrofit = getRetrofit(basePar.getConnectionTime(),basePar.getBaseUrl());
        httpDeal(basePar.getObservable(retrofit),basePar);
    }

    /**
     * 获取Retrofit对象
     * @param connectTime connectTime
     * @param baseUrl baseUrl
     * @return retrofit
     */
    public Retrofit getRetrofit(int connectTime,String baseUrl){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(connectTime, TimeUnit.SECONDS);
        if (RxRetrofitApp.isDebug()){
            builder.addInterceptor(getHttpLogginInterceptor());
        }
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return retrofit;
    }

    public void httpDeal(Observable observable,BaseApi basePar){
        observable = observable.retryWhen(new RetryWhenNetworkException(basePar.getRetryCount(),
                basePar.getRetryDelay(),basePar.getRetryIncreaseDelay()))
        .onErrorResumeNext(new ExceptionFunc())
        .compose(appCompatActivity.get().bindUntilEvent(ActivityEvent.DESTROY))
        .map(new ResultRunc())
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());

        if (onNextSubListener != null && null != onNextSubListener.get()){
            onNextSubListener.get().onNext(observable,basePar.getMethod());
        }

        if (onNextListener != null && null != onNextListener.get()){
            ProgressSubscriber subscriber = new ProgressSubscriber(basePar,onNextListener,appCompatActivity);
            observable.subscribe(subscriber);
        }
    }

    /**
     * 日志出输出
     * 自行添加判定是否添加
     * @return logginInterceptor
     */
    private HttpLoggingInterceptor getHttpLogginInterceptor(){
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("RxRetrofit","Retrofit====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
