package com.example.superdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.superdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人信息
 */
public class PersonInfoActivity extends AppCompatActivity {

    @BindView(R.id.main_top_name)
    TextView mainTopName;
    @BindView(R.id.main_top_back)
    RelativeLayout mTopBack;
    @BindView(R.id.button)
    Button mWang_btn;
    @BindView(R.id.button2)
    Button mYun_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        mainTopName.setText("不同风格-个人信息");
    }

    @OnClick({R.id.main_top_back, R.id.button, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_top_back:
                finish();
                break;
            case R.id.button:
                //网易新闻我的界面
                startActivity(new Intent(PersonInfoActivity.this, WangNewsActivity.class));
                break;
            case R.id.button2:
                //网易云个人信息
                startActivity(new Intent(PersonInfoActivity.this, WangYiYunActivity.class));
                break;
        }
    }
}
