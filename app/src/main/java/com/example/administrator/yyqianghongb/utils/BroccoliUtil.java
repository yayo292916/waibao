package com.example.administrator.yyqianghongb.utils;

import android.graphics.Color;
import android.view.View;
import android.view.animation.LinearInterpolator;

import me.samlss.broccoli.Broccoli;
import me.samlss.broccoli.BroccoliGradientDrawable;
import me.samlss.broccoli.PlaceholderParameter;

/**
 * Created by 杨勇 on 2020/11/10.
 * QQ邮箱：824343111@qq.com
 * 预加载占位图
 */
public class BroccoliUtil {
    private Broccoli broccoli;

    public BroccoliUtil() {
        broccoli = new Broccoli();
    }

    public void addBroccoli(View... views) {
        if (broccoli == null)
            return;
        for (View view : views
        ) {
            broccoli.addPlaceholder(new PlaceholderParameter.Builder()
                    .setView(view)
                    .setDrawable(new BroccoliGradientDrawable(Color.parseColor("#DDDDDD"),
                            Color.parseColor("#CCCCCC"), 0, 1000, new LinearInterpolator())).build());
        }
    }

    public void show() {
        if (broccoli != null)
            broccoli.show();
    }

    public void remove() {
        if (broccoli != null)
            broccoli.removeAllPlaceholders();
    }
}
