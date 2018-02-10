package com.myself.app.viewModel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.myself.app.R;
import com.myself.app.databinding.ActivityZxingCreateBinding;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class ZXingQRCodeCreateViewModel extends ViewModel {
    private Context context;
    private ActivityZxingCreateBinding binding;

    /**
     * 统一的构造方法
     *
     * @param context 上下文环境
     */
    public ZXingQRCodeCreateViewModel(Context context) {
        super(context);
        this.context = context;
    }

    public void setBinding(ActivityZxingCreateBinding binding) {
        this.binding = binding;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_qr_create:
                String textContent = binding.etQrContent.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(context, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.etQrContent.setText("");
                Bitmap mBitmap = CodeUtils.createImage(textContent, 200, 200, BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
                binding.ivQrCode.setImageBitmap(mBitmap);
                break;
            case R.id.btn_qr_create_1:
                textContent = binding.etQrContent.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(context, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.etQrContent.setText("");
                Bitmap mBitmap1 = CodeUtils.createImage(textContent, 200, 200, null);
                binding.ivQrCode.setImageBitmap(mBitmap1);
                break;
        }
    }
}
