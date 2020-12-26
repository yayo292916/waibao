package com.example.administrator.yyqianghongb.adpter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.view.shapeTvPage.ShapeTextView;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.List;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class YYNavigatorAdapter extends CommonNavigatorAdapter {
    private ViewPager vp;
    private List<String> list;
    private Context context;
    private int indicatorColor = Color.RED;
    private int titleSelectedColor = Color.RED;
    private int titleNormalColor = Color.BLACK;
    private int titleMargin;

    public YYNavigatorAdapter(Context context,ViewPager vp, List<String> list) {
        this.context = context;
        this.vp = vp;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
    }

    public void setTitleSelectedColor(int titleSelectedColor) {
        this.titleSelectedColor = titleSelectedColor;
    }

    public void setTitleNormalColor(int titleNormalColor) {
        this.titleNormalColor = titleNormalColor;
    }

    public void setTitleMargin(int titleMargin) {
        this.titleMargin = dp2pxInt(titleMargin);
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int i) {
        CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
        commonPagerTitleView.setContentView(R.layout.item_yytab);

        final TextView titleText = (TextView) commonPagerTitleView.findViewById(R.id.title);
        final ShapeTextView marker = (ShapeTextView) commonPagerTitleView.findViewById(R.id.marker);
        final RelativeLayout layout = (RelativeLayout) commonPagerTitleView.findViewById(R.id.layout);

        titleText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                titleText.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout.getLayoutParams();
                params.width = titleText.getWidth();
                layout.setLayoutParams(params);

            }
        });

        titleText.setText(list.get(i));

        commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

            @Override
            public void onSelected(int index, int totalCount) {
                titleText.setTextColor(Color.RED);
            }

            @Override
            public void onDeselected(int index, int totalCount) {
                titleText.setTextColor(Color.BLACK);
            }

            @Override
            public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
                titleText.setScaleX(1.0f + (0.9f - 1.0f) * leavePercent);
                titleText.setScaleY(1.0f + (0.9f - 1.0f) * leavePercent);
            }

            @Override
            public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                titleText.setScaleX(0.9f + (1.0f - 0.9f) * enterPercent);
                titleText.setScaleY(0.9f + (1.0f - 0.9f) * enterPercent);
            }
        });
        titleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemSelectedListener != null)
                    onItemSelectedListener.onItemSelected(i);
            }
        });

        return commonPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
        linePagerIndicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        linePagerIndicator.setLineWidth(dp2pxInt( 12));
        linePagerIndicator.setColors(indicatorColor);
        linePagerIndicator.setYOffset(dp2pxInt( 2));
        linePagerIndicator.setRoundRadius(linePagerIndicator.getLineHeight()/2);
        return linePagerIndicator;
    }

    OnItemSelectedListener onItemSelectedListener;

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }

    public int dp2pxInt(int dpValue){
        float scale= context.getResources().getDisplayMetrics().density;
        return (int) (dpValue*scale+0.5f);
    }
}
