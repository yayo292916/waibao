package com.example.administrator.yyqianghongb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import static com.example.administrator.yyqianghongb.common.GlobalVariable.isLog;


/**
 * Created by Administrator on 2017/7/11.
 */

public class MyLoge {

    public static void addLoge(String tile, String text) {
        if (null != text && isLog) {
            Log.e(tile, text);
        }

    }

    public static void loge(Activity tile, String text) {
        if (null != text && isLog) {
            Log.e(tile + "", text);
        }
    }

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static void addLoge(String tag, String message, Context context) {
        if (isApkInDebug(context)) {
            Log.e(tag, message);
        }
    }
}
