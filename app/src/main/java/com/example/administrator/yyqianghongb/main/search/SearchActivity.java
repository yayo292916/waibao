package com.example.administrator.yyqianghongb.main.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.adpter.HomeAdapter;
import com.example.administrator.yyqianghongb.base.BaseActivityNewest;
import com.example.administrator.yyqianghongb.bean.GetfindIndexHomeBean;
import com.example.administrator.yyqianghongb.bean.SendsearchBinfosBean;
import com.example.administrator.yyqianghongb.dialog.TipsDialog;
import com.example.administrator.yyqianghongb.utils.MyEasyHttp;
import com.example.administrator.yyqianghongb.utils.MyToast;
import com.example.administrator.yyqianghongb.utils.SpUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class SearchActivity extends BaseActivityNewest {
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.delete)
    ImageView delete;
    @BindView(R.id.mFlowLayout)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.historyLayout)
    LinearLayout historyLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private TagAdapter tagAdapter;
    private List<String> historyList;
    private String value;
    private int size;
    private HomeAdapter homeAdapter;
    private List<GetfindIndexHomeBean.ObjBean.ListBean> list = new ArrayList<>();

    @Override
    protected int setStatusBar() {
        return 0;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.search;
    }

    @Override
    protected void initView() {
        homeAdapter = new HomeAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    protected void initData() {
        historyList = SpUtil.getList(getContext(), "history");
        if (historyList == null) {
            historyList = new ArrayList<>();
        }
        tagAdapter = new TagAdapter<String>(historyList) {
            @Override
            public View getView(FlowLayout parent, int position, String bean) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_history, parent, false);
                final TextView tv = view.findViewById(R.id.title);
                ImageView img = view.findViewById(R.id.delete);

                tv.setText(bean);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edit.setText(tv.getText().toString());
                        value = tv.getText().toString();
                        searchBinfos("down");
                        judgeHistorySearchLayout();
                    }
                });

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeHistory(position);
                        notifyDataChanged();
                        judgeHistorySearchLayout();
                    }
                });
                return view;
            }
        };
        mFlowLayout.setAdapter(tagAdapter);
        judgeHistorySearchLayout();
    }

    private void removeHistory(int position) {
        historyList.remove(position);
        SpUtil.putList(getContext(), "history", historyList);
    }

    @Override
    protected void initListener() {
        homeAdapter.setOnItemClick(new HomeAdapter.OnItemClick() {
            @Override
            public void itemClick(int position) {
                boolean isCf = false;
                for (String str : historyList
                ) {
                    if (str.equals(list.get(position).getTitle())) {
                        isCf = true;
                        break;
                    }
                }
                if (!isCf) {
                    historyList.add(list.get(position).getTitle());
                    SpUtil.putList(getContext(), "history", historyList);
                    tagAdapter.notifyDataChanged();
                }
            }

            @Override
            public void longItemClick(int position) {

            }
        });
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                value = s.toString();
                searchBinfos("down");
                judgeHistorySearchLayout();
            }
        });
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                size++;
                searchBinfos("up");
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                searchBinfos("down");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipsDialog tipsDialog = new TipsDialog(getContext(), TipsDialog.DELETE_HISTORY).init();
                tipsDialog.setOnBtnClick(new TipsDialog.OnBtnClick() {
                    @Override
                    public void leftClick() {

                    }

                    @Override
                    public void rightClick() {
                        historyList.clear();
                        SpUtil.putList(getContext(), "history", historyList);
                        judgeHistorySearchLayout();
                    }
                });
                tipsDialog.showDialog();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void judgeHistorySearchLayout() {
        if (TextUtils.isEmpty(value)) {
            refreshLayout.setVisibility(View.GONE);
            if (historyList.size() > 0)
                historyLayout.setVisibility(View.VISIBLE);
            else
                historyLayout.setVisibility(View.GONE);
        } else {
            refreshLayout.setVisibility(View.VISIBLE);
            historyLayout.setVisibility(View.GONE);
        }
    }

    private void searchBinfos(String isDown) {
        if (isDown.equals("down"))
            size = 0;
        SendsearchBinfosBean bean = new SendsearchBinfosBean();
        bean.setPage(size);
        bean.setValue(value);
        bean.setTime("");
        MyEasyHttp.json(getContext(), "binfo/searchBinfos", bean, GetfindIndexHomeBean.class, new MyEasyHttp.OnResult<GetfindIndexHomeBean>() {
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


}
