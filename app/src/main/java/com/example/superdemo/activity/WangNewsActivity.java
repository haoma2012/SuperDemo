package com.example.superdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.superdemo.R;
import com.example.superdemo.ui.CircleImageView;
import com.example.superdemo.ui.WaveHelper;
import com.example.superdemo.ui.WaveView;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/15.
 * WangNewsActivity
 */
public class WangNewsActivity extends AppCompatActivity {

    @BindView(R.id.wave)
    WaveView wave;
    @BindView(R.id.head_img)
    CircleImageView headImg;
    @BindView(R.id.nick_name)
    TextView nickName;
    @BindView(R.id.news_read_tv)
    TextView mRead_news_tv;

    private int mBorderColor = Color.parseColor("#FF4081");
    private int mBorderWidth = 1;
    private WaveHelper mWaveHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        wave.setBorder(mBorderWidth, mBorderColor);
        wave.setShapeType(WaveView.ShapeType.SQUARE);
        wave.setWaveColor(Color.parseColor("#3F51B5"), Color.parseColor("#303F9F"));
        //传一个int值修改水位高度
        mWaveHelper = new WaveHelper(wave, 40);

        String result = getHun(40, 1000);
        mRead_news_tv.setText("今天阅读40篇，打败" + result + "%的人");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWaveHelper.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWaveHelper.cancel();
    }

    private String getHun(int num1, int num2) {
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();

        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);

        String result = numberFormat.format((float) num1 / (float) num2 * 100);

        return result;
    }
}
