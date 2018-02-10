package com.myself.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.myself.app.model.greendao.helper.GreenDaoHelper;
import com.myself.app.utils.Utils;
import com.myself.fresco.FrescoLoader;
import com.myself.image.FrescoImageLoader;
import com.myself.rxretrofitlib.RxRetrofitApp;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * application
 * Created by Administrator on 2017/6/15 0015.
 */

public class MyselfApp extends Application {
    private static final String TAG = "zhujl";

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化dao创建应用数据看
        GreenDaoHelper.getInstance(this, Utils.DB_NAME);
        // 初始化网络设置
        RxRetrofitApp.init(this, Utils.BASEURL);
        // 初始化FrescoImageLoader
        FrescoImageLoader.init(this, 50, new FrescoLoader());
        ZXingLibrary.initDisplayOpinion(this);

        registerActivityLifecycleCallbacks(callbacks);


    }

    private ActivityLifecycleCallbacks callbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.d(TAG, "onActivityDestroyed: activity_name:" + activity.getLocalClassName());
        }
    };

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate: ------------");
    }


}
