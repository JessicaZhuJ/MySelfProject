package com.myself.app.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

import com.myself.app.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class ConfigChangeActivity extends Activity {

    // SimpleDateFormat 为线程不安全操作,一般不要定义成static，若要定义成static，通过这种方式处理。
    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        @SuppressLint("SimpleDateFormat")
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_change);
        int orientation = this.getResources().getConfiguration().orientation;
        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText(df.get().format(new Date()));

//        Map<String,String> map = new HashMap<>();
//        Set<Map.Entry<String,String>> entries = map.entrySet();
//        for (Map.Entry<String,String> entry :entries){
//            entry.getKey();
//            entry.getValue();
//        }

        // JDK 8
//        map.forEach(new BiConsumer<String, String>() {
//            @Override
//            public void accept(String s, String s2) {
//
//            }
//        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
