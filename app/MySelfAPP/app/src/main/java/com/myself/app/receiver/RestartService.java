package com.myself.app.receiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2018/2/9 0009.
 */

public class RestartService extends Service {
    private static final String TAG = "zhujl";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("---------onDestroy: hahahahaha");
        Log.d(TAG, "onDestroy: hahahahaha");
    }
}
