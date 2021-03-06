package com.example.administrator.yyqianghongb.view.shapeTvPage;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;


import com.example.administrator.yyqianghongb.R;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by 杨勇 on 2020/5/28.
 * QQ邮箱：824343111@qq.com
 *  实现shape所有的属性
 *
 *
 *  动态设置切记需要在最后调用 setUseShape 才能对设置的参数生效
 * isAutoRadius 是否自动圆弧
 *  sGravity	enum	设置文字对其方式
 * center
 * left
 * right
 * top
 * bottom	默认center
 * sShapeType	enum	设置Shape的类型
 * rectangle
 * oval
 * right
 * line
 * ring	默认rectangle
 * sSolidColor	color	填充颜色	默认
 * sSelectorPressedColor	color	按下时颜色	默认0x20000000
 * sSelectorDisableColor	color	不可点击颜色	默认0x20000000
 * sSelectorNormalColor	color	正常显示的颜色	默认0x20000000
 * sCornersRadius	dimension	shape的四个圆角半径	默认0dp
 * sCornersTopLeftRadius	dimension	shape的左上圆角半径	默认0dp
 * sCornersTopRightRadius	dimension	shape的右上圆角半径	默认0dp
 * sCornersBottomLeftRadius	dimension	shape的左下圆角半径	默认0dp
 * sCornersBottomRightRadius	dimension	shape的右下圆角半径	默认0dp
 * sStrokeDashWidth	dimension	shape的虚线宽度	默认0dp
 * sStrokeDashGap	dimension	shape的虚线间隙	默认0dp
 * sStrokeWidth	dimension	shape的边框宽度	默认0dp
 * sStrokeColor	color	shape的边框颜色	默认0x20000000
 * sSizeWidth	dimension	shape的SizeWidth	默认0dp
 * sSizeHeight	dimension	shape的SizeHeight	默认0dp
 * sGradientOrientation	enum	设置渐变类型
 * TOP_BOTTOM
 * TR_BL
 * RIGHT_LEFT
 * BR_TL
 * BOTTOM_TOP
 * BL_TR
 * LEFT_RIGHT
 * TL_BR	默认rectangle
 * sGradientType	enum	设置渐变类型
 * linear
 * radial
 * sweep	默认rectangle
 * sGradientCenterX	dimension	shape的CenterX	默认0dp
 * sGradientCenterY	dimension	shape的CenterY	默认0dp
 * sGradientGradientRadius	dimension	shape的GradientRadius	默认0dp
 * sGradientStartColor	color	shape的StartColor
 * sGradientCenterColor	color	shape的CenterColor
 * sGradientEndColor	color	shape的EndColor
 * sGradientUseLevel	boolean	shape的UseLevel	默认flase
 * sUseSelector	boolean	是否使用selector	默认false
 */
public class ShapeTextView extends AppCompatTextView {

    private Context mContext;

    private int defaultColor = 0x20000000;
    private int defaultSelectorColor = 0x20000000;
    private boolean isAutoRadius;

    private int solidColor;
    private int selectorPressedColor;
    private int selectorDisableColor;
    private int selectorNormalColor;

    private float cornersRadius;
    private float cornersTopLeftRadius;
    private float cornersTopRightRadius;
    private float cornersBottomLeftRadius;
    private float cornersBottomRightRadius;

    private int strokeWidth;
    private int strokeColor;

    private float strokeDashWidth;
    private float strokeDashGap;

    private int sizeWidth;
    private int sizeHeight;

    private int gradientAngle;
    private int gradientCenterX;
    private int gradientCenterY;
    private int gradientGradientRadius;

    private int gradientStartColor;
    private int gradientCenterColor;
    private int gradientEndColor;

    private int gradientType;

    //"linear"	线形渐变。这也是默认的模式
    private static final int linear = 0;
    //"radial"	辐射渐变。startColor即辐射中心的颜色
    private static final int radial = 1;
    //"sweep"	扫描线渐变。
    private static final int sweep = 2;

    private boolean gradientUseLevel;

    private boolean useSelector;


    //shape的样式
    public static final int RECTANGLE = 0;
    public static final int OVAL = 1;
    public static final int LINE = 2;
    public static final int RING = 3;


    //渐变色的显示方式
    public static final int TOP_BOTTOM = 0;
    public static final int TR_BL = 1;
    public static final int RIGHT_LEFT = 2;
    public static final int BR_TL = 3;
    public static final int BOTTOM_TOP = 4;
    public static final int BL_TR = 5;
    public static final int LEFT_RIGHT = 6;
    public static final int TL_BR = 7;

