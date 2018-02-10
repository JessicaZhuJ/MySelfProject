package com.myself.app.viewModel;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.myself.app.R;
import com.myself.app.model.retrofit.entity.ProjectsApi;
import com.myself.app.utils.Utils;
import com.myself.rxretrofitlib.exception.ApiException;
import com.myself.rxretrofitlib.http.HttpManager;
import com.myself.rxretrofitlib.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

public class HttpTestViewModel extends ViewModel {
    private HttpManager httpManager;
    /**
     * 统一的构造方法
     *
     * @param context 上下文环境
     */
    public HttpTestViewModel(Activity context) {
        super(context);
        httpManager= new HttpManager(httpOnNextListener, (RxAppCompatActivity) context);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.projects_btn:
                ProjectsApi projectsApi = new ProjectsApi();
                httpManager.doHttpDeal(projectsApi);
                break;
        }
    }

    private HttpOnNextListener httpOnNextListener = new HttpOnNextListener() {
        @Override
        public void onNext(String result, String method) {
            if (TextUtils.equals(method, Utils.PROJECTS_LIST)){
                System.out.println("result="+result);
            }
        }

        @Override
        public void onError(ApiException e) {

        }
    };
}
