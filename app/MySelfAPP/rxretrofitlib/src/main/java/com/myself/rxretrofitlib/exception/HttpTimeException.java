package com.myself.rxretrofitlib.exception;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class HttpTimeException extends RuntimeException {
    public static final int UNKOWN_ERROR = 0x1002;
    public static final int NO_CHANGE_ERROR = 0x1003;
    public static final int CHANGE_TIMEOUT_ERROR = 0x1004;

    public HttpTimeException(int resultCode) {
        super(getApiExceptionMessage(resultCode));
    }

    public HttpTimeException(String detaliMessage) {
        super(detaliMessage);
    }

    private static String getApiExceptionMessage(int code) {
        switch (code) {
            case UNKOWN_ERROR:

                return "错误：网络错误";
            case NO_CHANGE_ERROR:

                return "错误：无缓存数据";
            case CHANGE_TIMEOUT_ERROR:

                return "错误：缓存数据过期";

            default:
                return "错误：未知错误";
        }
    }

}
