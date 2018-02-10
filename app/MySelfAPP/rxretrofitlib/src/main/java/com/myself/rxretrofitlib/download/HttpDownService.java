package com.myself.rxretrofitlib.download;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * service - 下载接口
 * Created by Administrator on 2017/6/16 0016.
 */

public interface HttpDownService {
    @Streaming
    @GET
    Observable<ResponseBody> download(@Header("RANGE") String start,
                                      @Url String url);
}
