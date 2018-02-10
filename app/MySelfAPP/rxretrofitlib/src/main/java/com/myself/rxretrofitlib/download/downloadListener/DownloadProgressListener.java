package com.myself.rxretrofitlib.download.downloadListener;

/**
 * 成功回调处理
 * Created by Administrator on 2017/6/19 0019.
 */

public interface DownloadProgressListener {
    /**
     * 下载进度
     * @param read read
     * @param count count
     * @param done done
     */
    void update(long read,long count,boolean done);
}
