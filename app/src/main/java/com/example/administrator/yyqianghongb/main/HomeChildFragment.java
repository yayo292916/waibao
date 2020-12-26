package com.example.administrator.yyqianghongb.main;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.adpter.HeadListAdapter;
import com.example.administrator.yyqianghongb.adpter.HomeAdapter;
import com.example.administrator.yyqianghongb.base.BaseFragmentNewest;
import com.example.administrator.yyqianghongb.bean.GetfindGrade2ByGrade1Bean;
import com.example.administrator.yyqianghongb.bean.GetfindIndexHomeBean;
import com.example.administrator.yyqianghongb.bean.SendfindIndexHomeBean;
import com.example.administrator.yyqianghongb.utils.MyEasyHttp;
import com.example.administrator.yyqianghongb.utils.MyToast;
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
public class HomeChildFragment extends BaseFragmentNewest {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private HomeAdapter homeAdapter;
    private List<GetfindIndexHomeBean.ObjBean.ListBean> list = new ArrayList<>();
    private List<GetfindGrade2ByGrade1Bean.ObjBean> g2List = new ArrayList<>();
    private HeadListAdapter headListAdapter;
    private String g1;
    private String nowG2;
    private RecyclerView headRecyclerView;
    private int size;
    private String time;

    @Override
    public int initLayout() {
        return R.layout.home_child;
    }

    @Override
    public void initView(View view) {
        assert getArguments() != null;
        g1 = getArguments().getString("g1");
        headListAdapter = new HeadListAdapter(getContext(), g2List);
        homeAdapter = new HomeAdapter(getContext(), list);
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.item_headlist, null);
        homeAdapter.addHeaderView(headView);

        headRecyclerView = headView.findViewById(R.id.headRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        headRecyclerView.setLayoutManager(linearLayoutManager);
        headRecyclerView.setAdapter(headListAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    public void initData(Context mContext) {
        findGrade2ByGrade1();
    }

    @Override
    public void initListener() {
        headListAdapter.setOnItemClick(new HeadListAdapter.OnItemClick() {
            @Override
            public void itemClick(String g2) {
                nowG2 = g2;
                findIndexHome("down");
            }
        });
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                size++;
                findIndexHome("up");
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                findIndexHome("down");
            }
        });
    }

    private void findGrade2ByGrade1() {
        HashMap<String, Object> maps = new HashMap<>();
        maps.put("grade1", g1);
        MyEasyHttp.post(getContext(), "binfo/findGrade2ByGrade1", maps, GetfindGrade2ByGrade1Bean.class, new MyEasyHttp.OnResult<GetfindGrade2ByGrade1Bean>() {
            @Override
            public void onSuccess(GetfindGrade2ByGrade1Bean response) {
                if (response.getStatus() == 200) {
                    g2List = response.getObj();
                    if (g2List.size() > 0) {
                        g2List.get(0).setClick(true);
                        headListAdapter.upData(g2List);
                        nowG2 = g2List.get(0).getGrade2();
                        headRecyclerView.setVisibility(View.VISIBLE);
                    } else {
                        nowG2 = "0";
                        headRecyclerView.setVisibility(View.GONE);
                    }
                    findIndexHome("down");
                } else {
                    MyToast.addToast(response.getMsg());
                }
            }

            @Override
            public void onError(String e) {
                MyToast.addToast(e);
            }
        });
    }

    private void findIndexHome(String isDown) {
        if (isDown.equals("down"))
            size = 0;
        SendfindIndexHomeBean bean = new SendfindIndexHomeBean();
        bean.setGrade1(g1);
        bean.setGrade2(nowG2);
        bean.setPage(size);
        bean.setTime(time);
        MyEasyHttp.json(getContext(), "binfo/findIndexHome", bean, GetfindIndexHomeBean.class, new MyEasyHttp.OnResult<GetfindIndexHomeBean>() {
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
                        time = response.getObj().getTime();
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    MyToast.addToast("你拒绝了权限申请，可能无法下载文件到本地哟！");
                }
                break;
            default:
        }
    }
}
