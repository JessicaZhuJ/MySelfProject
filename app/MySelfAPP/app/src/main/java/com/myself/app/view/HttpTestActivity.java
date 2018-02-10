package com.myself.app.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.myself.app.R;
import com.myself.app.databinding.ActivityHttpTestBinding;
import com.myself.app.utils.Utils;
import com.myself.app.viewModel.HttpTestViewModel;

import java.util.Observable;

/**
 * http测试
 * Created by zhujl on 2017/6/20 0020.
 */

public class HttpTestActivity extends BaseRxActivity {
    private static final String TAG = "HttpTestActivity";
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initBinding();
    }

    private void initBinding() {
        ActivityHttpTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_http_test);
        HttpTestViewModel viewModel = new HttpTestViewModel(this);
        binding.setHttpTestViewModel(viewModel);
        setupObservable(viewModel, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof HttpTestViewModel){

        }
    }


}
