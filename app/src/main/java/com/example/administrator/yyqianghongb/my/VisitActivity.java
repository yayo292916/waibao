package com.example.administrator.yyqianghongb.my;

import android.os.Bundle;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.adpter.HomeAdapter;
import com.example.administrator.yyqianghongb.base.BaseActivityNewest;
import com.example.administrator.yyqianghongb.bean.GetfindIndexHomeBean;
import com.example.administrator.yyqianghongb.utils.MyEasyHttp;
import com.example.administrator.yyqianghongb.utils.MyToast;
import com.example.administrator.yyqianghongb.view.YYTitleBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class VisitActivity extends BaseActivityNewest {
    @BindView(R.id.bar)
    YYTitleBar bar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int size;
    private HomeAdapter homeAdapter;
    private List<GetfindIndexHomeBean.ObjBean.ListBean> list = new ArrayList<>();

    @Override
    protected int setStatusBar() {
        return 0;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.visit;
    }

    @Override
    protected void initView() {
        homeAdapter = new HomeAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    protected void initData() {
        browseHistory("down");
    }

    @Override
    protected void initListener() {
        bar.setOnTitleBackClick(new YYTitleBar.OnTitleBackClick() {
            @Override
            public void titleBackClick() {
                finish();
            }
        });
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                size++;
                browseHistory("up");
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                browseHistory("down");
            }
        });
    }


    private void browseHistory(String isDown) {
        if (isDown.equals("down"))
            size = 0;
        HashMap<String, Object> maps = new HashMap<>();
        maps.put("size", size);
        MyEasyHttp.post(getContext(), "browse/browseHistory", maps, GetfindIndexHomeBean.class, new MyEasyHttp.OnResult<GetfindIndexHomeBean>() {
            @Override
            public void onSuccess(GetfindIndexHomeBean response) {
                if (response.getStatus() == 200) {
                    if (isDown.equals("down"))
                        list.clear();
                    if (response.getObj() == null) {
                        if (isDown.equals("down"))
                            list.clear();
                    } else {
                        list.addAll(response.getObj().getList());
                    }
                    homeAdapter.upData(list);
                } else {
                    MyToast.addToast(response.getMsg());
                }
                refreshLayout.finishLoadMore();
                refreshLayout.finishRefresh();
            }

            @Override
            public void onError(String e) {
                MyToast.addToast(e);
                refreshLayout.finishLoadMore();
                refreshLayout.finishRefresh();
            }
        });
    }

}
