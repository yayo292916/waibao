package com.example.administrator.yyqianghongb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.yyqianghongb.base.BaseActivityNewest;
import com.example.administrator.yyqianghongb.login.LoginActivity;
import com.example.administrator.yyqianghongb.main.MainActivity;
import com.example.administrator.yyqianghongb.user.User;
import com.example.administrator.yyqianghongb.utils.PermissionUtil;

public final class SplashActivity extends BaseActivityNewest {
    private ImageView img;
    private Context context;

    @Override
    protected int setStatusBar() {
        return 0;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        context=this;
        img = findViewById(R.id.img);
    }

    @Override
    protected void initData() {
        startActivity(new Intent(context, TestAc.class));
        finish();
        overridePendingTransition(R.anim.anim_splash_in, R.anim.anim_splash_out);
//        PermissionUtil.openPermissionWrite(getContext(), new PermissionUtil.OnGetPermission() {
//            @Override
//            public void success() {
//                User.getInstance().setQuanxian(true);
//                img.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        startActivity(new Intent(context, MainActivity.class));
//                        finish();
//                        overridePendingTransition(R.anim.anim_splash_in,R.anim.anim_splash_out);
//                    }
//                },1000);
//            }
//
//            @Override
//            public void fail() {
//                User.getInstance().setQuanxian(false);
//                img.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        startActivity(new Intent(context, MainActivity.class));
//                        finish();
//                        overridePendingTransition(R.anim.anim_splash_in,R.anim.anim_splash_out);
//                    }
//                },1000);
//            }
//        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onBackPressed() {

    }
}