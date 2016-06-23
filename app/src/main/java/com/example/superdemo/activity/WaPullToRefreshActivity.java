package com.example.superdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.superdemo.R;
import com.example.superdemo.refresh.UltraRefreshActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * WaPullToRefreshActivity
 */
public class WaPullToRefreshActivity extends AppCompatActivity {
    @BindView(R.id.refresh_btn1)
    Button mRefreshBtn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.refresh_btn1)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.refresh_btn1:
                startActivity(new Intent(this, UltraRefreshActivity.class));
                break;
        }
    }
}
