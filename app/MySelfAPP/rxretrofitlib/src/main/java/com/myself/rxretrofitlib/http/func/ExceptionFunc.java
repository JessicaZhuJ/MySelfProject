package com.myself.rxretrofitlib.http.func;

import android.util.Log;

import com.myself.rxretrofitlib.exception.FactoryException;

import rx.Observable;
import rx.functions.Func1;

/**
 * 异常处理
 * Created by Administrator on 2017/6/16 0016.
 */

public class ExceptionFunc implements Func1<Throwable, Observable> {
    @Override
    public Observable call(Throwable throwable) {
        Log.e("Tag","-------->"+throwable.getMessage());
        return Observable.error(FactoryException.analysisException(throwable));
    }
}
