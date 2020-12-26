package com.example.administrator.yyqianghongb.dialog;

import android.content.Context;
import android.view.View;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.base.YYBaseDialog;

/**
 * Created by 杨勇 on 2020/11/13.
 * QQ邮箱：824343111@qq.com
 */
public class LoadDialog extends YYBaseDialog {
    public LoadDialog(Context context) {
        super(context);
    }

    @Override
    public int layoutRes() {
        return R.layout.dialog_load;
    }

    @Override
    protected void initData(View inflate) {

    }
}
