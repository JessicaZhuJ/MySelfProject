package com.myself.app.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class LogFileUtil {
    private static String TAG = "LogFileUtil";
    //log日志存放路径
    private static String logPath = null;
    public static String path = Environment.getExternalStorageDirectory().getPath() +
            File.separator + "UAVLiveVideo";

    // 使用  在MainActivity 或 Application 中 LogFileUtil.init(this);

    /**
     * 初始化，须在使用之前设置，最好在Application创建时调用
     *
     * @param context
     */
    public static void init(Context context) {
        //获得文件储存路径,在后面加"/Logs"建立子文件夹
        logPath = getFilePath(context) + "/Logs";
    }

    /**
     * 获得文件存储路径
     *
     * @return
     */
    private static String getFilePath(Context context) {
        return path;
    }

    private static final char VERBOSE = 'v';

    private static final char DEBUG = 'd';

    private static final char INFO = 'i';

    private static final char WARN = 'w';

    private static final char ERROR = 'e';

    public static void v(String tag, String msg) {
        writeToFile(VERBOSE, tag, msg);
    }

    public static void d(String tag, String msg) {
        writeToFile(DEBUG, tag, msg);
    }

    public static void i(String tag, String msg) {
        writeToFile(INFO, tag, msg);
    }

    public static void w(String tag, String msg) {
        writeToFile(WARN, tag, msg);
    }

    public static void e(String tag, String msg) {
        writeToFile(ERROR, tag, msg);
    }

    public static void hex(String tag, byte[] data, int size) {
        byte[] des = null;
        des = copyData(data, size);
        writeToFile(ERROR, tag, bytesToHexString(des));
    }

    private static byte[] copyData(byte[] data, int size){
        byte[] des = new byte[size];
        System.arraycopy(data, 0, des, 0, size);
        return des;
    }

    /**
     * 将log信息写入文件中
     *
     * @param type
     * @param tag
     * @param msg
     */
    private static void writeToFile(char type, String tag, String msg) {

        if (null == logPath) {
            Log.e(TAG, "logPath == null ，未初始化LogToFile");
            return;
        }
        //log日志名，使用时间命名，保证不重复
        String fileName = logPath + "/log_"  + ".txt";
        //log日志内容，可以自行定制
        String log = " " + type + " " + tag + " " + msg  + "\n";

        //如果父路径不存在
        File file = new File(logPath);
        if (!file.exists()) {
            //创建父路径
            file.mkdirs();
        }
        //FileOutputStream会自动调用底层的close()方法，不用关闭
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            //这里的第二个参数代表追加还是覆盖，true为追加，flase为覆盖
            fos = new FileOutputStream(fileName, true);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(log);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    //关闭缓冲流
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);

            stringBuilder.append(" ");
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        stringBuilder.append("\n\n\n\n");
        return stringBuilder.toString();
    }
}
