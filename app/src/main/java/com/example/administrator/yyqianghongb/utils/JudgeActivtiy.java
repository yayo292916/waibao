package com.example.administrator.yyqianghongb.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

/**
 * Created by 杨勇 on 2018/11/24.
 * QQ邮箱：824343111@qq.com
 */

public class JudgeActivtiy {
    public static boolean isUseable(Activity mActivity) {
        if ((null == mActivity) || mActivity.isFinishing() || mActivity.isRestricted()) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                if (mActivity.isDestroyed()) {
                    return false;
                }
            } catch (Exception | Error e) {
                e.printStackTrace();
            }
        }

        return true;
    }
    public static boolean isUseable(Context context) {
        Activity mActivity = (Activity) context;
        if ((null == mActivity) || mActivity.isFinishing() || mActivity.isRestricted()) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                if (mActivity.isDestroyed()) {
                    return false;
                }
            } catch (Exception | Error e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
