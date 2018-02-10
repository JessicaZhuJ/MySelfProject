package com.myself.rxretrofitlib.download;

import com.myself.rxretrofitlib.download.downloadListener.DownloadInterceptor;
import com.myself.rxretrofitlib.exception.HttpTimeException;
import com.myself.rxretrofitlib.exception.RetryWhenNetworkException;
import com.myself.rxretrofitlib.subscribers.ProgressDownSubscriber;
import com.myself.rxretrofitlib.utils.DbDownUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.myself.rxretrofitlib.utils.AppUtil.getBaseUrl;
import static com.myself.rxretrofitlib.utils.AppUtil.writeCache;

/**
 * http下载处理类
 * Created by Administrator on 2017/6/19 0019.
 */

public class HttpDownManager {
    // 记录下载数据
    private Set<DownInfo> downInfos;
    // 回调sub队列
    private HashMap<String, ProgressDownSubscriber> subMap;
    // 单利对象
    private volatile static HttpDownManager instance;
    // 数据库类
    private DbDownUtil db;

    private HttpDownManager() {
        downInfos = new HashSet<>();
        subMap = new HashMap<>();
        db = DbDownUtil.getInstance();
    }

    /**
     * 获取单利对象
     *
     * @return
     */
    public static HttpDownManager getInstance() {
        if (instance == null) {
            synchronized (HttpDownManager.class) {
                if (instance == null) {
                    instance = new HttpDownManager();
                }
            }
        }
        return instance;
    }

    /**
     * 开始下载
     * @param info 下载信息
     */
    public void startDown(final DownInfo info) {
        // 正在下载不处理
        if (info == null || subMap.get(info.getUrl()) != null) {
            subMap.get(info.getUrl()).setDownInfo(info);
            return;
        }
        // 添加回调处理类
        ProgressDownSubscriber subscriber = new ProgressDownSubscriber(info);
        // 记录回调sub
        subMap.put(info.getUrl(), subscriber);
        // 获取service，多次请求公用一个service
        HttpDownService httpDownService;
        if (downInfos.contains(info)) {
            httpDownService = info.getService();
        } else {
            DownloadInterceptor interceptor = new DownloadInterceptor(subscriber);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(info.getConnectionTime(), TimeUnit.SECONDS);
            builder.addInterceptor(interceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(getBaseUrl(info.getUrl()))
                    .build();

            httpDownService = retrofit.create(HttpDownService.class);
            info.setService(httpDownService);
            downInfos.add(info);
        }
        httpDownService.download("bytes=" + info.getReadLength() + "-", info.getUrl())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryWhenNetworkException())
                .map(new Func1<ResponseBody, DownInfo>() {
                    @Override
                    public DownInfo call(ResponseBody responseBody) {
                        try {
                            writeCache(responseBody, new File(info.getSavePath()), info);
                        } catch (IOException e) {
                            throw new HttpTimeException(e.getMessage());
                        }
                        return info;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 停止下载
     * @param info 下载信息
     */
    public void stopDown(DownInfo info){
        if (info == null)return;
        info.setState(DownState.STOP);
        info.getListener().onStop();
        if (subMap.containsKey(info.getUrl())){
            ProgressDownSubscriber subscriber = subMap.get(info.getUrl());
            subscriber.unsubscribe();
            subMap.remove(info.getUrl());
        }

        db.insertDown(info);
    }

    /**
     * 暂停下载
     * @param info 下载信息
     */
    public void pause(DownInfo info){
        if (info == null )return;
        info.setState(DownState.PAUSE);
        info.getListener().onPause();
        if (subMap.containsKey(info.getUrl())){
            ProgressDownSubscriber subscriber = subMap.get(info.getUrl());
            subscriber.unsubscribe();
            subMap.remove(info.getUrl());
        }
        db.updateDown(info);
    }

    /**
     * 停止全部下载
     */
    public void stopAllDown(){
        for (DownInfo info:downInfos){
            stopDown(info);
        }
        subMap.clear();
        downInfos.clear();
    }

    /**
     * 暂停全部下载
     */
    public void pauseAll(){
        for (DownInfo info:downInfos){
            pause(info);
        }
        subMap.clear();
        downInfos.clear();
    }

    /**
     * 返回全部正在下载的数据
     * @return set
     */
    public Set<DownInfo> getDownInfos() {
        return downInfos;
    }

    /**
     * 移除下载数据
     * @param info 下载数据
     */
    public void remove(DownInfo info){
        subMap.remove(info.getUrl());
        downInfos.remove(info);
    }
}
