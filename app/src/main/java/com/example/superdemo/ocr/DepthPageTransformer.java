package com.example.superdemo.ocr;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2016/9/19.
 * 自己实现ViewPager切换效果
 */
public class DepthPageTransformer implements ViewPager.PageTransformer {

    private static float MIN_SCALE = 0.55F;

    public void transformPage(View paramView, float paramFloat) {
        int i = paramView.getWidth();
        if (paramFloat < -1.0F) {
            paramView.setAlpha(0.0F);
            return;
        }
        if (paramFloat <= 0.0F) {
            paramView.setAlpha(1.0F);
            paramView.setTranslationX(0.0F);
            paramView.setScaleX(1.0F);
            paramView.setScaleY(1.0F);
            return;
        }
        if (paramFloat <= 1.0F) {
            paramView.setAlpha(1.0F - paramFloat);
            paramView.setTranslationX(i * -paramFloat);
            paramFloat = MIN_SCALE + (1.0F - MIN_SCALE) * (1.0F - Math.abs(paramFloat));
            paramView.setScaleX(paramFloat);
            paramView.setScaleY(paramFloat);
            return;
        }
        paramView.setAlpha(0.0F);
    }
}
