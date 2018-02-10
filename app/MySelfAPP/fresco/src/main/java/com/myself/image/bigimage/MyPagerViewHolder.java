package com.myself.image.bigimage;

import android.view.ViewGroup;


import com.myself.biv.view.BigImageView;
import com.myself.image.FrescoImageLoader;

import java.io.File;
import java.util.Map;

import am.util.viewpager.adapter.RecyclePagerAdapter;

/**
 * Created by Administrator on 2017/4/30.
 */

public class MyPagerViewHolder extends RecyclePagerAdapter.PagerViewHolder {
    BigImageView imageView;
    public MyPagerViewHolder(ViewGroup parent) {
        super(new BigImageView(parent.getContext()));
        imageView = (BigImageView) itemView;
    }

    public MyPagerViewHolder init(Map<String,File> caches){
        imageView.setCachedFileMap(caches);
        return this;
    }

    public void setData(String url){
        FrescoImageLoader.loadBigImage(imageView,url);
    }



}
