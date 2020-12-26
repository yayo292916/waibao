package com.example.administrator.yyqianghongb.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


import com.example.administrator.yyqianghongb.R;

import androidx.annotation.Nullable;

/**
 * Created by 杨勇 on 2020/7/25.
 * QQ邮箱：824343111@qq.com
 * 自定义 箭头 view
 */
public class YYArrowheadView extends View {
    private Paint paint;
    private int height, width;
    private int paddingLeft,paddingTop,paddingRight,paddingBottom;
    public enum Orientation {
        LEFT(0),
        TOP(1),
        RIGHT(2),
        BOTTOM(3);

        Orientation(int value) {
            this.value = value;
        }

        private int value;

        public int getValue() {
            return value;
        }
    }

    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 3;
    private int orientation;
    private float strokeWidth;
    private int color;
    private float safe;

    public YYArrowheadView(Context context) {
        this(context, null);
    }

    public YYArrowheadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YYArrowheadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.YYArrowheadView);
        orientation = arr.getInteger(R.styleable.YYArrowheadView_orientation, LEFT);
        strokeWidth = arr.getFloat(R.styleable.YYArrowheadView_arrowheadWidth, 1);
        color = arr.getColor(R.styleable.YYArrowheadView_arrowheadColor, 0xff333333);
        arr.recycle();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(color);
        paint.setStrokeWidth(dp2px(strokeWidth));
        safe = dp2px(strokeWidth) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (orientation) {
            case 0:
                canvas.drawLine(width - safe + paddingLeft, 0 + safe + paddingTop, 0 + safe + paddingLeft , height / 2  + paddingTop, paint);
                canvas.drawLine(0 + safe + paddingLeft, height / 2  + paddingTop, width - safe + paddingLeft, height - safe + paddingTop, paint);
                break;
            case 1:
                canvas.drawLine(0 + safe + paddingLeft, height - safe + paddingTop, width / 2 + paddingLeft, 0 + safe + paddingTop, paint);
                canvas.drawLine(width / 2 + paddingLeft, 0 + paddingTop, width + paddingLeft, height + paddingTop, paint);
                break;
            case 2:
                canvas.drawLine(0 + safe + paddingLeft , 0 + safe + paddingTop, width - safe + paddingLeft, height / 2 + paddingTop, paint);
                canvas.drawLine(width - safe + paddingLeft, height / 2 + paddingTop, 0 + safe + paddingLeft, height - safe + paddingTop, paint);
                break;
            case 3:
                canvas.drawLine(0 + safe + paddingLeft, 0 + safe + paddingTop, width / 2 + paddingLeft, height - safe + paddingTop, paint);
                canvas.drawLine(width / 2 + paddingLeft, height - safe + paddingTop, width - safe + paddingLeft, 0 + safe + paddingTop, paint);
                break;
            default:
                canvas.drawLine(width - safe + paddingLeft, 0 + safe + paddingTop, 0 + safe + paddingLeft , height / 2  + paddingTop, paint);
                canvas.drawLine(0 + safe + paddingLeft, height / 2  + paddingTop, width - safe + paddingLeft, height - safe + paddingTop, paint);
                break;
        }
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation.getValue();
        invalidate();
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int viewHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);

        paddingLeft = getPaddingLeft();
        paddingTop = getPaddingTop();
        paddingRight = getPaddingRight();
        paddingBottom = getPaddingBottom();
        // 1.81  0.55263
        switch (viewWidthMode) {
            case MeasureSpec.EXACTLY:   //说明在布局文件中使用的是具体值：100dp或者match_parent
                width = viewWidth ;
                if (orientation == 0 || orientation == 2) {
                    height = (int) (width * 1.81);
                } else {
                    height = (int) (width * 0.55263);
                }
                break;
            case MeasureSpec.AT_MOST:  //说明在布局文件中使用的是wrap_content:
                if (orientation == 0 || orientation == 2) {
                    width = (int) dp2px(10) ;
                    height = (int) (width * 0.55263);
                } else {
                    width = (int) dp2px(19) ;
                    height = (int) (width * 0.55263);
                }
                break;
            default:
                if (orientation == 0 || orientation == 2) {
                    width = (int) dp2px(10) ;
                    height = (int) (width * 0.55263);
                } else {
                    width = (int) dp2px(19);
                    height = (int) (width * 0.55263);
                }
                break;
        }
        setMeasuredDimension(width + paddingLeft + paddingRight, height+ paddingTop + paddingBottom);
    }

    public float dp2px(float dpValue) {
        float scale = this.getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }

}
