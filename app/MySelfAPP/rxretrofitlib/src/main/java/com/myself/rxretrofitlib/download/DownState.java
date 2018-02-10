package com.myself.rxretrofitlib.download;

/**
 * 下载状态
 * Created by Administrator on 2017/6/16 0016.
 */

public enum DownState {
    START(0),
    DOWN(1),
    PAUSE(2),
    STOP(3),
    ERROR(4),
    FINISH(5);

    private int state;

    DownState(int state){
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
