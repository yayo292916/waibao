package com.example.administrator.yyqianghongb.base;

/**
 * Created by 杨勇 on 2020/4/3.
 * QQ邮箱：824343111@qq.com
 * 万能适配器
 * 可添加 头尾布局，可用于多布局
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public abstract class YYBaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView mRecyclerView;
    private List<T> list;
    private Context context;

    private View VIEW_FOOTER;
    private View VIEW_HEADER;

    private boolean isHaveFooter;
    private boolean isHaveHeader;

    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;
    //处理 item刷新 闪烁问题，false 为处理。在嵌套recyclerView时 内层的adapter 不能使用，不然会闪烁，建议内层adapter 设置成true；
    private boolean isStable = true;

    /**
     * @param stable false 为不处理。 true 处理  默认为 true
     *               处理 item刷新 闪烁问题，在嵌套recyclerView时 内层的adapter 不能使用，不然会闪烁，建议内层adapter 设置成false；
     */
    public void setStable(boolean stable) {
        this.isStable = stable;
        setHasStableIds(isStable);
    }

    public YYBaseAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
        setHasStableIds(isStable);
    }

    public Context getContext() {
        return context;
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == TYPE_FOOTER) {
            holder = new OtherHolder(VIEW_FOOTER);
            return holder;
        } else if (viewType == TYPE_HEADER) {
            holder = new OtherHolder(VIEW_HEADER);
            return holder;
        } else {
            return getMyHolder(parent, viewType);
        }
    }

    public void upData(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holderBase, int index) {
        if (!isHeaderView(index) && !isFooterView(index)) {
            if (haveHeaderView()) index--;
            final int position = index;
            onBindViewHolderBase(holderBase, position, list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        int count = (list == null ? 0 : list.size());
        if (VIEW_FOOTER != null) {
            count++;
        }

        if (VIEW_HEADER != null) {
            count++;
        }
        return count;
    }

    @Override
    public long getItemId(int position) {
        if (isStable) {
            return position;
        } else {
            return super.getItemId(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return getItemViewTypeBase(position - (isHaveHeader ? 1 : 0));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            ((DefaultItemAnimator) Objects.requireNonNull(recyclerView.getItemAnimator())).setSupportsChangeAnimations(!isStable);
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("headerView has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }

    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }

    public boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        isHaveHeader = haveHeaderView() && position == 0;
        return isHaveHeader;
    }

    private boolean isFooterView(int position) {
        isHaveFooter = haveFooterView() && position == getItemCount() - 1;
        return isHaveFooter;
    }

    public static class OtherHolder extends RecyclerView.ViewHolder {

        public OtherHolder(View itemView) {
            super(itemView);
        }
    }

    public abstract void onBindViewHolderBase(RecyclerView.ViewHolder holderBase, int position, T data);

    public abstract RecyclerView.ViewHolder getMyHolder(ViewGroup parent, int viewType);

    public abstract int getItemViewTypeBase(int position);
}

