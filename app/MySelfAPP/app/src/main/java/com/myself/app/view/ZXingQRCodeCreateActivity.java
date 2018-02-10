package com.myself.app.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.myself.app.R;
import com.myself.app.databinding.ActivityZxingCreateBinding;
import com.myself.app.viewModel.ZXingQRCodeCreateViewModel;


/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class ZXingQRCodeCreateActivity extends BaseActivity {
    ActivityZxingCreateBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_zxing_create);
        ZXingQRCodeCreateViewModel viewModel = new ZXingQRCodeCreateViewModel(this);
        binding.setViewModel(viewModel);

        setupObservable(viewModel, this);
        viewModel.setBinding(binding);
    }
}
