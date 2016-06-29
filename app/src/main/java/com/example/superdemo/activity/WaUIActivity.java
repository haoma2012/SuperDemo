package com.example.superdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.superdemo.R;
import com.example.superdemo.chart.HelloChartActivity;
import com.example.superdemo.weixin.WeiQuanActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * UI
 */
public class WaUIActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.chart_btn)
    Button mChartBtn;
    @BindView(R.id.weixin_btn)
    Button mWeixinBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ui);
        ButterKnife.bind(this);

        setToolBar();
    }

    private void setToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setTitle(getResources().getString(R.string.line_chart));
        mToolbar.setNavigationIcon(R.mipmap.back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.chart_btn,R.id.weixin_btn})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chart_btn:
                startActivity(new Intent(this,HelloChartActivity.class));
                break;
            case R.id.weixin_btn:
                startActivity(new Intent(this,WeiQuanActivity.class));
                break;
        }

    }
}
