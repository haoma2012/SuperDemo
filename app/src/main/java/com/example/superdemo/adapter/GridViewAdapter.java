package com.example.superdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.superdemo.R;
import com.example.superdemo.utils.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> mList;

    public GridViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.with_grid_view_item_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CommonUtils.loadGlideImage(context, mList.get(position), viewHolder.mImageView);
        Log.i("key", "url = " + mList.get(position));
        return convertView;
    }

    private class ViewHolder {
        private ImageView mImageView;
    }
}
