package com.myself.app.viewModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.myself.app.R;
import com.myself.dialog.DialogUIUtils;
import com.myself.dialog.adapter.TieAdapter;
import com.myself.dialog.bean.BuildBean;
import com.myself.dialog.bean.PopuBean;
import com.myself.dialog.bean.TieBean;
import com.myself.dialog.listener.DialogUIDateTimeSaveListener;
import com.myself.dialog.listener.DialogUIItemListener;
import com.myself.dialog.listener.DialogUIListener;
import com.myself.dialog.listener.TdataListener;
import com.myself.dialog.widget.DateSelectorWheelView;

import java.util.ArrayList;
import java.util.List;

import static com.myself.dialog.DialogUIUtils.showToast;

/**
 * Created by Administrator on 2017/6/21 0021.
 */

public class DialogShowViewModel extends ViewModel {
    private Context mContext;
    private Activity mActivity;

    /**
     * 统一的构造方法
     *
     * @param context 上下文环境
     */
    public DialogShowViewModel(Context context) {
        super(context);
        this.mContext = context;
        this.mActivity = (Activity) context;
        DialogUIUtils.init(mContext);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_popu:
                DialogUIUtils.showPopuWindow(mContext, LinearLayout.LayoutParams.MATCH_PARENT,
                        4, mActivity.findViewById(R.id.btn_popu), new TdataListener() {
                            @Override
                            public void initPupoData(List<PopuBean> lists) {
                                for (int i = 0; i < 5; i++) {
                                    PopuBean popu = new PopuBean();
                                    popu.setTitle("item" + i);
                                    popu.setId(i);
                                    lists.add(popu);
                                }
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position) {
                            }
                        });
                break;
            case R.id.btn_custom_alert:
                View rootView = View.inflate(mActivity, R.layout.custom_dialog_layout, null);
                final Dialog dialog = DialogUIUtils.showCustomAlert(mContext, rootView, Gravity.CENTER, true, false).show();
                rootView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUIUtils.dismiss(dialog);
                    }
                });
                break;
            case R.id.btn_custom_bottom_alert:
                View rootViewB = View.inflate(mActivity, R.layout.custom_dialog_bottom_layout, null);
                DialogUIUtils.showCustomBottomAlert(mContext, rootViewB).show();
                break;
            case R.id.btn_system_alert:
                new AlertDialog
                        .Builder(mActivity)
                        .setTitle("标题")
                        .setMessage("这是内容")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create()
                        .show();
                break;
            case R.id.btn_loading:
                DialogUIUtils.showLoading(mContext, "Loading...", false,
                        true, true, true).show();
                break;
            case R.id.btn_md_loading:
                DialogUIUtils.showMdLoading(mContext, "Loading...", true,
                        true, true, true).show();
                break;

            case R.id.btn_alert_multichoose:
                String[] words = new String[]{"hello", "good bye", "thank you"};
                boolean[] choseDefault = new boolean[]{false, false, false};
                DialogUIUtils.showMdMultiChoose(mActivity, "Witch", words, choseDefault,
                        new DialogUIListener() {
                    @Override
                    public void onPositive() {

                    }

                    @Override
                    public void onNegative() {

                    }
                }).show();
                break;
            case R.id.btn_alert_singlechoose:
                String[] words2 = new String[]{"1", "2", "3"};
                DialogUIUtils.showSingleChoose(mActivity, "单选", 0, words2,
                        new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text + "--" + position);
                    }
                }).show();
                break;
            case R.id.btn_md_alert:
                DialogUIUtils.showMdAlert(mActivity, "标题",
                        "哈哈这就是信息", new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        showToast("onPositive");
                    }

                    @Override
                    public void onNegative() {
                        showToast("onNegative");
                    }

                }).show();
                break;
            case R.id.btn_tie_alert:
                DialogUIUtils.showAlert(mActivity, "标题", "哈哈这就是信息", "",
                        "", "确定", "", true,
                        true, true, new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        showToast("onPositive");
                    }

                    @Override
                    public void onNegative() {
                        showToast("onNegative");
                    }

                }).show();
                break;
            case R.id.btn_alert_input:
                DialogUIUtils.showAlert(mActivity, "登录", "",
                        "请输入用户名", "请输入密码", "登录", "取消",
                        false, true, true, new DialogUIListener() {
                    @Override
                    public void onPositive() {

                    }

                    @Override
                    public void onNegative() {

                    }

                    @Override
                    public void onGetInput(CharSequence input1, CharSequence input2) {
                        super.onGetInput(input1, input2);
                        showToast("input1:" + input1 + "--input2:" + input2);
                    }
                }).show();
                break;
            case R.id.btn_center_sheet: {
                List<TieBean> strings = new ArrayList<TieBean>();
                strings.add(new TieBean("1"));
                strings.add(new TieBean("2"));
                strings.add(new TieBean("3"));
                DialogUIUtils.showSheet(mActivity, strings, "", Gravity.CENTER,
                        true, true, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast((String) text);
                    }
                }).show();
            }
            break;
            case R.id.btn_bottom_sheet_cancel: {
                List<TieBean> strings = new ArrayList<TieBean>();
                strings.add(new TieBean("1"));
                strings.add(new TieBean("2"));
                strings.add(new TieBean("3"));
                DialogUIUtils.showSheet(mActivity, strings, "取消", Gravity.BOTTOM, true, true, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text + "---" + position);
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("取消");
                    }
                }).show();
            }
            break;
            case R.id.btn_md_bottom_vertical:
                List<TieBean> datas2 = new ArrayList<TieBean>();
                datas2.add(new TieBean("1"));
                datas2.add(new TieBean("2"));
                datas2.add(new TieBean("3"));
                datas2.add(new TieBean("4"));
                datas2.add(new TieBean("5"));
                datas2.add(new TieBean("6"));
                TieAdapter adapter = new TieAdapter(mContext, datas2, true);
                BuildBean buildBean = DialogUIUtils.showMdBottomSheet(mActivity, true,
                        "", datas2, 0, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text + "---" + position);
                    }
                });
                buildBean.mAdapter = adapter;
                buildBean.show();
                break;
            case R.id.btn_md_bottom_horizontal:
                List<TieBean> datas3 = new ArrayList<TieBean>();
                datas3.add(new TieBean("1"));
                datas3.add(new TieBean("2"));
                datas3.add(new TieBean("3"));
                datas3.add(new TieBean("4"));
                datas3.add(new TieBean("5"));
                datas3.add(new TieBean("6"));
                DialogUIUtils.showMdBottomSheet(mActivity, false, "标题", datas3, 4, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        showToast(text + "---" + position);
                    }
                }).show();
                break;

            case R.id.btn_toast_top:
                DialogUIUtils.showToastTop("上部的Toast弹出方式");
                break;
            case R.id.btn_toast_center:
                DialogUIUtils.showToastCenter("中部的Toast弹出方式");
                break;
            case R.id.btn_toast:
                showToast("默认的Toast弹出方式");
                break;
            case R.id.btn_select_ymd: {
                DialogUIUtils.showDatePick(mActivity, Gravity.CENTER, "选择日期",
                        System.currentTimeMillis() + 60000, DateSelectorWheelView.TYPE_YYYYMMDD,
                        0, new DialogUIDateTimeSaveListener() {
                    @Override
                    public void onSaveSelectedDate(int tag, String selectedDate) {

                    }
                }).show();
            }
            break;
            case R.id.btn_select_ymdhm: {
                DialogUIUtils.showDatePick(mActivity, Gravity.CENTER, "选择日期",
                        System.currentTimeMillis() + 60000, DateSelectorWheelView.TYPE_YYYYMMDDHHMM,
                        0, new DialogUIDateTimeSaveListener() {
                    @Override
                    public void onSaveSelectedDate(int tag, String selectedDate) {

                    }
                }).show();
            }
            break;
            case R.id.btn_select_ymdhms: {
                DialogUIUtils.showDatePick(mActivity, Gravity.BOTTOM, "选择日期",
                        System.currentTimeMillis() + 60000, DateSelectorWheelView.TYPE_YYYYMMDDHHMMSS,
                        0, new DialogUIDateTimeSaveListener() {
                    @Override
                    public void onSaveSelectedDate(int tag, String selectedDate) {

                    }
                }).show();
            }
            break;
        }
    }
}
