package com.example.administrator.yyqianghongb.base;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.utils.JudgeActivtiy;

import java.util.Objects;

import androidx.annotation.LayoutRes;

/**
 * Created by 杨勇 on 2020/6/16.
 * QQ邮箱：824343111@qq.com
 */
public abstract class YYBaseDialog {
    private Context context;
    private Dialog dialog;
    public static int BOTTOM = 110;
    public static int CENTER = 111;
    private View inflate;
    private Window window;

    public YYBaseDialog(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {
        dialog = new Dialog(context, R.style.myDialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        inflate = LayoutInflater.from(context).inflate(layoutRes(), null);
        window = dialog.getWindow();
        assert window != null;
        window.requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(inflate);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
        window.setWindowAnimations(R.style.dialogCenterAnim);
        window.setGravity(Gravity.CENTER);
    }

    /**
     * 调用该方法后才能初始数据
     */
    @SuppressWarnings("unchecked")
    public <T extends YYBaseDialog> T init() {
        initData(inflate);
        return (T) this;
    }

    public abstract @LayoutRes
    int layoutRes();

    protected abstract void initData(View inflate);

    public void showDialog() {
        if (JudgeActivtiy.isUseable((Activity) context)) {
            dialog.show();
        }
    }

    public void closeDialog() {
        if (JudgeActivtiy.isUseable((Activity) context)) {
            dialog.dismiss();
        }
    }

    public boolean isShow(){
        return dialog.isShowing();
    }

    public void closeAnimations(){
        window.setWindowAnimations(0);
    }

    /**
     * @param isCancel 是否可以点击外部取消
     */
    public void setCanceledOnTouchOutside(boolean isCancel) {
        dialog.setCanceledOnTouchOutside(isCancel);
    }

    /**
     * @param isCancel 是否可以返回取消
     */
    public void setCancelable(boolean isCancel) {
        dialog.setCancelable(isCancel);
    }

    /**
     * @param gravity 跳窗弹出位置 BOTTOM底部  CENTER 中间
     */
    public void setGravity(int gravity) {
        if (gravity == BOTTOM) {
            Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.BOTTOM);
            dialog.getWindow().setWindowAnimations(R.style.dialogBottomAnim);
        } else if ((gravity == CENTER)) {
            Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.CENTER);
            dialog.getWindow().setWindowAnimations(R.style.dialogCenterAnim);
        }
    }


    public void setWindowsMatch() {
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(attributes);
    }

    public void setWindowsMatchDouble() {
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(attributes);
    }


    /**
     * 拉出软键盘
     */
    public void setPlWindows() {
        if (dialog.getWindow() != null) {
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);//显示软键盘
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); //显示软键盘
        }

    }



    /**
     * @param amount 设置dialog的遮罩 透明度 0 ~ 1 ，0代表全透明
     */
    public void setDimAmount(float amount) {
        Objects.requireNonNull(dialog.getWindow()).setDimAmount(amount);
    }

    public Context getContext() {
        return context;
    }

}
