package com.example.administrator.yyqianghongb.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.SplashActivity;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 杨勇 on 2019/8/27.
 * QQ邮箱：824343111@qq.com
 */
public abstract class BaseActivityNewest extends AppCompatActivity {
    private Unbinder binder;
    public final int BLACK=0;
    public final int WHITE=1;
    private ImmersionBar mImmersionBar;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            Intent intent=new Intent(this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        context = this;
        mImmersionBar = ImmersionBar.with(this);
        initStatusBar(setStatusBar());
        setContentView(getLayoutId(savedInstanceState));
        binder = ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    public Context getContext() {
        return context;
    }

    private void initStatusBar(int status) {
        switch (status){
            case 0:
                mImmersionBar.statusBarDarkFont(true);
                mImmersionBar.init();
                break;
            case 1:
                mImmersionBar.init();
                break;
            default:
                //设置透明状态栏
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    getWindow().setStatusBarColor(0x00000000);
                }
                if (mImmersionBar != null)
                    mImmersionBar.reset();
                break;
        }
    }
    /**
     * 设置沉侵式 0黑色 1 白色 其他 不使用
     */
    protected abstract int setStatusBar();

    /**
     * 布局ID
     */
    protected  abstract int getLayoutId(Bundle savedInstanceState);

    /**
     * 初始化view
     */
    protected abstract void initView();


    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听
     */
    protected abstract void initListener();


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (checkDoubleClick(intent)) {
            super.startActivityForResult(intent, requestCode);
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_right);
    }

    public void openActivity(Class clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }
    public void onResume() {
        super.onResume();
    }
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        binder.unbind();
        EventBus.getDefault().unregister(this);
        if (mImmersionBar != null)
            mImmersionBar.reset();
        super.onDestroy();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String mActivityJumpTag;        //activity跳转tag
    private long mClickTime;                //activity跳转时间

    /**
     * 检查是否重复跳转，不需要则重写方法并返回true
     */
    protected boolean checkDoubleClick(Intent intent) {
        // 默认检查通过
        boolean result = true;
        // 标记对象
        String tag;
        if (intent.getComponent() != null) { // 显式跳转
            tag = intent.getComponent().getClassName();
        }else if (intent.getAction() != null) { // 隐式跳转
            tag = intent.getAction();
        }else {
            return true;
        }

        if (tag.equals(mActivityJumpTag) && mClickTime >= SystemClock.uptimeMillis() - 500) {
            // 检查不通过
            result = false;
        }

        // 记录启动标记和时间
        mActivityJumpTag = tag;
        mClickTime = SystemClock.uptimeMillis();
        return result;
    }

}