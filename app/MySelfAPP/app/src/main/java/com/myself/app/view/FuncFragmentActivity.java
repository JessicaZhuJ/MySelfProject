package com.myself.app.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.myself.app.R;
import com.myself.app.utils.Constants;
import com.myself.app.view.fragment.CustomRefreshFragment;
import com.myself.app.view.fragment.RecyclerViewFragment;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class FuncFragmentActivity extends FragmentActivity {
    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func_fragment);

        title = (TextView) findViewById(R.id.title);
        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (Constants.fragmentName) {
            case CUSTOM_REFRESH:
                title.setText("Custom Refresh View");
                CustomRefreshFragment userMessageFragment = new CustomRefreshFragment();
                // android.R.id.content refers to the content
                // view of the activity
                fragmentTransaction.replace(R.id.content_layout, userMessageFragment);
                break;
            case RECYCLER_VIEW:
                title.setText("Recycler View");
                RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
                // android.R.id.content refers to the content
                // view of the activity
                fragmentTransaction.replace(R.id.content_layout, recyclerViewFragment);
                break;
        }
        fragmentTransaction.commit();
    }
}
