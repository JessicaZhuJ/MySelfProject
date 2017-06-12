package com.myself.app.viewModel;

import android.os.Handler;

import com.myself.app.databinding.ActivityMainBinding;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class MainViewModel{
    private static final String TAG = "MainViewModel";
    private ActivityMainBinding binding;
    private Handler handler;
    public String H = "lllll";

    public MainViewModel(ActivityMainBinding binding){
        this.binding = binding;
        initView();
    }
    private void initView(){
        binding.tv.setText("hahahahhaha");
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
