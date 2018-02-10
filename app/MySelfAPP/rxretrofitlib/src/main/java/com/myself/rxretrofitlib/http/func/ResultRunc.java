package com.myself.rxretrofitlib.http.func;

import android.text.TextUtils;

import com.myself.rxretrofitlib.exception.HttpTimeException;

import rx.functions.Func1;

/**
 * 服务器返回数据判断
 * Created by Administrator on 2017/6/16 0016.
 */

public class ResultRunc implements Func1<Object,Object> {
    @Override
    public Object call(Object o) {
        if (o == null || TextUtils.isEmpty(o.toString())){
            throw new HttpTimeException("数据错误");
        }
        return o;
    }
}
