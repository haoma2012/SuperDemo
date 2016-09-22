package com.example.superdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.superdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/19.
 */
public class MyOCRTestActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activty_ocrtest);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        webview.loadUrl("http://www.bejson.com/chargeservice/ocr/");
    }
}
