package com.example.superdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superdemo.R;
import com.example.superdemo.adapter.WangRecycleAdapter;
import com.example.superdemo.model.ItemView;
import com.example.superdemo.ui.CircleImageView;
import com.example.superdemo.utils.CommonUtils;
import com.example.superdemo.utils.RecycleItemClick;
import com.example.superdemo.utils.WaConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/15.
 * WangYiYunActivity
 */
public class WangYiYunActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.col_toolbar_layout)
    CollapsingToolbarLayout colToolbarLayout;
    @BindView(R.id.recycleView)
    RecyclerView mRecycleView;
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.head_img)
    CircleImageView headImg;
    @BindView(R.id.nick_name)
    TextView nickName;

    private List<ItemView> mList;
    WangRecycleAdapter adapter = new WangRecycleAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wangyiyun);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getData();
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(adapter);

        initView();
    }

    private void initView() {

        RecycleItemClick.addTo(mRecycleView).setOnItemClickListener(new RecycleItemClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        CommonUtils.loadGlideImage(getApplicationContext(), WaConstants.imageUrl, backImg);


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
