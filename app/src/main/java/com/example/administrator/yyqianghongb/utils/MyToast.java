package com.example.administrator.yyqianghongb.utils;

import android.content.Context;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MyToast {

    public static void addToast(Context context, String hint){
        if (hint.equals("登录成功"))
            return;
        ToastUtils.show(hint);
    }

    public static void addToast(String hint) {
        if (hint.equals("登录成功"))
            return;
        ToastUtils.show(hint);
    }
    public static Toast addToastGet(String hint) {
        ToastUtils.getToast().setText(hint);
        return ToastUtils.getToast();
    }
}