package com.example.administrator.yyqianghongb.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.yyqianghongb.R;

/**
 * Created by 杨勇 on 2020/12/3.
 * QQ邮箱：824343111@qq.com
 * <p>
 * 适用于 自适应layout 子布局需要一个img背景。img根据layout 大小决定 img大小
 */
public class YYImageWrapRelativeLayout extends RelativeLayout {
    private static final String TAG = "imgTag";
    private ImageView bgImg;
    private int imgHeight, imgWidth;

    public YYImageWrapRelativeLayout(Context context) {
        this(context, null);
    }

    public YYImageWrapRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YYImageWrapRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addBgImg();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int viewWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int viewHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        imgWidth = MeasureSpec.getSize(widthMeasureSpec);
        imgHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (viewHeightMode == MeasureSpec.UNSPECIFIED) {
            int maxChildHeight = 0;
            int childCount = this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = this.getChildAt(i);
                int ch = child.getMeasuredHeight();
                maxChildHeight = Math.max(maxChildHeight, ch);
            }
            imgHeight = maxChildHeight;
        }
        setBgImg();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void setBgImg(){
        if (bgImg != null) {
            RelativeLayout.LayoutParams params = (LayoutParams) bgImg.getLayoutParams();
            params.width = imgWidth;
            params.height = imgHeight;
            bgImg.setLayoutParams(params);
        }
    }


    private void addBgImg() {
        bgImg = new ImageView(getContext());
        bgImg.setImageResource(R.drawable.splash_screen_icon_kn);
        bgImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        bgImg.setTag("imgTag");
        addView(bgImg, 0);
    }

}
