package com.myself.rxretrofitlib.api;

import com.myself.rxretrofitlib.http.HttpManager;
import com.myself.rxretrofitlib.listener.HttpOnNextListener;
import com.myself.rxretrofitlib.listener.HttpOnNextSubListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * 请求数据统一封装类
 * Created by Administrator on 2017/6/19 0019.
 */

public class HttpManagerApi extends BaseApi {
    private HttpManager manager;

    public HttpManagerApi(HttpOnNextListener onNextListener, RxAppCompatActivity appCompatActivity) {
        manager = new HttpManager(onNextListener, appCompatActivity);
    }

    public HttpManagerApi(HttpOnNextSubListener onNextSubListener, RxAppCompatActivity appCompatActivity) {
        manager = new HttpManager(onNextSubListener, appCompatActivity);
    }

    protected Retrofit getRetrofit() {
        return manager.getRetrofit(getConnectionTime(), getBaseUrl());
    }

    protected void doHttpDeal(Observable observable) {
        manager.httpDeal(observable, this);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        return null;
    }
}
