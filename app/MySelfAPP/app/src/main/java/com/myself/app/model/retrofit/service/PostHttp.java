package com.myself.app.model.retrofit.service;

import com.myself.app.model.retrofit.entity.BaseResultEntity;

import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/19 0019.
 */

public interface PostHttp {

    @POST("login")
    Observable<BaseResultEntity> login(@Part String username, @Part String password);
}
