package com.myself.app.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.myself.app.R;
import com.myself.app.databinding.ActivityDialogShowBinding;
import com.myself.app.viewModel.DialogShowViewModel;

import java.util.Observable;

/**
 * dialog 调试页面
 * Created by Administrator on 2017/6/21 0021.
 */

public class DialogShowActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    private void initDataBinding() {
        ActivityDialogShowBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dialog_show);
        DialogShowViewModel viewModel = new DialogShowViewModel(this);
        binding.setDialogshowViewModel(viewModel);
        setupObservable(viewModel, this);
    }


    @Override
    public void update(Observable o, Object arg) {
        super.update(o, arg);
        if (o instanceof DialogShowViewModel){

        }
    }
}
