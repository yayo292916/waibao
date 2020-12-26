package com.example.administrator.yyqianghongb.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.utils.PxtoDpUntils;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

/**
 * Created by 王彬帆
 * Email：1329898078@qq.com
 * on 2020/6/9.
 */
public class BackView extends View {
    private int color = 0xff222222;
    private float backWith = 3f;
    private Context context;
    public BackView(Context context) {
        this(context,null);
    }

    public BackView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public BackView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(attrs, context);
        initPaint();
    }


    private void initView(AttributeSet attrs,Context context){
        if (attrs==null){
            backWith = PxtoDpUntils.dp2px(context,1.8f);
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BackView);
        color = a.getColor(R.styleable.BackView_color_b,color);
        backWith = a.getDimension(R.styleable.BackView_back_width,PxtoDpUntils.dp2px(context,1.8f));
        a.recycle();
    }


    private Paint paint;
    private void initPaint(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(backWith);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(PxtoDpUntils.dp2pxInt(context,22),PxtoDpUntils.dp2pxInt(context,25));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(getWidth() / 3 * 2, getHeight() / 5, getWidth()/3, getHeight() / 2, paint);
        canvas.drawLine(getWidth()/3, getHeight() / 2, getWidth()/3*2, getHeight()/5*4, paint);
    }


    /**
     *
     * @param color 颜色
     */
    public void setColor(@ColorInt int color){
        paint.setColor(color);
        invalidate();
    }
}
