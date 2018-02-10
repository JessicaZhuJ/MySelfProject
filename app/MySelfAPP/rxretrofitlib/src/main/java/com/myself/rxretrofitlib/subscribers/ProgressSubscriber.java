package com.myself.rxretrofitlib.subscribers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.myself.rxretrofitlib.api.BaseApi;
import com.myself.rxretrofitlib.exception.ApiException;
import com.myself.rxretrofitlib.exception.CodeException;
import com.myself.rxretrofitlib.exception.HttpTimeException;
import com.myself.rxretrofitlib.http.cookie.CookieResult;
import com.myself.rxretrofitlib.listener.HttpOnNextListener;
import com.myself.rxretrofitlib.utils.AppUtil;
import com.myself.rxretrofitlib.utils.CookieDbUtil;

import java.lang.ref.SoftReference;

import rx.Observable;
import rx.Subscriber;

/**
 * 用于在http请求开始时，自动显示一个progress dialog
 * 在http请求结束时，关闭progress dialog
 * Created by Administrator on 2017/6/15 0015.
 */

public class ProgressSubscriber<T> extends Subscriber<T> {
    // 是否弹框
    private boolean showProgress = true;
    // 回调接口
    private SoftReference<HttpOnNextListener> mSubscriberOnNextListener;
    // 软引用
    private SoftReference<Context> mActivity;
    // 加载框
    private ProgressDialog pd;
    // 请求数据
    private BaseApi api;

    public ProgressSubscriber(BaseApi api,
                              SoftReference<HttpOnNextListener> onNextListener,
                              SoftReference<Context> mActivity) {
        this.api = api;
        this.mSubscriberOnNextListener = onNextListener;
        this.mActivity = mActivity;
        setShowProgress(api.isShowProgress());
        if (api.isShowProgress()) {
            initProgressDialog(api.isCancel());
        }

    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    private void initProgressDialog(boolean cancel) {
        Context context = mActivity.get();
        if (pd == null && context != null) {
            pd = new ProgressDialog(context);
            pd.setCancelable(cancel);
            if (cancel) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        onCancelProgress();
                    }
                });
            }
        }
    }

    private void showProgressDialog() {
        if (!isShowProgress()) return;
        Context context = mActivity.get();
        if (pd == null || context == null) return;
        if (!pd.isShowing()) {
            pd.show();
        }
    }

    private void dismissProgressDialog() {
        if (!isShowProgress()) return;
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }

    private void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    /**
     * 完成后隐藏dialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    /**
     * 错误处理
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (api.isCache()) {
            getCache();
        } else {
            errorDo(e);
        }
        dismissProgressDialog();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment处理
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (api.isCache()){
            CookieResult result = CookieDbUtil.getInstance().queryCookieBy(api.getUrl());
            long time = System.currentTimeMillis();
            if (result == null){
                result = new CookieResult(api.getUrl(),t.toString(),time);
                CookieDbUtil.getInstance().saveCookie(result);
            }else{
                result.setResult(t.toString());
                result.setTime(time);
                CookieDbUtil.getInstance().updateCookie(result);
            }
        }
        if (mSubscriberOnNextListener.get() != null){
            mSubscriberOnNextListener.get().onNext((String) t,api.getMethod());
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
        if (api.isCache() && AppUtil.isNetworkAvailable(mActivity.get())) {
            CookieResult cookieResult = CookieDbUtil.getInstance().queryCookieBy(api.getUrl());
            if (cookieResult != null) {
                long time = (System.currentTimeMillis() - cookieResult.getTime()) / 1000;
                if (time < api.getCookieNetWorkTime()) {
                    if (mSubscriberOnNextListener.get() != null) {
                        mSubscriberOnNextListener.get().onNext(cookieResult.getResult(), api.getMethod());
                    }
                    onCompleted();
                    unsubscribe();
                }
            }
        }
    }

    public void getCache() {
        Observable.just(api.getUrl()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                errorDo(e);
            }

            @Override
            public void onNext(String s) {
                CookieResult cookieResult = CookieDbUtil.getInstance().queryCookieBy(s);
                if (cookieResult == null) {
                    throw new HttpTimeException(HttpTimeException.NO_CHANGE_ERROR);
                }
                long time = (System.currentTimeMillis() - cookieResult.getTime()) / 1000;
                if (time < api.getCookieNetWorkTime()) {
                    if (mSubscriberOnNextListener.get() != null) {
                        mSubscriberOnNextListener.get().onNext(cookieResult.getResult(), api.getMethod());
                    }
                } else {
                    CookieDbUtil.getInstance().deleteCookie(cookieResult);
                    throw new HttpTimeException(HttpTimeException.CHANGE_TIMEOUT_ERROR);
                }
            }
        });
    }

    /**
     * 错误同意处理
     *
     * @param e e
     */
    private void errorDo(Throwable e) {
        Context context = mActivity.get();
        if (context == null) return;
        HttpOnNextListener httpOnNextListener = mSubscriberOnNextListener.get();
        if (httpOnNextListener == null) return;
        if (e instanceof ApiException) {
            httpOnNextListener.onError((ApiException) e);
        } else if (e instanceof HttpTimeException) {
            HttpTimeException exception = (HttpTimeException) e;
            httpOnNextListener.onError(new ApiException(exception, CodeException.RUNTIME_ERROR, exception.getMessage()));
        } else {
            httpOnNextListener.onError(new ApiException(e, CodeException.UNKNOWN_ERROR, e.getMessage()));
        }
    }
}
