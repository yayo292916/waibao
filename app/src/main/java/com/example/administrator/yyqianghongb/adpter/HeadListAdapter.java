package com.example.administrator.yyqianghongb.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.base.YYBaseAdapter;
import com.example.administrator.yyqianghongb.bean.GetfindGrade2ByGrade1Bean;
import com.example.administrator.yyqianghongb.view.shapeTvPage.ShapeTextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class HeadListAdapter extends YYBaseAdapter<GetfindGrade2ByGrade1Bean.ObjBean> {

    public HeadListAdapter(Context context, List<GetfindGrade2ByGrade1Bean.ObjBean> list) {
        super(context, list);
    }

    @Override
    public void onBindViewHolderBase(RecyclerView.ViewHolder holderBase, int position, GetfindGrade2ByGrade1Bean.ObjBean data) {
        MyHolder holder = (MyHolder) holderBase;
        holder.title.setText(data.getGrade2Name());
        if (data.isClick()) {
            holder.title.setShapeSolidColor(0xFFEE3D37);
            holder.title.setUseShape();
            holder.title.setTextColor(0xffffffff);
        } else {
            holder.title.setShapeSolidColor(0xFFF6F6F6);
            holder.title.setUseShape();
            holder.title.setTextColor(0xff666666);
        }

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (GetfindGrade2ByGrade1Bean.ObjBean b : getList()
                ) {
                    b.setClick(false);
                }
                data.setClick(true);
                notifyDataSetChanged();
                if (onItemClick != null)
                    onItemClick.itemClick(data.getGrade2());
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getMyHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_headitem, parent, false);
        return new MyHolder(view);
    }

    @Override
    public int getItemViewTypeBase(int position) {
        return 0;
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        ShapeTextView title;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void itemClick(String g2);
    }

}
