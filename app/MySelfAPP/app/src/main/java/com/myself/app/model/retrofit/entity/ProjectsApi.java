package com.myself.app.model.retrofit.entity;

import com.myself.app.model.retrofit.service.GetHttp;
import com.myself.app.utils.Utils;
import com.myself.rxretrofitlib.api.BaseApi;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * 测试获取工程列表接口
 * Created by zhujl on 2017/6/20 0020.
 */

public class ProjectsApi extends BaseApi {
    public ProjectsApi() {
        setMethod(Utils.PROJECTS_LIST);
        setShowProgress(true);
        setCache(false);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        GetHttp getHttp = retrofit.create(GetHttp.class);

        return getHttp.getProjectList(Utils.USERID);
    }
}
