package com.myself.app.view;

import android.app.Activity;

import java.util.Observable;
import java.util.Observer;

/**
 * 基本Activity类，主要是封装通用的方法以及继承实现通用的类，接口，减少代码量
 * Created by zhujl on 2017/6/19 0019.
 */

public class BaseActivity extends Activity implements Observer {
    private Observable observable;
    private Observer observer;

    /**
     * 观察者类的更新方法，在接收到被观察者发出的setChanged()与notifyObservers()/notifyObservers(Object arg)
     * 方法时被调用
     */
    @Override
    public void update(Observable o, Object arg) {

    }

    /**
     * 观察者类，必须在使用观察与被观察的作用前调用此方法，否则会没有响应
     *
     * @param observable
     * @param observer
     */
    public void setupObservable(Observable observable, Observer observer) {
        this.observable = observable;
        this.observer = observer;
        observable.addObserver(observer);
    }

    /**
     * 观察者类不会被回收，所以需要自己添加回收方法进行回收
     * 在页面销毁的同时要吧观察者类清除，否则会出现内存泄露
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (observable != null && observer != null) {
            observable.deleteObserver(observer);
        }
    }
}
