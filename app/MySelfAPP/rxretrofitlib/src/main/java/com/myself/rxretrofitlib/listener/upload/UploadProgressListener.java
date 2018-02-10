package com.myself.rxretrofitlib.listener.upload;

/**
 *
 * 上传进度回调类
 * Created by Administrator on 2017/6/19 0019.
 */

public interface UploadProgressListener {

    /**
     * 上传进度
     * @param currentBytesCount currentBytesCount
     * @param totalBytesCount totalBytesCount
     */
    void onProgress(long currentBytesCount,long totalBytesCount);
}
