package com.example.administrator.yyqianghongb.adpter;

import android.content.Context;
import android.view.View;

import com.example.administrator.yyqianghongb.utils.PxtoDpUntils;
import com.example.administrator.yyqianghongb.view.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.List;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class MyPageCommonNavigatorAdapter extends CommonNavigatorAdapter {
    private ViewPager vp;
    private List<String> list;
    private int indicatorColor = 0xff333333;

    public MyPageCommonNavigatorAdapter(ViewPager vp, List<String> list) {
        this.vp = vp;
        this.list = list;
    }

    public MyPageCommonNavigatorAdapter(ViewPager vp, List<String> list, int indicatorColor) {
        this.vp = vp;
        this.list = list;
        this.indicatorColor = indicatorColor;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int i) {
        ScaleTransitionPagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
        simplePagerTitleView.setText(list.get(i));
        simplePagerTitleView.setTextSize(17);//设置导航的文字大小
        simplePagerTitleView.setNormalColor(0xff333333);//正常状态下的标题颜色
        simplePagerTitleView.setSelectedColor(0xFFEE3D37);//选中的标题字体颜色
        addClickListener(simplePagerTitleView, i);

        return simplePagerTitleView;
    }

    public void addClickListener(ScaleTransitionPagerTitleView simplePagerTitleView, int i) {
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onItemSelected(i);
                } else {
                    vp.setCurrentItem(i);
                }
            }
        });
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
        linePagerIndicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        linePagerIndicator.setLineWidth(PxtoDpUntils.dp2px(context, 28));
        linePagerIndicator.setColors(0xFFEE3D37);
        linePagerIndicator.setYOffset(PxtoDpUntils.dp2px(context, 2));
        linePagerIndicator.setRoundRadius(5);
        return linePagerIndicator;
    }

    OnItemSelectedListener onItemSelectedListener;

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }
}
