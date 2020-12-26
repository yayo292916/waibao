package com.example.administrator.yyqianghongb.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.base.BaseActivityNewest;
import com.example.administrator.yyqianghongb.my.CenterFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class MainActivity extends BaseActivityNewest {
    @BindView(R.id.allPage)
    RelativeLayout allPage;
    @BindView(R.id.home_icon)
    ImageView homeIcon;
    @BindView(R.id.home_icon_text)
    TextView homeIconText;
    @BindView(R.id.hof)
    LinearLayout hof;
    @BindView(R.id.fo_icon)
    ImageView foIcon;
    @BindView(R.id.fo_icon_text)
    TextView foIconText;
    @BindView(R.id.fof)
    LinearLayout fof;
    private FragmentManager fragmentManager;
    private Fragment[] fragments = new Fragment[2];
    private FragmentTransaction transaction;
    private int now_count;

    @Override
    protected int setStatusBar() {
        return 0;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        initFragment();
    }

    @Override
    protected void initListener() {
        hof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceFragment(0);
                homeIcon.setImageResource(R.drawable.home_page_icon_checked);
                homeIconText.setTextColor(0xff333333);
            }
        });
        fof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceFragment(1);
                foIcon.setImageResource(R.drawable.my_icon_checked);
                foIconText.setTextColor(0xff333333);
            }
        });
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        fragments[0] = new HomeFragment();
        transaction.add(R.id.allPage, fragments[0]);
        transaction.commit();
//        choiceFragment(1);
        choiceFragment(0);
        homeIcon.setImageResource(R.drawable.home_page_icon_checked);
        homeIconText.setTextColor(0xff333333);
    }


    public void choiceFragment(int btn_index) {
        initBottomIcon();
        if (now_count != btn_index) {
            transaction = fragmentManager.beginTransaction();
            if (fragments[btn_index] == null) {
                fragments[btn_index] = newFragment(btn_index);
                transaction.add(R.id.allPage, fragments[btn_index]);
            } else {
                transaction.show(fragments[btn_index]);
            }
            transaction.hide(fragments[now_count]);
            transaction.commitAllowingStateLoss();
            now_count = btn_index;
        } else {
            //其他
        }

    }

    //初始化底部栏图标字体
    private void initBottomIcon() {
        homeIcon.setImageResource(R.drawable.home_page_icon_unselected);
        homeIconText.setTextColor(0xff999999);

        foIcon.setImageResource(R.drawable.my_icon_unselected);
        foIconText.setTextColor(0xff999999);
    }

    public Fragment newFragment(int btn_index) {
        switch (btn_index) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CenterFragment();
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
        overridePendingTransition(0, 0);
    }


}
