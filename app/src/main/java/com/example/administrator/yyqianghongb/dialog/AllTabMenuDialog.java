package com.example.administrator.yyqianghongb.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.base.YYBaseDialog;
import com.example.administrator.yyqianghongb.bean.GetfindGrade1Bean;
import com.example.administrator.yyqianghongb.view.shapeTvPage.ShapeTextView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class AllTabMenuDialog extends YYBaseDialog {
    private TagAdapter tagAdapter;
    private List<GetfindGrade1Bean.ObjBean> g1List;

    public void setG1List(List<GetfindGrade1Bean.ObjBean> g1List) {
        this.g1List = g1List;
    }

    public AllTabMenuDialog(Context context) {
        super(context);
    }

    @Override
    public int layoutRes() {
        return R.layout.dialog_tabmenu;
    }

    @Override
    protected void initData(View inflate) {
        ViewHolder holder = new ViewHolder(inflate);
        tagAdapter = new TagAdapter<GetfindGrade1Bean.ObjBean>(g1List) {
            @Override
            public View getView(FlowLayout parent, int position, GetfindGrade1Bean.ObjBean bean) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_dialog_tabmenu, parent, false);
                final ShapeTextView tv = view.findViewById(R.id.title);
                tv.setText(bean.getGrade1Name());
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClick!=null)
                            onItemClick.itemClick(position);
                        closeDialog();
                    }
                });

                return view;
            }
        };

        holder.mFlowLayout.setAdapter(tagAdapter);
        setWindowsMatch();
        setCanceledOnTouchOutside(false);
        setGravity(BOTTOM);
        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog();
            }
        });
    }

    static
    class ViewHolder {
        @BindView(R.id.close)
        ImageView close;
        @BindView(R.id.commit)
        TextView commit;
        @BindView(R.id.mFlowLayout)
        TagFlowLayout mFlowLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void itemClick(int position);
    }
}
