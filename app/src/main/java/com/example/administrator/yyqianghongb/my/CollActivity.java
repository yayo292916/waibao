package com.example.administrator.yyqianghongb.my;

import android.os.Bundle;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.adpter.HomeAdapter;
import com.example.administrator.yyqianghongb.base.BaseActivityNewest;
import com.example.administrator.yyqianghongb.base.GetBaseBean;
import com.example.administrator.yyqianghongb.bean.GetfindIndexHomeBean;
import com.example.administrator.yyqianghongb.dialog.TipsDialog;
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
public class CollActivity extends BaseActivityNewest {
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
        return R.layout.coll;
    }

    @Override
    protected void initView() {
        homeAdapter = new HomeAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        collectData("down");
    }

    @Override
    protected void initListener() {
        homeAdapter.setOnItemClick(new HomeAdapter.OnItemClick() {
            @Override
            public void itemClick(int position) {

            }

            @Override
            public void longItemClick(int position) {
                TipsDialog tipsDialog = new TipsDialog(getContext(), TipsDialog.DELETE_COLL).init();
                tipsDialog.setOnBtnClick(new TipsDialog.OnBtnClick() {
                    @Override
                    public void leftClick() {

                    }

                    @Override
                    public void rightClick() {
                        deleteCollect(list.get(position).getCid());
                        list.remove(position);
                        homeAdapter.upData(list);
                    }
                });
                tipsDialog.showDialog();
            }
        });
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
                collectData("up");
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                collectData("down");
            }
        });
    }

    private void collectData(String isDown) {
        if (isDown.equals("down"))
            size = 0;
        HashMap<String, Object> maps = new HashMap<>();
        maps.put("size", size);
        MyEasyHttp.post(getContext(), "collect/collectData", maps, GetfindIndexHomeBean.class, new MyEasyHttp.OnResult<GetfindIndexHomeBean>() {
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
                refreshLayout.finishLoadMore();
                refreshLayout.finishRefresh();
            }
        });
    }

    private void deleteCollect(String cid) {
        HashMap<String, Object> maps = new HashMap<>();
        maps.put("cid", cid);
        MyEasyHttp.post(getContext(), "collect/deleteCollect", maps, GetBaseBean.class, new MyEasyHttp.OnResult<GetBaseBean>() {
            @Override
            public void onSuccess(GetBaseBean response) {
            }

            @Override
            public void onError(String e) {

            }
        });
    }

}
