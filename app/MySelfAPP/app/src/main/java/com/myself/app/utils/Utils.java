package com.myself.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;

import com.myself.app.R;

/**
 * 工具类，设置一些常量
 * Created by Administrator on 2017/6/12 0012.
 */

public class Utils {

    public static final String DB_NAME ="myself.db";

    private Utils() {
    }

    public static final String IP = "";
    public static final String PORT = "";
    public static final String BASEURL = "http://192.168.1.41:1337/";

    // get
    public static final String PROJECTS_LIST="projects/";
    public static final String USERID="3a802240-0613-11e6-b3f9-01c3b486940c";

    private static SoundPool changeTimeSoundPool;

    @SuppressLint("HandlerLeak")
    public static void changeTimeSoundPool(Context context) {

        changeTimeSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        changeTimeSoundPool.load(context, R.raw.change_record, 1);
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                changeTimeSoundPool.play(1, 1, 1, 0, 3, 1);
            }
        }.sendEmptyMessageDelayed(0, 1000);
    }
}
