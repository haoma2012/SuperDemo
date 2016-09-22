package com.example.superdemo.ocr;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.example.superdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

/**
 * Created by Administrator on 2016/9/19.
 */
public class leadActivity extends AppCompatActivity {

    @BindView(R.id.lead_pager)
    ViewPager mViewPager;
    private int[] Imgs = {R.drawable.lead1, R.drawable.lead2, R.drawable.lead3, R.drawable.lead4};
    private List<ImageView> imhList = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(FLAG_FULLSCREEN);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        setContentView(R.layout.activity_lead);
        ButterKnife.bind(this);

        initView();


    }

    private void initView() {

        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Imgs.length;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView localImageView = new ImageView(leadActivity.this);
                localImageView.setImageResource(leadActivity.this.Imgs[position]);
                localImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(localImageView);
                leadActivity.this.imhList.add(localImageView);
                if (position == 3) {
                    localImageView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View paramAnonymous2View) {

                        }
                    });
                }
                return localImageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
                container.removeView(imhList.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return object == view;
            }


        });
    }
}
