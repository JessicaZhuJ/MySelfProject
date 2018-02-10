package com.myself.app.viewModel;

import android.content.Context;
import android.view.View;

import java.util.Observable;

/**
 * 抽象ViewModel类，其他的具体ViewModel继承此类
 * 继承被观察者类，用于向观察者类的对应的View类发送通知更新UI
 * Created by zhujl on 2017/6/19 0019.
 */

public abstract class ViewModel extends Observable {

    /**
     * 统一的构造方法
     *
     * @param context 上下文环境
     */
    public ViewModel(Context context) {
    }

    /**
     * 点击事件方法，需要用到的重写，否则可不重
     * onClick方法在xml文件中配置： android:onClick="@{viewModel::onClick}"
     *
     * @param view view
     */
    public void onClick(View view) {
    }

    /**
     * 更新数据
     *
     * @param arg 需要传递的参数
     */
    public void update(Object arg) {
        setChanged();
        notifyObservers(arg);
    }

    /**
     * 更新数据
     */
    public void update() {
        setChanged();
        notifyObservers();
    }
}
