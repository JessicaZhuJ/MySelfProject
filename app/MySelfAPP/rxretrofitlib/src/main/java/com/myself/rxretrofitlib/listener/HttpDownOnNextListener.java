package com.myself.rxretrofitlib.listener;

/**
 * 下载过程中的回调处理
 * Created by Administrator on 2017/6/16 0016.
 */
public abstract class HttpDownOnNextListener<T> {
    /**
     * 成功后回调
     *
     * @param t t
     */
    public abstract void onNext(T t);

    /**
     * 开始下载
     */
    public abstract void onStart();

    /**
     * 完成下载
     */
    public abstract void onComplete();

    /**
     * 下载进度
     *
     * @param readLength  readLength
     * @param countLength countLength
     */
    public abstract void updateProgress(long readLength, long countLength);

    /**
     * 失败或者错误方回调方法
     *
     * @param e e
     */
    public void onError(Throwable e) {

    }

    /**
     * 停止下载
     */
    public void onPause() {
    }

    /**
     * 停止下载销毁
     */
    public void onStop() {

    }
}
