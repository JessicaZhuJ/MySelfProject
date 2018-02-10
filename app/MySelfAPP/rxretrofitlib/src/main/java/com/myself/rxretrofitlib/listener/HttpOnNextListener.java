package com.myself.rxretrofitlib.listener;

import com.myself.rxretrofitlib.exception.ApiException;

/**
 * 成功回调处理
 * Created by Administrator on 2017/6/15 0015.
 */

public interface HttpOnNextListener {

    /**
     * 成功后回调方法
     * @param result 结果
     * @param method 方法
     */
    void onNext(String result,String method);

    /**
     * 失败或者错误处理方法，
     * @param e e
     */
    void onError(ApiException e);
}
