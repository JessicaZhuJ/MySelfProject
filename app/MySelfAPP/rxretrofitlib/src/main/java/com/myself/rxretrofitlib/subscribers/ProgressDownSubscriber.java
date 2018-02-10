package com.myself.rxretrofitlib.subscribers;


import com.myself.rxretrofitlib.download.DownInfo;
import com.myself.rxretrofitlib.download.DownState;
import com.myself.rxretrofitlib.download.HttpDownManager;
import com.myself.rxretrofitlib.download.downloadListener.DownloadProgressListener;
import com.myself.rxretrofitlib.listener.HttpDownOnNextListener;

import java.lang.ref.SoftReference;

import rx.Subscriber;

/**
 * 断点下载处理类Subscriber
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束时，关闭dialog
 * 调用者自己对请求数据进行处理
 * Created by Administrator on 2017/6/19 0019.
 */

public class ProgressDownSubscriber<T> extends Subscriber<T> implements DownloadProgressListener {

    // 弱引用结果回调
    private SoftReference<HttpDownOnNextListener> mSubscriberOnNextListener;
    // 下载数据
    private DownInfo downInfo;

    public ProgressDownSubscriber(DownInfo downInfo) {
        this.mSubscriberOnNextListener = new SoftReference<>(downInfo.getListener());
        this.downInfo = downInfo;
    }

    public void setDownInfo(DownInfo downInfo) {
        this.mSubscriberOnNextListener = new SoftReference<>(downInfo.getListener());
        this.downInfo = downInfo;
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        super.onStart();
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onStart();
        }
        downInfo.setState(DownState.START);
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        if (mSubscriberOnNextListener.get() != null){
            mSubscriberOnNextListener.get().onComplete();
        }

        HttpDownManager.getInstance();
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void update(long read, long count, boolean done) {

    }
}
