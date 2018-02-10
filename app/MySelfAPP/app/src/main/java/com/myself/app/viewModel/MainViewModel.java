package com.myself.app.viewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.ObservableField;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.myself.app.R;
import com.myself.app.utils.Constants;
import com.myself.app.utils.Utils;
import com.myself.app.view.ConfigChangeActivity;
import com.myself.app.view.DialogShowActivity;
import com.myself.app.view.FrescoTestActivity;
import com.myself.app.view.FuncFragmentActivity;
import com.myself.app.view.HttpTestActivity;
import com.myself.app.view.ListViewMenuActivity;
import com.myself.app.view.RefreshActivity;
import com.myself.app.view.ZXingQRCodeCreateActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.wefika.calendar.CollapseCalendarView;
import com.wefika.calendar.manager.CalendarManager;

import org.joda.time.LocalDate;

import java.util.Date;

/**
 * 与xml文件匹配的所有参数和方法都要是public类型
 * Created by Administrator on 2017/6/12 0012.
 */

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";
    private Activity context;
    public ObservableField<String> tvText;//参数的名字必须与xml文件中的相对应android:text="@{viewModel.tvText}"
    private static final int ZXING_REQ_CODE = 11;

    public MainViewModel(Activity context) {
        super(context);
        this.context = context;
        tvText = new ObservableField<>("Let's begin");

        CalendarManager manager = new CalendarManager(LocalDate.now(), CalendarManager.State.MONTH, new LocalDate(0), LocalDate.now().plusYears(4));
        CollapseCalendarView calendarView = (CollapseCalendarView) context.findViewById(R.id.calendar);
        calendarView.init(manager);
        // 获取当前选中日期
//        calendarView.getSelectedDate();


    }

    /**
     * 重写点击事件
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn:
                Intent intent = new Intent(context, HttpTestActivity.class);
                context.startActivity(intent);
//                update();
                break;
            case R.id.dialog_show_btn:
                intent = new Intent(context, DialogShowActivity.class);
                context.startActivity(intent);
                break;
            case R.id.fresco_btn:
                intent = new Intent(context, FrescoTestActivity.class);
                context.startActivity(intent);
                break;
            case R.id.zxing_btn:
//                PermissionRequest permissionRequest =
                intent = new Intent(context, CaptureActivity.class);
                context.startActivityForResult(intent, ZXING_REQ_CODE);
                break;
            case R.id.zxing_create_btn:
                intent = new Intent(context, ZXingQRCodeCreateActivity.class);
                context.startActivity(intent);
                break;
            case R.id.pull_to_refresh_btn:
                intent = new Intent(context, RefreshActivity.class);
                context.startActivity(intent);
                break;
            case R.id.listView_menu_btn:
                intent = new Intent(context, ListViewMenuActivity.class);
                context.startActivity(intent);
                break;
            case R.id.custom_refresh_btn:
                intent = new Intent(context, FuncFragmentActivity.class);
                context.startActivity(intent);
            case R.id.recycler_view_btn:
                intent = new Intent(context, FuncFragmentActivity.class);
                Constants.fragmentName = Constants.FragmentName.RECYCLER_VIEW;
                context.startActivity(intent);
                break;
            case R.id.config_change_btn:
                intent = new Intent(context, ConfigChangeActivity.class);
                context.startActivity(intent);
                break;
            case R.id.raw_load:
                Date date = new Date();
                boolean isBefore2001 = date.before(new Date(2001, 1, 1));
                if (isBefore2001) {
                    Utils.changeTimeSoundPool(context);
                }
                break;
        }
    }


}
