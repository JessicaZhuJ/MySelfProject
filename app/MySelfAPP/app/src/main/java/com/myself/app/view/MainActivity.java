package com.myself.app.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.myself.app.R;
import com.myself.app.databinding.ActivityMainBinding;
import com.myself.app.receiver.RestartService;
import com.myself.app.viewModel.MainViewModel;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.Observable;


public class MainActivity extends BaseActivity {
    private static final String TAG = "zhujl";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel viewModel;
        binding.setViewModel((viewModel = new MainViewModel(this)));
        setupObservable(viewModel, this);

        startService(new Intent(this,RestartService.class));
    }


    private void registerRestart() {
        Intent intent = new Intent();
        Context c = null;
        try {
            c = createPackageContext("com.example.broadcasttest", Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//            intent.setPackage(getPackageName());
//            intent.setComponent(pkgName, className);
//            intent.setComponent(pkgNameContext, className);
        intent.setClassName(c, "com.myself.app.receiver.RestartReceiver");
//            intent.setClassName("com.example.broadcasttest", "com.example.broadcasttest.TestBroadcastReceiver");
        intent.setAction("com.myself.app.receiver.RestartReceiver");
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MainViewModel) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 11:
                if (data == null) {
                    return;
                }
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                result = bundle.getString(CodeUtils.RESULT_STRING);
                handler.sendEmptyMessage(0);
                break;
        }
    }

    String result = "";
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(context, "result=" + result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: xin lei onDestroy");
    }
}
