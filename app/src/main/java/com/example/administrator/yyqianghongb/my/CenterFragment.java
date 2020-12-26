package com.example.administrator.yyqianghongb.my;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.base.BaseFragmentNewest;
import com.example.administrator.yyqianghongb.dialog.TipsDialog;
import com.example.administrator.yyqianghongb.login.LoginActivity;
import com.example.administrator.yyqianghongb.user.User;
import com.example.administrator.yyqianghongb.utils.MyToast;
import com.example.administrator.yyqianghongb.view.shapeTvPage.ShapeLinearLayout;

import butterknife.BindView;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class CenterFragment extends BaseFragmentNewest {
    @BindView(R.id.coll)
    ShapeLinearLayout coll;
    @BindView(R.id.visit)
    ShapeLinearLayout visit;
    @BindView(R.id.outLogin)
    ImageView outLogin;
    @BindView(R.id.name)
    TextView name;

    @Override
    public int initLayout() {
        return R.layout.center;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData(Context mContext) {
        name.setText(User.getInstance().getuName());
    }

    @Override
    public void initListener() {
        coll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CollActivity.class));
            }
        });

        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), VisitActivity.class));
            }
        });

        outLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipsDialog tipsDialog = new TipsDialog(getContext(), TipsDialog.DELETE_OUT_LOGIN).init();
                tipsDialog.setOnBtnClick(new TipsDialog.OnBtnClick() {
                    @Override
                    public void leftClick() {

                    }

                    @Override
                    public void rightClick() {
                        clearData();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        MyToast.addToast(getContext(), "退出成功！");
                        getActivity().finish();
                    }
                });
                tipsDialog.showDialog();
            }
        });
    }

    private void clearData() {

    }
}
