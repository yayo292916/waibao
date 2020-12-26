package com.example.administrator.yyqianghongb.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.utils.PxtoDpUntils;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.TintTypedArray;

/**
 * Created by 杨勇 on 2019/5/8.
 * QQ邮箱：824343111@qq.com
 */
public class LineLinearLayout extends LinearLayout {
    private Paint paint;//线
    private Context context;
    private int lineColor ;//线颜色
    private int lineWidth;//线的 宽度
    private int linePaddingLeft;//线距左边边距；
    private int lineMarginBottom;//线距底部距离；
    private int layoutH;

    public LineLinearLayout(Context context) {
        super(context);
        this.context=context;
        initView();
    }

    public LineLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initAttrs(attrs);
        initView();
    }

    public LineLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initAttrs(attrs);
        initView();
    }

    @SuppressLint("RestrictedApi")
    private void initAttrs(AttributeSet attrs) {
        TintTypedArray tta = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                R.styleable.LineLinearLayout);
        lineColor = tta.getColor(R.styleable.LineLinearLayout_line_color,0xff000000);
        linePaddingLeft = PxtoDpUntils.dp2pxInt(context,tta.getInteger(R.styleable.LineLinearLayout_padding_left, 20));
        lineWidth = PxtoDpUntils.dp2pxInt(context,tta.getInteger(R.styleable.LineLinearLayout_line_width, 1));
    }


    private void initView() {
        setWillNotDraw(false);//是OnDraw方法被执行 clearPaint
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(lineColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(lineWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        layoutH = 0;
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
//            this.measureChild(child, widthMeasureSpec, heightMeasureSpec);
//            int cw = child.getMeasuredWidth();
            int ch = child.getMeasuredHeight();
            layoutH += ch;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(linePaddingLeft,0,linePaddingLeft,(layoutH - lineMarginBottom),paint);
    }

    public void setMarginBottom(int s){
        lineMarginBottom = PxtoDpUntils.dp2pxInt(context,s);
        requestLayout();
        invalidate();
    }
}
