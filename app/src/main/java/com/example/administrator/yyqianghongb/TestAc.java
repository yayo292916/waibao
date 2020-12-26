package com.example.administrator.yyqianghongb;

import android.os.Bundle;

import com.example.administrator.yyqianghongb.base.BaseActivityNewest;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * Created by 杨勇 on 2020/12/3.
 * QQ邮箱：824343111@qq.com
 */
public class TestAc extends BaseActivityNewest {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private List<Fragment>fragmentList = new ArrayList<>();
    private List<String>topList = new ArrayList<>();
    @Override
    protected int setStatusBar() {
        return 0;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_test3;
    }

    @Override
    protected void initView() {
        fragmentList.add(new TestFragment());
        fragmentList.add(new TestFragment());
        topList.add("标题1");
        topList.add("标题2");

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            private int mChildCount;
            @Override
            public void notifyDataSetChanged() {
                mChildCount = getCount();
                super.notifyDataSetChanged();
            }

            @Override
            public int getItemPosition(Object object) {
                if (mChildCount > 0) {
                    mChildCount--;
                    return POSITION_NONE;
                }
                return super.getItemPosition(object);
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return topList.get(position);
            }

        });

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
