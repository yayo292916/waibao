package com.example.administrator.yyqianghongb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yyqianghongb.base.BaseFragmentNewest;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by 杨勇 on 2020/12/4.
 * QQ邮箱：824343111@qq.com
 */
public class TestFragment extends BaseFragmentNewest {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Override
    public int initLayout() {
        return  R.layout.activity_test2;
    }

    @Override
    public void initView(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_list_base, parent, false);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 50;
            }
        });
    }

    @Override
    public void initData(Context mContext) {

    }

    @Override
    public void initListener() {

    }
    static class Holder extends RecyclerView.ViewHolder {

        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
