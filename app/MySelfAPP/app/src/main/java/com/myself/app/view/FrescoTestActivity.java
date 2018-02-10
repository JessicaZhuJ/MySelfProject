package com.myself.app.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.myself.app.R;
import com.myself.biv.view.BigImageView;
import com.myself.image.FrescoImageLoader;

/**
 * Created by Administrator on 2017/6/28 0028.
 */

public class FrescoTestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);

        BigImageView bigImageView = (BigImageView) this.findViewById(R.id.biv);
        String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRLQkc32zftlCAkUz76t_eUrliP3ylpGP2a92jh0bplDjJyXXOi3aDQQTM";
        String url1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490347504209&di=f6bd1e94c89712f1876df5e36e8f92fe&imgtype=0&src=http%3A%2F%2Fimg.elife.com%2Fforum%2F201411%2F03%2F113034p9rrtuxk8zbzp6zr.jpg";
        FrescoImageLoader.loadBigImage(bigImageView,url1);
    }
}
