package com.example.administrator.yyqianghongb.view;

import android.content.Context;
import android.view.Gravity;

import com.hjq.toast.style.BaseToastStyle;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class ToastBlackStyle extends BaseToastStyle {

    public ToastBlackStyle(Context context) {
        super(context);
    }

    @Override
    public int getCornerRadius() {
        return dp2px(30);
    }

    @Override
    public int getBackgroundColor() {
        return 0Xf2EFEFEF;
    }

    @Override
    public int getTextColor() {
        return 0XEE222222;
    }

    @Override
    public float getTextSize() {
        return sp2px(14);
    }

    @Override
    public int getPaddingStart() {
        return dp2px(24);
    }

    @Override
    public int getPaddingTop() {
        return dp2px(16);
    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public int getYOffset() {
        return dp2px(30);
    }
}