package com.example.administrator.yyqianghongb.adpter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.base.GetBaseBean;
import com.example.administrator.yyqianghongb.base.YYBaseAdapter;
import com.example.administrator.yyqianghongb.bean.GetfindIndexHomeBean;
import com.example.administrator.yyqianghongb.main.X5TbsFileServicePage;
import com.example.administrator.yyqianghongb.utils.MyEasyHttp;
import com.example.administrator.yyqianghongb.utils.MyLoge;
import com.example.administrator.yyqianghongb.utils.MyToast;
import com.example.administrator.yyqianghongb.view.shapeTvPage.ShapeTextView;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class HomeAdapter extends YYBaseAdapter<GetfindIndexHomeBean.ObjBean.ListBean> {

    public HomeAdapter(Context context, List<GetfindIndexHomeBean.ObjBean.ListBean> list) {
        super(context, list);
    }

    @Override
    public void onBindViewHolderBase(RecyclerView.ViewHolder holderBase, int position, GetfindIndexHomeBean.ObjBean.ListBean data) {
        MyHolder holder = (MyHolder) holderBase;
        holder.title.setText(data.getTitle());
        holder.uname.setText(data.getUname());
        if (TextUtils.isEmpty(data.getGrade2Name())){
            holder.type.setVisibility(View.GONE);
        }else {
            holder.type.setVisibility(View.VISIBLE);
            holder.type.setText(data.getGrade2Name());
        }

        holder.time.setText(data.getCtime());
        holder.typeImg.setImageResource(data.getType() == 1 ? R.drawable.home_page_icon_word : R.drawable.home_page_icon_pdf);
        holderBase.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClick != null)
                    onItemClick.longItemClick(position);
                return false;
            }
        });
        holderBase.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //动态权限申请
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    if (onItemClick != null)
                        onItemClick.itemClick(position);
                    browse(data.getBid());
                    MyLoge.addLoge("fileUrl", data.getUrl());
                    Intent intent = new Intent(getContext(), X5TbsFileServicePage.class);
                    intent.putExtra("title", data.getTitle());
                    intent.putExtra("bid", data.getBid());
                    intent.putExtra("url", data.getUrl());
                    intent.putExtra("typeName", data.getTypeName());
                    getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getMyHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_list_base, parent, false);
        return new MyHolder(view);
    }

    @Override
    public int getItemViewTypeBase(int position) {
        return 0;
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.typeImg)
        ImageView typeImg;
        @BindView(R.id.uname)
        TextView uname;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.type)
        ShapeTextView type;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void browse(String bid) {
        HashMap<String, Object> maps = new HashMap<>();
        maps.put("bid", bid);
        MyEasyHttp.post(getContext(), "browse/browse", maps, GetBaseBean.class, new MyEasyHttp.OnResult<GetBaseBean>() {
            @Override
            public void onSuccess(GetBaseBean response) {
            }

            @Override
            public void onError(String e) {
                MyToast.addToast(e);
            }
        });
    }

    public OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void itemClick(int position);
        void longItemClick(int position);
    }
}
