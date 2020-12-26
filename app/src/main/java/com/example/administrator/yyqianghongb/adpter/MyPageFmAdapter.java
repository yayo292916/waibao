package com.example.administrator.yyqianghongb.adpter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class MyPageFmAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String>topList;

    public MyPageFmAdapter(FragmentManager fm, List<Fragment> list, List<String>topList) {
        super(fm);
        this.list=list;
        this.topList=topList;
    }
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
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return topList.get(position);
    }
}