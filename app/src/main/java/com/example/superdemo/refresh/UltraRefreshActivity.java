package com.example.superdemo.refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.superdemo.R;
import com.example.superdemo.adapter.WangRecycleAdapter;
import com.example.superdemo.model.ItemView;
import com.example.superdemo.ui.swiper.SwipeMenuListView;
import com.example.superdemo.utils.WaConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Administrator on 2016/6/20.
 * UltraRefreshActivity
 */
public class UltraRefreshActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rotate_header_list_view)
    SwipeMenuListView mListView;
    @BindView(R.id.rotate_header_grid_view_frame)
    PtrClassicFrameLayout mPtrFrame;

    private List<ItemView> mList;
    private List<ItemView> list = new ArrayList<>();
    WangRecycleAdapter adapter = new WangRecycleAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ulrefresh);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setTitle("ulrefresh");
        mToolbar.setNavigationIcon(R.mipmap.back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getData();

        adapter.setList(mList);

        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                updateData();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);

//        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }

    private void updateData() {
        list.clear();
        for (int i = 0; i < 20; i++) {
            ItemView itemView = new ItemView();
            itemView.setTitle("我喜欢的音乐");
            itemView.setDescription("758首 播放1796次");
            itemView.setImageUrl(WaConstants.imageUrl2);
            list.add(itemView);
        }
        mPtrFrame.refreshComplete();
        mList.clear();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    private void getData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ItemView itemView = new ItemView();
            itemView.setTitle("我喜欢的音乐");
            itemView.setDescription("758首 播放1796次");
            itemView.setImageUrl("http://zhuangbi.idagou.com/i/2015-07-07-26c59a95c0ec52dd9b94f62bb0724263.gif");
            mList.add(itemView);
        }
        adapter.setList(mList);
    }

}
