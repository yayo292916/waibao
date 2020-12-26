package com.example.administrator.yyqianghongb.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.utils.PxtoDpUntils;


/**
 * Created by 杨勇 on 2019/8/22.
 * QQ邮箱：824343111@qq.com
 */
public class YYTitleBar extends RelativeLayout {
    //左侧 返回按键  右侧菜单按钮
    private LineLinearLayout back, menu;
    //左侧 右侧 控件的宽度
    private int backTotalWidth;
    private BackView imgBack;
    private TextView rightText;
    private String rightTextStr = "";
    private int rightSize = 14;
    private int titleColor = 0xff222222;
    private int rightColor = 0xff222222;
    private int backColor = 0xff222222;
    private int lineColor = 0xffececec;
    //底部线条
    private View lineView;
    //标题
    private TextView titleTv;
    private Context context;
    private LayoutParams backParams, lineParams, titleParams, menuParams;
    private int cqs_height;
    private String titleStr;

    public YYTitleBar(Context context) {
        super(context);
        initView(context, null, 0);
    }

    public YYTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    public YYTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.YYTitleBar, defStyleAttr, 0);
        titleStr = a.getString(R.styleable.YYTitleBar_title);
        backColor = a.getColor(R.styleable.YYTitleBar_back_color, backColor);
        rightTextStr = a.getString(R.styleable.YYTitleBar_right_content);
        rightSize = a.getInt(R.styleable.YYTitleBar_right_size, 14);
        rightColor = a.getColor(R.styleable.YYTitleBar_right_color, rightColor);
        titleColor = a.getColor(R.styleable.YYTitleBar_title_color, titleColor);
        lineColor = a.getColor(R.styleable.YYTitleBar_line_colors, lineColor);
        a.recycle();
        cqs_height = getResources().getDimensionPixelSize(R.dimen.status_bar_height);
        addLayout();
    }

    private void addLayout() {
        setPadding(0, PxtoDpUntils.dp2pxInt(getContext(), 25), 0, 0);
        back = new LineLinearLayout(context);
        imgBack = new BackView(context);
        imgBack.setColor(backColor);

        menu = new LineLinearLayout(context);
        rightText = new TextView(context);
        rightText.setTextColor(rightColor);
        rightText.setTextSize(rightSize);
        if (rightTextStr != null)
            rightText.setText(rightTextStr);

        lineView = new View(context);
        titleTv = new TextView(context);

        titleTv.setTextColor(titleColor);
        titleTv.setMaxLines(1);
        titleTv.setMaxEms(13);
        titleTv.setEllipsize(TextUtils.TruncateAt.END);
        titleTv.setTextSize(16);
        titleTv.setGravity(Gravity.CENTER);
        lineView.setBackgroundColor(lineColor);
//        back.setBackgroundResource(R.drawable.shuibowen);
        back.setGravity(Gravity.CENTER);
        back.addView(imgBack);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTitleBackClick != null)
                    onTitleBackClick.titleBackClick();
            }
        });
        menu.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
//        menu.setBackgroundResource(R.drawable.shuibowen);
        menu.addView(rightText);
        menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTitleMenuClick != null)
                    onTitleMenuClick.titleMenuClick();
            }
        });
        addView(lineView, 0);
        addView(back, 1);
        addView(titleTv, 2);
        addView(menu, 3);
        lineView.bringToFront();
        //在底部添加一条线
        lineParams = new LayoutParams(0
                , 0);
        backParams = new LayoutParams(0
                , 0);
        titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT
                , LayoutParams.WRAP_CONTENT);
        menuParams = new LayoutParams(LayoutParams.WRAP_CONTENT
                , LayoutParams.WRAP_CONTENT);

        if (titleStr != null)
            titleTv.setText(titleStr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int viewWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int viewHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        switch (viewHeightMode) {
            case MeasureSpec.EXACTLY:   //说明在布局文件中使用的是具体值：100dp或者match_parent
                backTotalWidth = viewHeight > PxtoDpUntils.dp2pxInt(getContext(), 100) ?
                        PxtoDpUntils.dp2pxInt(getContext(), 100) :
                        viewHeight - cqs_height;
                viewHeight = viewHeight > PxtoDpUntils.dp2pxInt(getContext(), 100) ?
                        PxtoDpUntils.dp2pxInt(getContext(), 100) :
                        viewHeight;
                break;
            case MeasureSpec.AT_MOST:  //说明在布局文件中使用的是wrap_content:
                //这时我们也写死宽高
                backTotalWidth = PxtoDpUntils.dp2pxInt(getContext(), 65) - cqs_height;
                viewHeight = PxtoDpUntils.dp2pxInt(getContext(), 65);
                break;
            default:
                backTotalWidth = PxtoDpUntils.dp2pxInt(getContext(), 65) - cqs_height;
                viewHeight = PxtoDpUntils.dp2pxInt(getContext(), 65);
                break;
        }
        backParams.width = PxtoDpUntils.dp2pxInt(getContext(), 46);
        backParams.height = backTotalWidth;
        backParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        back.setLayoutParams(backParams);

        menuParams.width = viewWidth / 2;
        menuParams.height = backTotalWidth;
        menuParams.setMargins(0, 0, PxtoDpUntils.dp2pxInt(context, 12), 0);
        menuParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        menuParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        menu.setLayoutParams(menuParams);

        lineParams.width = viewWidth;
        lineParams.height = PxtoDpUntils.dp2pxInt(getContext(), 1);
        lineParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lineView.setLayoutParams(lineParams);

        titleParams.height = backTotalWidth;
        titleParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        titleParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        titleTv.setLayoutParams(titleParams);

        setMeasuredDimension(viewWidth, viewHeight);
        super.onMeasure(widthMeasureSpec, PxtoDpUntils.dp2pxInt(getContext(), 65));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public OnTitleBackClick onTitleBackClick;
    public OnTitleMenuClick onTitleMenuClick;

    public void setOnTitleMenuClick(OnTitleMenuClick onTitleMenuClick) {
        this.onTitleMenuClick = onTitleMenuClick;
    }

    public void setOnTitleBackClick(OnTitleBackClick onTitleBackClick) {
        this.onTitleBackClick = onTitleBackClick;
    }

    public interface OnTitleBackClick {
        void titleBackClick();
    }

    public interface OnTitleMenuClick {
        void titleMenuClick();
    }

    public void setText(String title) {
        titleTv.setText(title);
    }

    public void setBackColor(int color) {
        imgBack.setColor(color);
    }

    public TextView getTitleTextView() {
        return titleTv;
    }

    public TextView getRightText() {
        return rightText;
    }

    public View getLine() {
        return lineView;
    }

}
