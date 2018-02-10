package com.myself.app.view;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.Observable;
import java.util.Observer;

/**
 * 需要使用到rx的页面
 * Created by zhujl on 2017/6/21 0021.
 */

public class BaseRxActivity extends RxAppCompatActivity implements Observer {
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
