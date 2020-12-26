package com.example.administrator.yyqianghongb.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.base.YYBaseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class TipsDialog extends YYBaseDialog {
    public static final int DELETE_HISTORY = 0;
    public static final int DELETE_COLL = 1;
    public static final int DELETE_OUT_LOGIN = 2;
    private int type;

    public TipsDialog(Context context) {
        super(context);
    }

    public TipsDialog(Context context, int type) {
        super(context);
        this.type = type;
    }

    @Override
    public int layoutRes() {
        return R.layout.dialog_tips;
    }

    @Override
    protected void initData(View inflate) {
        ViewHolder holder = new ViewHolder(inflate);
        switch (type) {
            case DELETE_HISTORY:
                holder.left.setText("取消");
                holder.left.setTextColor(0xff333333);
                holder.right.setText("删除");
                holder.right.setTextColor(0xFFF55050);
                holder.title.setText("确定删除全部历史记录？");
                break;
            case DELETE_COLL:
                holder.left.setText("取消");
                holder.left.setTextColor(0xff333333);
                holder.right.setText("删除");
                holder.right.setTextColor(0xFFF55050);
                holder.title.setText("确认删除1条收藏吗？");
                break;
            case DELETE_OUT_LOGIN:
                holder.left.setText("取消");
                holder.left.setTextColor(0xff333333);
                holder.right.setText("退出");
                holder.right.setTextColor(0xFFF55050);
                holder.title.setText("您确定要退出登录吗？");
                break;
        }

        holder.left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog();
                if (onBtnClick != null)
                    onBtnClick.leftClick();
            }
        });
        holder.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog();
                if (onBtnClick != null)
                    onBtnClick.rightClick();
            }
        });
    }

    static
    class ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.left)
        TextView left;
        @BindView(R.id.right)
        TextView right;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public OnBtnClick onBtnClick;

    public void setOnBtnClick(OnBtnClick onBtnClick) {
        this.onBtnClick = onBtnClick;
    }

    public interface OnBtnClick {
        void leftClick();

        void rightClick();
    }
}
