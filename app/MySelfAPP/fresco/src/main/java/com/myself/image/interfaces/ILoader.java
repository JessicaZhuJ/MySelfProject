package com.myself.image.interfaces;

import android.content.Context;
import android.view.View;


import com.myself.image.config.SingleConfig;

import java.io.File;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public interface ILoader {

    void init(Context context, int cacheSizeInM);

    void request(SingleConfig config);

    void pause();

    void resume();

    void clearDiskCache();

    void clearMemoryCache();

    long getCacheSize();

    void clearCacheByUrl(String url);

    void clearMemoryCache(View view);
    void clearMemoryCache(String url);

    File getFileFromDiskCache(String url);

    void getFileFromDiskCache(String url, FileGetter getter);



   boolean  isCached(String url);

    void trimMemory(int level);

    void onLowMemory();

}
