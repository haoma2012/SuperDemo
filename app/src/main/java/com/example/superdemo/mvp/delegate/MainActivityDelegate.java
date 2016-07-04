package com.example.superdemo.mvp.delegate;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.superdemo.R;
import com.example.superdemo.mvp.mvp_frame.view.AppDelegate;

import butterknife.BindView;


/**
 * <Pre>
 *     主页面视图代理
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/1/27 11:10
 */
public class MainActivityDelegate extends AppDelegate {
    @BindView(R.id.container)
    ViewPager mViewpager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_mymvp;
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    public void setViewPagerAdapter(FragmentPagerAdapter adapter){
        mViewpager.setOffscreenPageLimit(3);//设置viewpager预加载页面数
        mViewpager.setAdapter(adapter);
    }

    public void setupWithViewPager(){
        mTabLayout.setupWithViewPager(mViewpager);
    }


}