    //文字显示的位置方式
    public static final int TEXT_GRAVITY_CENTER = 0;
    public static final int TEXT_GRAVITY_LEFT = 1;
    public static final int TEXT_GRAVITY_RIGHT = 2;
    public static final int TEXT_GRAVITY_TOP = 3;
    public static final int TEXT_GRAVITY_BOTTOM = 4;


    private int shapeType;

    private int gravity;

    private GradientDrawable gradientDrawable;

    private ShapeBuilder shapeBuilder;

    public ShapeTextView(Context context) {
        this(context, null);
    }

    public ShapeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        getAttr(attrs);
        init();
    }

    private int height;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height=MeasureSpec.getSize(heightMeasureSpec);
        if (isAutoRadius){
            setShapeCornersRadius(height/2f);
            setUseShape();
        }
    }

    private void getAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.ShapeTextView);

        gravity = typedArray.getInt(R.styleable.ShapeTextView_gravity_s, 0);
        isAutoRadius = typedArray.getBoolean(R.styleable.ShapeTextView_isAutoRadius, false);

        shapeType = typedArray.getInt(R.styleable.ShapeTextView_shapeType, GradientDrawable.RECTANGLE);

        solidColor = typedArray.getColor(R.styleable.ShapeTextView_solidColor, defaultColor);

        selectorPressedColor = typedArray.getColor(R.styleable.ShapeTextView_selectorPressedColor, defaultSelectorColor);
        selectorDisableColor = typedArray.getColor(R.styleable.ShapeTextView_selectorDisableColor, defaultSelectorColor);
        selectorNormalColor = typedArray.getColor(R.styleable.ShapeTextView_selectorNormalColor, defaultSelectorColor);

        cornersRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_cornersRadius, 0);
        cornersTopLeftRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_cornersTopLeftRadius, 0);
        cornersTopRightRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_cornersTopRightRadius, 0);
        cornersBottomLeftRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_cornersBottomLeftRadius, 0);
        cornersBottomRightRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_cornersBottomRightRadius, 0);

        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_strokeWidth, 0);
        strokeDashWidth = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_strokeDashWidth, 0);
        strokeDashGap = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_strokeDashGap, 0);

        strokeColor = typedArray.getColor(R.styleable.ShapeTextView_strokeColor, defaultColor);

        sizeWidth = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_sizeWidth_s, 0);
        sizeHeight = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_sizeHeight_s, dip2px(mContext, 0));

        gradientAngle = (int) typedArray.getFloat(R.styleable.ShapeTextView_gradientAngle, -1);
        gradientCenterX = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_gradientCenterX, 0);
        gradientCenterY = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_gradientCenterY, 0);
        gradientGradientRadius = typedArray.getDimensionPixelSize(R.styleable.ShapeTextView_gradientGradientRadius, 0);

        gradientStartColor = typedArray.getColor(R.styleable.ShapeTextView_gradientStartColor, -1);
        gradientCenterColor = typedArray.getColor(R.styleable.ShapeTextView_gradientCenterColor, -1);
        gradientEndColor = typedArray.getColor(R.styleable.ShapeTextView_gradientEndColor, -1);

        gradientType = typedArray.getInt(R.styleable.ShapeTextView_gradientType, linear);
        gradientUseLevel = typedArray.getBoolean(R.styleable.ShapeTextView_gradientUseLevel, false);

        useSelector = typedArray.getBoolean(R.styleable.ShapeTextView_useSelector, false);

        typedArray.recycle();
    }

    private void init() {
        setClickable(true);
        shapeBuilder = new ShapeBuilder();
        shapeBuilder
                .setShapeType(shapeType)
                .setShapeCornersRadius(cornersRadius)
                .setShapeCornersTopLeftRadius(cornersTopLeftRadius)
                .setShapeCornersTopRightRadius(cornersTopRightRadius)
                .setShapeCornersBottomRightRadius(cornersBottomRightRadius)
                .setShapeCornersBottomLeftRadius(cornersBottomLeftRadius)
                .setShapeSolidColor(solidColor)
                .setShapeStrokeColor(strokeColor)
                .setShapeStrokeWidth(strokeWidth)
                .setShapeStrokeDashWidth(strokeDashWidth)
                .setShapeStrokeDashGap(strokeDashGap)
                .setShapeUseSelector(useSelector)
                .setShapeSelectorNormalColor(selectorNormalColor)
                .setShapeSelectorPressedColor(selectorPressedColor)
                .setShapeSelectorDisableColor(selectorDisableColor)
                .setShapeSizeWidth(sizeWidth)
                .setShapeSizeHeight(sizeHeight)
                .setShapeGradientType(gradientType)
                .setShapeGradientAngle(gradientAngle)
                .setShapeGradientUseLevel(gradientUseLevel)
                .setShapeGradientCenterX(gradientCenterX)
                .setShapeGradientCenterY(gradientCenterY)
                .setShapeGradientStartColor(gradientStartColor)
                .setShapeGradientCenterColor(gradientCenterColor)
                .setShapeGradientEndColor(gradientEndColor)
                .into(this);
        setSGravity();
    }


    /**
     * 设置文字对其方式
     */
    private void setSGravity() {
        switch (gravity) {
            case 0:
                setGravity(Gravity.CENTER);
                break;
            case 1:
                setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                break;
            case 2:
                setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                break;
            case 3:
                setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                break;
            case 4:
                setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
                break;
        }
    }


    /////////////////对外暴露的方法//////////////

    /**
     * 设置Shape类型
     *
     * @param type 类型
     * @return 对象
     */
    public ShapeTextView setShapeType(int type) {
        this.shapeType = type;
        return this;
    }

    /**
     * 设置文字对其方式
     *
     * @param gravity 对齐方式
     * @return 对象
     */
    public ShapeTextView setTextGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * 设置按下的颜色
     *
     * @param color 颜色
     * @return 对象
     */
    public ShapeTextView setShapeSelectorPressedColor(int color) {
        this.shapeBuilder.setShapeSelectorPressedColor(color);
        return this;
    }

    /**
     * 设置正常的颜色
     *
     * @param color 颜色
     * @return 对象
     */
    public ShapeTextView setShapeSelectorNormalColor(int color) {
        this.shapeBuilder.setShapeSelectorNormalColor(color);
        return this;
    }

    /**
     * 设置不可点击的颜色
     *
     * @param color 颜色
     * @return 对象
     */
    public ShapeTextView setShapeSelectorDisableColor(int color) {
        this.shapeBuilder.setShapeSelectorDisableColor(color);
        return this;
    }

    /**
     * 设置填充的颜色
     *
     * @param color 颜色
     * @return 对象
     */
    public ShapeTextView setShapeSolidColor(int color) {
        this.shapeBuilder.setShapeSolidColor(color);
        return this;
    }

    /**
     * 设置边框宽度
     *
     * @param strokeWidth 边框宽度值
     * @return 对象
     */
    public ShapeTextView setShapeStrokeWidth(int strokeWidth) {
        this.shapeBuilder.setShapeStrokeWidth(dip2px(mContext, strokeWidth));
        return this;
    }

    /**
     * 设置边框颜色
     *
     * @param strokeColor 边框颜色
     * @return 对象
     */
    public ShapeTextView setShapeStrokeColor(int strokeColor) {
        this.shapeBuilder.setShapeStrokeColor(strokeColor);
        return this;
    }

    /**
     * 设置边框虚线宽度
     *
     * @param strokeDashWidth 边框虚线宽度
     * @return 对象
     */
    public ShapeTextView setShapeStrokeDashWidth(float strokeDashWidth) {
        this.shapeBuilder.setShapeStrokeDashWidth(dip2px(mContext, strokeDashWidth));
        return this;
    }

    /**
     * 设置边框虚线间隙
     *
     * @param strokeDashGap 边框虚线间隙值
     * @return 对象
     */
    public ShapeTextView setShapeStrokeDashGap(float strokeDashGap) {
        this.shapeBuilder.setShapeStrokeDashGap(dip2px(mContext, strokeDashGap));
        return this;
    }

    /**
     * 设置圆角半径
     *
     * @param radius 半径
     * @return 对象
     */
    public ShapeTextView setShapeCornersRadius(float radius) {
        this.shapeBuilder.setShapeCornersRadius(dip2px(mContext, radius));
        return this;
    }

    /**
     * 设置左上圆角半径
     *
     * @param radius 半径
     * @return 对象
     */
    public ShapeTextView setShapeCornersTopLeftRadius(float radius) {
        this.shapeBuilder.setShapeCornersTopLeftRadius(dip2px(mContext, radius));
        return this;
    }

    /**
     * 设置右上圆角半径
     *
     * @param radius 半径
     * @return 对象
     */
    public ShapeTextView setShapeCornersTopRightRadius(float radius) {
        this.shapeBuilder.setShapeCornersTopRightRadius(dip2px(mContext, radius));
        return this;
    }

    /**
     * 设置左下圆角半径
     *
     * @param radius 半径
     * @return 对象
     */
    public ShapeTextView setShapeCornersBottomLeftRadius(float radius) {
        this.shapeBuilder.setShapeCornersBottomLeftRadius(dip2px(mContext, radius));
        return this;
    }

    /**
     * 设置右下圆角半径
     *
     * @param radius 半径
     * @return 对象
     */
    public ShapeTextView setShapeCornersBottomRightRadius(float radius) {
        this.shapeBuilder.setShapeCornersBottomRightRadius(dip2px(mContext, radius));
        return this;
    }

    /**
     * 设置shape的宽度
     *
     * @param sizeWidth 宽
     * @return 对象
     */
    public ShapeTextView setShapeSizeWidth(int sizeWidth) {
        this.shapeBuilder.setShapeSizeWidth(dip2px(mContext, sizeWidth));
        return this;
    }

    /**
     * 设置shape的高度
     *
     * @param sizeHeight 高
     * @return 对象
     */
    public ShapeTextView setShapeSizeHeight(int sizeHeight) {
        this.shapeBuilder.setShapeSizeHeight(dip2px(mContext, sizeHeight));
        return this;
    }

    /**
     * 设置背景渐变方式
     *
     * @param gradientAngle 渐变类型
     * @return 对象
     */
    public ShapeTextView setShapeGradientAngle(int gradientAngle) {
        this.shapeBuilder.setShapeGradientAngle(gradientAngle);
        return this;
    }

    /**
     * 设置渐变中心X
     *
     * @param gradientCenterX 中心x
     * @return 对象
     */
    public ShapeTextView setShapeGradientCenterX(int gradientCenterX) {
        this.shapeBuilder.setShapeGradientCenterX(gradientCenterX);
        return this;
    }

    /**
     * 设置渐变中心Y
     *
     * @param gradientCenterY 中心y
     * @return 对象
     */
    public ShapeTextView setShapeGradientCenterY(int gradientCenterY) {
        this.shapeBuilder.setShapeGradientCenterY(gradientCenterY);
        return this;
    }

    /**
     * 设置渐变半径
     *
     * @param gradientGradientRadius 渐变半径
     * @return 对象
     */
    public ShapeTextView setShapeGradientGradientRadius(int gradientGradientRadius) {
        this.shapeBuilder.setShapeGradientGradientRadius(gradientGradientRadius);
        return this;
    }

    /**
     * 设置渐变开始的颜色
     *
     * @param gradientStartColor 开始颜色
     * @return 对象
     */
    public ShapeTextView setShapeGradientStartColor(int gradientStartColor) {
        this.shapeBuilder.setShapeGradientStartColor(gradientStartColor);
        return this;
    }

    /**
     * 设置渐变中间的颜色
     *
     * @param gradientCenterColor 中间颜色
     * @return 对象
     */
    public ShapeTextView setShapeGradientCenterColor(int gradientCenterColor) {
        this.shapeBuilder.setShapeGradientCenterColor(gradientCenterColor);
        return this;
    }

    /**
     * 设置渐变结束的颜色
     *
     * @param gradientEndColor 结束颜色
     * @return 对象
     */
    public ShapeTextView setShapeGradientEndColor(int gradientEndColor) {
        this.shapeBuilder.setShapeGradientEndColor(gradientEndColor);
        return this;
    }

    /**
     * 设置渐变类型
     *
     * @param gradientType 类型
     * @return 对象
     */
    public ShapeTextView setShapeGradientType(int gradientType) {
        this.shapeBuilder.setShapeGradientType(gradientType);
        return this;
    }

    /**
     * 设置是否使用UseLevel
     *
     * @param gradientUseLevel true  or  false
     * @return 对象
     */
    public ShapeTextView setShapeGradientUseLevel(boolean gradientUseLevel) {
        this.shapeBuilder.setShapeGradientUseLevel(gradientUseLevel);
        return this;
    }

    /**
     * 是否使用selector
     *
     * @param useSelector true  or  false
     * @return 对象
     */
    public ShapeTextView setShapeUseSelector(boolean useSelector) {
        this.shapeBuilder.setShapeUseSelector(useSelector);
        return this;
    }

    /**
     * 使用shape
     * 所有与shape相关的属性设置之后调用此方法才生效
     */
    public void setUseShape() {
        this.shapeBuilder.into(this);
    }


    /**
     * 单位转换工具类
     *
     * @param context  上下文对象
     * @param dipValue 值
     * @return 返回值
     */
    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}