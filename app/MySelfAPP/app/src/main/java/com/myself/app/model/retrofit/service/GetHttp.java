package com.myself.app.model.retrofit.service;

import com.myself.app.utils.Utils;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * get请求
 * Created by Administrator on 2017/6/20 0020.
 */

public interface GetHttp {
    @GET(Utils.PROJECTS_LIST)
    Observable<String> getProjectList(@Query("userID") String userID);
}
