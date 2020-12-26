package com.example.administrator.yyqianghongb.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.administrator.yyqianghongb.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 杨勇 on 2019/8/27.
 * QQ邮箱：824343111@qq.com
 */
public abstract class BaseFragmentNewest extends Fragment {
    //获取TAG的fragment名称
    protected final String TAG = this.getClass().getSimpleName();
    private Context context;
    private Unbinder unbinder;
    private Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(initLayout(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        getBundle(savedInstanceState);
        initView(rootView);
        initData(context);
        initListener();
        return rootView;
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        activity.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }


    /**
     * 初始化布局
     *
     * @return 布局id
     */
    public abstract int initLayout();

    /**
     * 初始化控件
     *
     * @param view 布局View
     */
    public abstract void initView(final View view);

    /**
     * 初始化、绑定数据
     *
     * @param mContext 上下文
     */
    public abstract void initData(Context mContext);

    /**
     * 初始化、监听事件
     */
    public abstract void initListener();


    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    public void getBundle(Bundle savedInstanceState) {

    }
}