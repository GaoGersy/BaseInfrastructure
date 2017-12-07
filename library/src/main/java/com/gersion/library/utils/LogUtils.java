package com.gersion.library.utils;

import android.os.Environment;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {
    public static void d(Object object) {
        if (object == null) {
            Logger.e("出错了，你传进来的是一个 “ NULL ” 哦！");
        } else {
            Logger.d(object);
        }
    }

    public static void e(Object object) {
        if (object == null) {
            Logger.e("出错了，你传进来的是一个 “ NULL ” 哦！");
        } else {
            Logger.e("" + object);
        }
    }

    public static void json(Object object) {
        if (object == null) {
            Logger.e("出错了，你传进来的是一个 “ NULL ” 哦！");
        } else {
            Logger.json((String)object);
        }
    }

    public static void xml(Object object) {
        if (object == null) {
            Logger.e("出错了，你传进来的是一个 “ NULL ” 哦！");
        } else {
            Logger.xml("请求结果：\n"+object);
        }
    }

    public static void saveLogFile(String dirPath, String str) {
        try {
            long timestamp = System.currentTimeMillis();
            // 用于格式化日期,作为日志文件名的一部分
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat TempStampformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = formatter.format(new Date());
            String fileName = time + ".log";
            String path = "";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File appDir = new File(dirPath);
                if (!appDir.exists()) {
                    appDir.mkdirs();
                }
                path = dirPath + fileName;
                FileOutputStream outStream = new FileOutputStream(path, true);
                String tempStamp = "=====" + TempStampformatter.format(timestamp) + "=====";
                outStream.write(tempStamp.getBytes());
                outStream.write("\r\n".getBytes());
                outStream.write(str.getBytes());
                outStream.write("\r\n".getBytes());
                outStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
