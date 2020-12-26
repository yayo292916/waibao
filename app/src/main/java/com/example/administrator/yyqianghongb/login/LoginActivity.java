package com.example.administrator.yyqianghongb.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.base.BaseActivityNewest;
import com.example.administrator.yyqianghongb.bean.GetLoginBean;
import com.example.administrator.yyqianghongb.dialog.LoadDialog;
import com.example.administrator.yyqianghongb.main.MainActivity;
import com.example.administrator.yyqianghongb.user.User;
import com.example.administrator.yyqianghongb.user.UserData;
import com.example.administrator.yyqianghongb.utils.Md5Util;
import com.example.administrator.yyqianghongb.utils.MyEasyHttp;
import com.example.administrator.yyqianghongb.utils.MyLoge;
import com.example.administrator.yyqianghongb.utils.MyToast;
import com.example.administrator.yyqianghongb.utils.SpUtil;
import com.example.administrator.yyqianghongb.view.shapeTvPage.ShapeTextView;

import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class LoginActivity extends BaseActivityNewest {

    @BindView(R.id.id)
    EditText id;
    @BindView(R.id.mima)
    EditText mima;
    @BindView(R.id.jizhumima)
    TextView jizhumima;
    @BindView(R.id.commit)
    ShapeTextView commit;
    private boolean jizhu = true;
    private boolean isCommit;
    private LoadDialog loadDialog;
    private UserData userData;

    @Override
    protected int setStatusBar() {
        return 0;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.login;
    }

    @Override
    protected void initView() {
        loadDialog = new LoadDialog(getContext()).init();
    }

    @Override
    protected void initData() {
        if (!User.getInstance().isQuanxian()){
            jizhu = false;
            Drawable drawable = getResources().getDrawable(jizhu ? R.drawable.ooed : R.drawable.oo);
            jizhumima.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null);
        }else {
            userData = SpUtil.getObject(getContext(), "UserData");
            if (userData != null) {
                if (userData.isJizhu()) {
                    id.setText(userData.getuName());
                    mima.setText(userData.getPwd());
                    jizhu = true;
                    judgeIsCommit();
                }else {
                    jizhu = false;
                }
            } else {
                jizhu = true;
                userData = new UserData();
            }
            Drawable drawable = getResources().getDrawable(jizhu ? R.drawable.ooed : R.drawable.oo);
            jizhumima.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null);
        }
    }

    @Override
    protected void initListener() {
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCommit)
                    return;
                login();
            }
        });
        jizhumima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!User.getInstance().isQuanxian()) {
                    MyToast.addToast("未打开权限不能使用该功能");
                    return;
                }
                jizhu = !jizhu;
                Drawable drawable = getResources().getDrawable(jizhu ? R.drawable.ooed : R.drawable.oo);
                jizhumima.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null);
            }
        });
        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                judgeIsCommit();
            }
        });
        mima.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                judgeIsCommit();
            }
        });
    }

    private void judgeIsCommit() {
        if (!TextUtils.isEmpty(mima.getText().toString()) && !TextUtils.isEmpty(id.getText().toString())) {
            commit.setShapeSolidColor(0xFFEE3D37);
            isCommit = true;
        } else {
            isCommit = false;
            commit.setShapeSolidColor(0xFFD8D8D8);
        }
        commit.setUseShape();
    }

    private void login() {
        loadDialog.showDialog();
        HashMap<String, Object> maps = new HashMap<>();
        maps.put("uname", id.getText().toString());
        maps.put("pwd", Md5Util.encode(mima.getText().toString()));
        MyEasyHttp.post(getContext(), "user/login", maps, GetLoginBean.class, new MyEasyHttp.OnResult<GetLoginBean>() {
            @Override
            public void onSuccess(GetLoginBean response) {
                if (response.getStatus() == 200) {
                    User.getInstance().setToken(response.getObj().getToken());
                    User.getInstance().setUid(response.getObj().getUser().getUserid());
                    User.getInstance().setuName(response.getObj().getUser().getUname());
                    if (userData!=null) {
                        userData.setJizhu(jizhu);
                        userData.setuName(response.getObj().getUser().getUname());
                        userData.setPwd(mima.getText().toString());
                        SpUtil.putObject(getContext(), "UserData", userData);
                        MyLoge.addLoge("userData",userData.toString());
                    }
                    startActivity(new Intent(getContext(), MainActivity.class));
                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                } else {
                    MyToast.addToast(response.getMsg());
                }
                loadDialog.closeDialog();
            }

            @Override
            public void onError(String e) {
                MyToast.addToast(e);
                loadDialog.closeDialog();
            }
        });
    }

}
