package com.example.superdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superdemo.R;
import com.example.superdemo.model.ItemView;
import com.example.superdemo.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/13.
 */
public class WangRecycleAdapter extends RecyclerView.Adapter {

    private List<ItemView> list;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder viewHolder = (DebounceViewHolder) holder;

        ItemView item = list.get(position);
        CommonUtils.loadGlideImage(holder.itemView.getContext(), item.imageUrl, viewHolder.recycleItemImg);
        viewHolder.recycleItemTitle.setText(item.title);
        viewHolder.recycleItemSubtitle.setText(item.description);
    }

    public void setList(List<ItemView> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycle_item_img)
        ImageView recycleItemImg;
        @BindView(R.id.recycle_item_title)
        TextView recycleItemTitle;
        @BindView(R.id.recycle_item_subtitle)
        TextView recycleItemSubtitle;

        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
