package com.myself.rxretrofitlib.listener;

import rx.Observable;

/**
 * 回调ober对象
 * Created by Administrator on 2017/6/16 0016.
 */

public interface HttpOnNextSubListener {
    /**
     * ober成功回调
     * @param observable observable
     * @param method method
     */
    void onNext(Observable observable,String method);
}
