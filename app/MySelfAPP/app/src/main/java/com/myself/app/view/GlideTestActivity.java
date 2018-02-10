package com.myself.app.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Observable;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class GlideTestActivity extends BaseActivity {
    /**
     * GlideImageLoader.init(Context context) //初始化
     * GlideImageLoader.trimMemory(int level);
     * GlideImageLoader.clearAllMemoryCaches();
     * GlideImageLoader.getActualLoader(); //获取当前的loader
     * GlideImageLoader.with(Context context) //加载图片
     * GlideImageLoader.saveImageIntoGallery(String url) // 保存图片到相册
     * GlideImageLoader.pauseRequests() //取消请求
     * GlideImageLoader.resumeRequests() //回复的请求（当列表在滑动的时候，调用pauseRequests()取消请求，滑动停止时，调用resumeRequests()恢复请求 等等）
     * GlideImageLoader.clearDiskCache()//清除磁盘缓存(必须在后台线程中调用)
     * GlideImageLoader.clearMomoryCache(View view) //清除指定view的缓存
     * GlideImageLoader.clearMomory() // 清除内存缓存(必须在UI线程中调用)
     * url(String url) //支持filepath、图片链接、contenProvider、资源id四种
     * thumbnail(float thumbnail)//缩略图
     * rectRoundCorner(int rectRoundRadius, int overlayColorWhenGif) //形状为圆角矩形时的圆角半径
     * asSquare() //形状为正方形
     * colorFilter(int color) //颜色滤镜
     * diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) //DiskCacheStrategy.NONE :不缓存图片 ／DiskCacheStrategy.SOURCE :缓存图片源文件／DiskCacheStrategy.RESULT:缓存修改过的图片／DiskCacheStrategy.ALL:缓存所有的图片，默认
     * asCircle(int overlayColorWhenGif)//加载圆形图片
     * placeHolder(int placeHolderResId) //占位图
     * override(int oWidth, int oHeight) //加载图片时设置分辨率 a
     * scale(int scaleMode) // CENTER_CROP等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示 ; FIT_CENTER 等比例缩放图片，宽或者是高等于ImageView的宽或者是高 默认：FIT_CENTER
     * animate(int animationId ) 引入动画
     * animate( Animation animation) 引入动画
     * animate(ViewPropertyAnimation.Animator animato) 引入动画
     * asBitmap(BitmapListener bitmapListener)// 使用bitmap不显示到imageview
     * into(View targetView) //展示到imageview
     * colorFilter(int filteColor) //颜色滤镜
     * blur(int blurRadius) ／/高斯模糊
     * brightnessFilter(float level) //调节图片亮度
     * grayscaleFilter() //黑白效果
     * swirlFilter() //漩涡效果
     * toonFilter() //油画效果
     * sepiaFilter() //水墨画效果
     * contrastFilter(float constrasrLevel) //锐化效果
     * invertFilter() //胶片效果
     * pixelationFilter(float pixelationLevel) //马赛克效果
     * sketchFilter() // //素描效果
     * vignetteFilter() //晕映效果
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         GlideImageLoader.with(this)
                .file("file://"+ Environment.getExternalStorageDirectory().getPath()+FOREWARD_SLASH+IMG_NAME)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .into(iv_test11);


        GlideImageLoader.with(this)
                .file(new File(getFilesDir(), IMG_NAME_C))
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .into(iv_test12);

        GlideImageLoader.with(this)
                .asserts(ASSERTS_PATH+IMG_NAME_C)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .rectRoundCorner(50)
                .into(iv_test13);

        GlideImageLoader.with(this)
                .raw(ANDROID_RESOURCE+getPackageName()+RAW+R.raw.jpeg_test)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .asCircle()
                .into(iv_test14);

        GlideImageLoader.with(this)
                .raw(ANDROID_RESOURCE+getPackageName()+RAW+R.raw.jpeg_test)
                .placeHolder(R.mipmap.ic_launcher)
                .scale(ScaleMode.FIT_CENTER)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .asSquare()
                .into(iv_test15);
         */
    }

    @Override
    public void update(Observable o, Object arg) {
        super.update(o, arg);
    }
}
